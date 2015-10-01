package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.BookingRegistrationDao;
import com.yly.entity.BookingRegistration;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * 预约登记
 * 
 * @author Administrator
 *
 */
@Repository("bookingRegistrationDaoImpl")
public class BookingRegistrationDaoImpl extends BaseDaoImpl<BookingRegistration,Long> implements BookingRegistrationDao{

}
