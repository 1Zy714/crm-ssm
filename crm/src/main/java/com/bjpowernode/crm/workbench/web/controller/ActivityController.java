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
import java.util.*;

@Controller
@RequestMapping("/workbench/activity")
public class ActivityController {
    @Resource
    private UserService userService;
    @Resource
    private ActivityService activityService;

    @RequestMapping("index.do")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("users", userService.queryAllUsers());
        mv.setViewName("/workbench/activity/index");
        return mv;
    }

    @RequestMapping("save.do")
    @ResponseBody
    public Object save(Activity a, HttpSession session) {
        //添加createBy createTime，id
        User createUser = (User) session.getAttribute(Constants.SESSION_USER);
        a.setId(UUIDUtils.getUUID());
        a.setCreateTime(DateUtils.formatDateTime(new Date()));
        a.setCreateBy(createUser.getId());
        ReturnObject ro = new ReturnObject();
        try {
            int count = activityService.saveCreateActivity(a);
            if (count > 0) {
                ro.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
            } else {
                ro.setCode(Constants.RETURN_OBJECT_CODE_FAILED);
                ro.setMsg("插入失败");
            }
        } catch (Exception e) {
            ro.setCode(Constants.RETURN_OBJECT_CODE_FAILED);
            ro.setMsg("插入失败");
        }
        return ro;

    }

    @RequestMapping("pageList.do")
    @ResponseBody
    public Object queryActivityByConditionForPage(String name, String owner, String startDate,
                                                  String endDate, int pageNo, int pageSize) {
        //将传递过来的数据封装map
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("owner", owner);
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        map.put("beginNo", (pageNo - 1) * pageSize);
        map.put("pageSize", pageSize);
        //调用service方法
        List<Activity> activityList = activityService.queryActivityList(map);
        System.out.println(activityList);

        int total = activityService.selectTotalOfActivityByCondition(map);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("totalRows", total);
        resultMap.put("activityList", activityList);
        return resultMap;

    }

    @RequestMapping("delete.do")
    @ResponseBody
    public Object deleteActivityByIds(String[] id) {
        ReturnObject ro = new ReturnObject();
        try {
            int count = activityService.deleteActivityByIds(id);
            if (count == id.length)
                ro.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
            else {
                ro.setCode(Constants.RETURN_OBJECT_CODE_FAILED);
                ro.setCode("删除失败");
            }
        } catch (Exception e) {
            ro.setCode(Constants.RETURN_OBJECT_CODE_FAILED);
            ro.setCode("删除失败");
        }
        return ro;
    }
}
