package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.ReportBookingRegistrationDao;
import com.yly.entity.ReportBookingRegistration;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * DaoImpl - 预约登记统计报表
 * @author yohu
 *
 */
@Repository("reportBookingRegistrationDaoImpl")
public class ReportBookingRegistrationDaoImpl extends BaseDaoImpl<ReportBookingRegistration, Long> implements ReportBookingRegistrationDao {

}
