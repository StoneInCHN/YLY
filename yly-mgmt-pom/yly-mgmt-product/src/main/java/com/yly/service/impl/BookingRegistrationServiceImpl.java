package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.BookingRegistrationDao;
import com.yly.entity.BookingRegistration;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.BookingRegistrationService;

/**
 * 预约登记
 * 
 * @author Administrator
 *
 */
@Service("bookingRegistrationServiceImpl")
public class BookingRegistrationServiceImpl extends BaseServiceImpl<BookingRegistration, Long>
    implements BookingRegistrationService {
  @Resource(name = "bookingRegistrationDaoImpl")
  private BookingRegistrationDao bookingRegistrationDao;

  @Resource
  public void setBaseDao(BookingRegistrationDao bookingRegistrationDao) {
    super.setBaseDao(bookingRegistrationDao);
  }
}
