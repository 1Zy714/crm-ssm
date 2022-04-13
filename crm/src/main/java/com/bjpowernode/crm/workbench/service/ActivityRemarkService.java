package com.bjpowernode.crm.workbench.service;

import com.bjpowernode.crm.workbench.domain.ActivityRemark;

import java.util.List;

public interface ActivityRemarkService {
List<ActivityRemark> queryActivityForDetail(String activityId);
int saveActivityRemark(ActivityRemark remark);
int deleteActivityRemarkById(String id);
int updateActivityRemark(ActivityRemark remark);
}
