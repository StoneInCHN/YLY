package com.yly.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.yly.common.log.LogUtil;
import com.yly.dao.BookingRegistrationDao;
import com.yly.entity.BookingRegistration;
import com.yly.entity.commonenum.CommonEnum.Gender;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.json.request.BookingRegistrationSearchRequest;
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

    QueryParser peopleWhoBookedParser =
        new QueryParser(Version.LUCENE_35, "peopleWhoBooked", analyzer);
    QueryParser elderlyNameParser = new QueryParser(Version.LUCENE_35, "elderlyName", analyzer);
    QueryParser intentRoomTypeParser =
        new QueryParser(Version.LUCENE_35, "intentRoomType.configValue", analyzer);
    QueryParser tenantParser = new QueryParser(Version.LUCENE_35, "tenantID", analyzer);

    Query tenantQuery = null;
    Query peopleWhoBookedQuery = null;
    Query elderlyNameQuery = null;
    Query intentRoomTypeQuery = null;
    Filter bookingCheckInDateFilter = null;

    try {
      tenantQuery = tenantParser.parse(tenantAccountService.getCurrentTenantID().toString());
      query.add(tenantQuery, Occur.MUST);
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
      String intentRoomTypeValue =
          QueryParser.escape(bookingRegistration.getIntentRoomType().getConfigValue());
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
    if (LogUtil.isDebugEnabled(BookingRegistrationServiceImpl.class)) {
      LogUtil
          .debug(
              BookingRegistrationServiceImpl.class,
              "searchBookingRegistration",
              "Searching booking and registration records with params,peopleWhoBooked=%s,elderlyName=%s,intentRoomType=%s,start bookingCheckInDate=%s,end bookingCheckInDate=%s",
              bookingRegistration.getPeopleWhoBooked(), bookingRegistration.getElderlyName(),
              bookingRegistration.getIntentRoomType(), bookingCheckInDateBeginDate,
              bookingCheckInDateEndDate);
    }

    return search(query, pageable, analyzer, bookingCheckInDateFilter);
  }
  /**
   * 根据搜索条件，返回查询结果List
   */
  @Override
  public List<BookingRegistration> searchListByFilter(
      BookingRegistrationSearchRequest bookingRegSearch) {
    IKAnalyzer analyzer = new IKAnalyzer();
    analyzer.setMaxWordLength(true);
    try {
      BooleanQuery query = getQuery(analyzer, bookingRegSearch);
      Filter returnVisitDateFilter = getFilter(bookingRegSearch);
      
      return bookingRegistrationDao.searchList(query, analyzer, returnVisitDateFilter);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
  /**
   * 根据搜索条件，返回查询结果个数
   */
  @Override
  public int countByFilter(BookingRegistrationSearchRequest bookingRegSearch) {
    IKAnalyzer analyzer = new IKAnalyzer();
    analyzer.setMaxWordLength(true);
    try {
      BooleanQuery query = getQuery(analyzer, bookingRegSearch);
      Filter returnVisitDateFilter = getFilter(bookingRegSearch);
      
      return bookingRegistrationDao.count(query, analyzer, returnVisitDateFilter);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    return 0;
  }
  /**
   * 根据查询条件中的 开始时间和结束时间，返回对预约入住时间的过滤 
   * @return
   */
  private Filter getFilter(BookingRegistrationSearchRequest bookingRegSearch) {
    
    Filter bookingCheckInDateFilter = null;
    Date bookingCheckInDateBeginDate = bookingRegSearch.getBookingCheckInBeginDateHidden();
    Date bookingCheckInDateEndDate = bookingRegSearch.getBookingCheckInEndDateHidden();
    if (bookingCheckInDateBeginDate != null || bookingCheckInDateEndDate != null) {

      bookingCheckInDateFilter =
          new TermRangeFilter("bookingCheckInDate", DateTimeUtils.convertDateToString(
              bookingCheckInDateBeginDate, null), DateTimeUtils.convertDateToString(
              bookingCheckInDateEndDate, null), true, true);
    }
    return bookingCheckInDateFilter;
  }
  /**
   * 根据查询条件中的 预约人，老人姓名，意向房型，返回带条件的查询Query
   * @return
   */
  private BooleanQuery getQuery(IKAnalyzer analyzer,
      BookingRegistrationSearchRequest bookingRegSearch) {

    try {
      BooleanQuery booleanQuery = new BooleanQuery();
      
      QueryParser queryParser = null;
      Query query = null;
      
      String peopleWhoBooked = bookingRegSearch.getPeopleWhoBookedHidden();
      String elderlyName = bookingRegSearch.getElderlyNameHidden();
      String searchRoomTypeValue = bookingRegSearch.getSearchRoomTypeValueHidden();

      try {
        queryParser = new QueryParser(Version.LUCENE_35, "tenantID", analyzer);
        query = queryParser.parse(tenantAccountService.getCurrentTenantID().toString());
        booleanQuery.add(query, Occur.MUST);
      } catch (ParseException e1) {
        e1.printStackTrace();
      }
      if (searchRoomTypeValue != null) {
        try {
          queryParser = new QueryParser(Version.LUCENE_35, "intentRoomType.configValue", analyzer);
          query = queryParser.parse(QueryParser.escape(searchRoomTypeValue));
        } catch (ParseException e) {
          e.printStackTrace();
        }
        booleanQuery.add(query, Occur.MUST);
      }
      
      if (peopleWhoBooked != null) {
        try {
          queryParser = new QueryParser(Version.LUCENE_35, "peopleWhoBooked", analyzer);
          query = queryParser.parse(QueryParser.escape(peopleWhoBooked));
        } catch (ParseException e) {
          e.printStackTrace();
        }
        booleanQuery.add(query, Occur.MUST);
      }

      if (elderlyName != null) {
        try {
          queryParser = new QueryParser(Version.LUCENE_35, "elderlyName", analyzer);
          query = queryParser.parse(QueryParser.escape(elderlyName));
        } catch (ParseException e) {
          e.printStackTrace();
        }
        booleanQuery.add(query, Occur.MUST);
      }
      return booleanQuery;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  
  }

  /**
   * 准备数据，将list转化成HashMap,作为需要导出的数据
   * @param consultationList
   * @return
   */
  @Override
  public List<Map<String, String>> prepareMap(List<BookingRegistration> bookingRegistrationList) {
    List<Map<String, String>> mapList = new ArrayList<Map<String,String>>();
    for (BookingRegistration bookingReg : bookingRegistrationList) {
      Map<String, String> bookingRegMap = new HashMap<String, String>();
      
      bookingRegMap.put("peopleWhoBooked", bookingReg.getPeopleWhoBooked());
      bookingRegMap.put("elderlyName", bookingReg.getElderlyName());
      bookingRegMap.put("phoneNumber", bookingReg.getPhoneNumber());
      bookingRegMap.put("bookingCheckInDate", DateTimeUtils.getSimpleFormatString(DateTimeUtils.shortDateFormat, bookingReg.getBookingCheckInDate()));
      bookingRegMap.put("IDCard", bookingReg.getIDCard());
      bookingRegMap.put("intentRoomType", bookingReg.getIntentRoomType()!=null?bookingReg.getIntentRoomType().getConfigValue():"");
      if(bookingReg.getGender() == Gender.MALE) {
        bookingRegMap.put("gender", "男");
      } else if(bookingReg.getGender() == Gender.FEMALE) {
        bookingRegMap.put("gender", "女");
      }
      bookingRegMap.put("remark", bookingReg.getRemark());
      mapList.add(bookingRegMap);
    }
    return mapList;
  
  }

}
