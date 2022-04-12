package com.bjpowernode.crm.workbench.web.controller;

import com.bjpowernode.crm.commons.constants.Constants;
import com.bjpowernode.crm.commons.domain.ReturnObject;
import com.bjpowernode.crm.commons.utils.DateUtils;
import com.bjpowernode.crm.commons.utils.UUIDUtils;
import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.workbench.domain.ActivityRemark;
import com.bjpowernode.crm.workbench.service.ActivityRemarkService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/workbench/activity/")
public class ActivityRemarkController {
    @Resource
    private ActivityRemarkService activityRemarkService;
    @RequestMapping("saveActivityRemark.do")
    @ResponseBody
    public Object saveActivityRemark(ActivityRemark remark, HttpSession session){
        ReturnObject ro = new ReturnObject();
        //封装参数
        remark.setId(UUIDUtils.getUUID());
        remark.setCreateTime(DateUtils.formatDateTime(new Date()));
        remark.setCreateBy(((User)session.getAttribute(Constants.SESSION_USER)).getId());
        remark.setEditFlag(Constants.REMARK_EDIT_FLAG_NO_EDITED);
        try {
            int flag = activityRemarkService.saveActivityRemark(remark);
            if(flag > 0){
                ro.setCode(Constants.RETURN_OBJECT_CODE_SUCCESS);
                ro.setRetData(remark);
            }else{
                ro.setCode(Constants.RETURN_OBJECT_CODE_FAILED);
                ro.setMsg("保存失败了");
            }
        }catch (Exception e){
            ro.setCode(Constants.RETURN_OBJECT_CODE_FAILED);
            System.out.println(e.getMessage());
            ro.setMsg("保存失败了");
        }
        return  ro;
    }
}
