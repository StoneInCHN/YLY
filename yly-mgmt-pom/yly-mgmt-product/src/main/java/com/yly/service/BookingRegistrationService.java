package com.yly.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yly.entity.BookingRegistration;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.framework.service.BaseService;
import com.yly.json.request.BookingRegistrationSearchRequest;

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

  public List<BookingRegistration> searchListByFilter(BookingRegistrationSearchRequest bookingRegSearch);

  public int countByFilter(BookingRegistrationSearchRequest bookingRegSearch);

  public List<Map<String, String>> prepareMap(List<BookingRegistration> bookingRegistrationList);
}
