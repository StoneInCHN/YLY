package com.yly.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermRangeFilter;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.yly.dao.BookingRegistrationDao;
import com.yly.entity.BookingRegistration;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.BookingRegistrationService;
import com.yly.utils.DateTimeUtils;

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

  public Page<BookingRegistration> searchBookingRegistration(Date bookingCheckInDateBeginDate,
      Date bookingCheckInDateEndDate, BookingRegistration bookingRegistration,
      Long searchRoomTypeId, Pageable pageable) {
    


    IKAnalyzer analyzer = new IKAnalyzer();
    analyzer.setMaxWordLength(true);
    BooleanQuery query = new BooleanQuery();

    QueryParser peopleWhoBookedParser = new QueryParser(Version.LUCENE_35, "peopleWhoBooked", analyzer);
    QueryParser elderlyNameParser = new QueryParser(Version.LUCENE_35, "elderlyName", analyzer);
    QueryParser intentRoomTypeParser = new QueryParser(Version.LUCENE_35, "intentRoomType.configValue", analyzer);
    QueryParser tenantParser = new QueryParser(Version.LUCENE_35, "tenantID", analyzer);

    Query tenantQuery = null;
    Query peopleWhoBookedQuery = null;
    Query elderlyNameQuery = null;
    Query intentRoomTypeQuery = null;
    Filter bookingCheckInDateFilter = null;
    
    try {
      tenantQuery = tenantParser.parse(tenantAccountService.getCurrentTenantID().toString());
      query.add(tenantQuery,Occur.MUST);
    } catch (ParseException e1) {
      e1.printStackTrace();
    }

    if (bookingRegistration.getPeopleWhoBooked() != null) {
      String peopleWhoBookedName = QueryParser.escape(bookingRegistration.getPeopleWhoBooked());
      try {
        peopleWhoBookedQuery = peopleWhoBookedParser.parse(peopleWhoBookedName);
      } catch (ParseException e) {
        e.printStackTrace();
      }
      query.add(peopleWhoBookedQuery, Occur.MUST);
    }

    if (bookingRegistration.getElderlyName() != null) {
      String elderlyName = QueryParser.escape(bookingRegistration.getElderlyName());
      try {
        elderlyNameQuery = elderlyNameParser.parse(elderlyName);
      } catch (ParseException e) {
        e.printStackTrace();
      }
      query.add(elderlyNameQuery, Occur.MUST);
    }

    if (bookingRegistration.getIntentRoomType() != null) {
      String intentRoomTypeValue = QueryParser.escape(bookingRegistration.getIntentRoomType().getConfigValue());
      try {
        intentRoomTypeQuery = intentRoomTypeParser.parse(intentRoomTypeValue);
      } catch (ParseException e) {
        e.printStackTrace();
      }
      query.add(intentRoomTypeQuery, Occur.MUST);
    }

    if (bookingCheckInDateBeginDate != null || bookingCheckInDateEndDate != null) {

      bookingCheckInDateFilter =
          new TermRangeFilter("bookingCheckInDate", DateTimeUtils.convertDateToString(
              bookingCheckInDateBeginDate, null), DateTimeUtils.convertDateToString(
                  bookingCheckInDateEndDate, null), true, true);
    }
    return search(query, pageable, analyzer, bookingCheckInDateFilter);
  }
  
}
