package com.bjpowernode.crm.workbench.service;

import com.bjpowernode.crm.workbench.domain.Activity;

import java.util.List;
import java.util.Map;

public interface ActivityService {
    int saveCreateActivity(Activity activity);
    List<Activity> queryActivityList(Map<String,Object> map);
    int selectTotalOfActivityByCondition(Map<String,Object> map);
    int deleteActivityByIds(String[] ids);
    Activity selectActivityById(String id);
    int updateActivity(Activity activity);
}
