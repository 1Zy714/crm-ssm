package com.bjpowernode.crm.settings.web.controller;

import com.bjpowernode.crm.commons.constants.Constants;
import com.bjpowernode.crm.commons.domain.ReturnObject;
import com.bjpowernode.crm.commons.utils.DateUtils;
import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/settings/qx/user/")
public class UserController {
    @Resource
    private UserService userService;
    /**
     * 跳转登陆页面
     * */
    @RequestMapping("toLogin.do")
    public String toLogin(){

        return "settings/qx/user/login";
    }
    @RequestMapping("login.do")
    @ResponseBody
    public Object login(HttpSession session, HttpServletResponse response,HttpServletRequest request, String loginAct, String loginPwd, String isRemPwd){
        String ip = request.getRemoteAddr();
        System.out.println("ip: "+ip);
        System.out.println("isRemPwd: "+isRemPwd);
        //将数据封装map
        Map<String,Object> map = new HashMap<>();
        map.put("loginAct",loginAct);
        map.put("loginPwd",loginPwd);
        //调用service方法，查询用户
        User user = userService.queryUserByLoginActAndPwd(map);
        ReturnObject ro = new ReturnObject();
        if(user == null){
            //登陆失败，用户名密码不匹配
            ro.setCode(Constants.RETURN_OBJECT_CODE_FAILED);
            ro.setMsg("用户名或者密码错误");

        }else if(DateUtils.formatDateTime(new Date()).compareTo(user.getExpireTime())>0) {
            //登陆失败，账号已过期
            ro.setCode(Constants.RETURN_OBJECT_CODE_FAILED);
            ro.setMsg("账号过期");

        } else if(!user.getAllowIps().contains(ip)){
            //登陆失败，ip地址非法
            ro.setCode(Constants.RETURN_OBJECT_CODE_FAILED);
            ro.setMsg("ip非法");

        }else if("0".equals(user.getLockState())){
            //登陆失败，状态锁定
            ro.setCode(Constants.RETURN_OBJECT_CODE_FAILED);
            ro.setMsg("账号处于锁定状态");
        }else {
            //登陆成功
            //保存user到session域
            session.setAttribute(Constants.SESSION_USER,user);
            ro.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
            //判断是否需要填写cookie
            if("true".equals(isRemPwd)){
               Cookie c1 =  new Cookie("loginAct",loginAct);
               c1.setMaxAge(10*24*60*60);
                response.addCookie(c1);
               Cookie c2 = new Cookie("loginPwd",loginPwd);
               c2.setMaxAge(10*24*60*60);
               response.addCookie(c2);
                Cookie c3 = new Cookie("checked","checked");
                c2.setMaxAge(10*24*60*60);
                response.addCookie(c3);
            }else {
                //如果不需要记录密码，删除cookie:age设为0，则删除
                Cookie c1 =  new Cookie("loginAct","1");
                c1.setMaxAge(0);
                response.addCookie(c1);
                Cookie c2 = new Cookie("loginPwd","1");
                c2.setMaxAge(0);
                response.addCookie(c2);
            }
        }
            return ro;
    }

    @RequestMapping("logout.do")
    public String logout(HttpSession session,HttpServletResponse response){
        //清空cookie
        Cookie c1 =  new Cookie("loginAct","0");
        c1.setMaxAge(0);
        response.addCookie(c1);
        Cookie c2 = new Cookie("loginPwd","0");
        c2.setMaxAge(0);

        //销毁session
        session.invalidate();
        //跳转到项目首页
        /*
        * 注意这里不能定位index所在，因为重定向redirect不能访问WEB-INF目录的资源
        * */
        return "redirect:/";
    }

}
