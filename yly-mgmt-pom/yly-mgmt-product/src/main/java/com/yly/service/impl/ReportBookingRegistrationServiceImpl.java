package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.ReportBookingRegistrationDao;
import com.yly.entity.ReportBookingRegistration;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.ReportBookingRegistrationService;

/**
 * 
 * @author yohu
 *
 */
@Service ("reportBookingRegistrationServiceImpl")
public class ReportBookingRegistrationServiceImpl extends
    BaseServiceImpl<ReportBookingRegistration, Long> implements
    ReportBookingRegistrationService
{

  @Resource (name = "reportBookingRegistrationDaoImpl")
  private void setBaseDao (ReportBookingRegistrationDao reportBookingRegistrationDao)
  {
    super.setBaseDao (reportBookingRegistrationDao);
  }
}
