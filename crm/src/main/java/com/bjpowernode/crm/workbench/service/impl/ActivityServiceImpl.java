package com.bjpowernode.crm.workbench.service.impl;

import com.bjpowernode.crm.workbench.domain.Activity;
import com.bjpowernode.crm.workbench.mapper.ActivityMapper;
import com.bjpowernode.crm.workbench.service.ActivityService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ActivityServiceImpl implements ActivityService {
    @Resource
    private ActivityMapper activityMapper;
    @Override
    public int saveCreateActivity(Activity activity) {
        return activityMapper.insertActivity(activity);
    }

    @Override
    public List<Activity> queryActivityList(Map<String, Object> map) {
        List<Activity> activityList = activityMapper.selectActivityList(map);
        return activityList;
    }

    @Override
    public int selectTotalOfActivityByCondition(Map<String, Object> map) {
        return activityMapper.selectTotalOfActivityByCondition(map);
    }

    @Override
    public int deleteActivityByIds(String[] ids) {
        return activityMapper.deleteActivityByIds(ids);
    }

    @Override
    public Activity selectActivityById(String id) {
        return  activityMapper.selectActivityById(id);
    }

    @Override
    public int updateActivity(Activity activity) {
        return activityMapper.updateActivity(activity);
    }

    @Override
    public List<Activity> selectActivities() {
       return activityMapper.getActivities();
    }

    @Override
    public int saveActivityByList(List<Activity> activityList) {
        return activityMapper.insertActivityByList(activityList);
    }

    @Override
    public Activity selectActivityDetailById(String id) {
        return  activityMapper.selectActivityDetailById(id);
    }

}
