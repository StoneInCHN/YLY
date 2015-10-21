package com.yly.service;

import java.util.Date;

import com.yly.entity.BookingRegistration;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.framework.service.BaseService;

/**
 * 预约登记
 * 
 * @author Administrator
 *
 */
public interface BookingRegistrationService extends BaseService<BookingRegistration, Long> {

  public Page<BookingRegistration> searchBookingRegistration(Date bookingCheckInDateBeginDate,
      Date bookingCheckInDateEndDate, BookingRegistration bookingRegistration,
      Long searchRoomTypeId, Pageable pageable);
}
