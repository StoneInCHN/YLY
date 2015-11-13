package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.NotificationDao;
import com.yly.entity.Notification;
import com.yly.framework.dao.impl.BaseDaoImpl;

@Repository("notificationDaoImpl")
public class NotificationDaoImpl extends BaseDaoImpl<Notification, Long> implements NotificationDao {

}
