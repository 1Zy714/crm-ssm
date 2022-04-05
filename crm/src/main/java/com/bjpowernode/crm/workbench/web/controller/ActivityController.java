package com.bjpowernode.crm.workbench.web.controller;

import com.bjpowernode.crm.commons.constants.Constants;
import com.bjpowernode.crm.commons.domain.ReturnObject;
import com.bjpowernode.crm.commons.utils.DateUtils;
import com.bjpowernode.crm.commons.utils.UUIDUtils;
import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.service.UserService;
import com.bjpowernode.crm.workbench.domain.Activity;
import com.bjpowernode.crm.workbench.service.ActivityService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/workbench/activity")
public class ActivityController {
    @Resource
    private UserService userService;
    @Resource
    private ActivityService activityService;
@RequestMapping("index.do")
    public ModelAndView index(){
    ModelAndView mv = new ModelAndView();
    mv.addObject("users",userService.queryAllUsers());
    mv.setViewName("/workbench/activity/index");
    return mv;
    }
    @RequestMapping("save.do")
    @ResponseBody
    public Object save(Activity a, HttpSession session){
    //添加createBy createTime，id
        User createUser = (User)session.getAttribute(Constants.SESSION_USER);
        a.setId(UUIDUtils.getUUID());
        a.setCreateTime(DateUtils.formatDateTime(new Date()));
        a.setCreateBy(createUser.getId());
        ReturnObject ro = new ReturnObject();
    try {
        int count = activityService.saveCreateActivity(a);
        if(count > 0){
            ro.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
        }else {
            ro.setCode(Constants.RETURN_OBJECT_CODE_FAILED);
            ro.setMsg("插入失败");
        }
    }catch (Exception e){
        ro.setCode(Constants.RETURN_OBJECT_CODE_FAILED);
        ro.setMsg("插入失败");
    }
    return  ro;

    }

}
