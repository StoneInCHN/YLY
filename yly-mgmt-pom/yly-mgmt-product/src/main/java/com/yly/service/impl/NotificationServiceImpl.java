package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.NotificationDao;
import com.yly.entity.Notification;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.NotificationService;

@Service("notificationServiceImpl")
public class NotificationServiceImpl extends BaseServiceImpl<Notification, Long> implements NotificationService {

  @Resource(name = "notificationDaoImpl")
  private NotificationDao notificationDao;
  
  @Resource
  public void setBaseDao(NotificationDao notificationDao) {
    super.setBaseDao(notificationDao);
  }

}
