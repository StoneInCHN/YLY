package com.yly.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TermRangeFilter;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.yly.common.log.LogUtil;
import com.yly.dao.ElderlyInfoDao;
import com.yly.entity.ElderlyInfo;
import com.yly.entity.commonenum.CommonEnum.ElderlyStatus;
import com.yly.entity.commonenum.CommonEnum.Gender;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.json.request.ElderlyInfoSearchRequest;
import com.yly.service.ElderlyInfoService;
import com.yly.utils.DateTimeUtils;

/**
 * 老人信息
 * 
 * @author shijun
 *
 */
@Service("elderlyInfoServiceImpl")
public class ElderlyInfoServiceImpl extends BaseServiceImpl<ElderlyInfo, Long> implements
    ElderlyInfoService {

  @Resource(name = "elderlyInfoDaoImpl")
  private ElderlyInfoDao elderlyInfoDao;

  @Resource
  public void setBaseDao(ElderlyInfoDao elderlyInfoDao) {
    super.setBaseDao(elderlyInfoDao);
  }

  public Page<ElderlyInfo> searchElderlyInfo(Date beHospitalizedBeginDate,
      Date beHospitalizedEndDate, ElderlyInfo elderlyInfo, Pageable pageable) {


    IKAnalyzer analyzer = new IKAnalyzer();
    analyzer.setMaxWordLength(true);
    BooleanQuery query = new BooleanQuery();

    QueryParser identifierParser = new QueryParser(Version.LUCENE_35, "identifier", analyzer);
    QueryParser elderlyNameParser = new QueryParser(Version.LUCENE_35, "name", analyzer);
    QueryParser tenantParser = new QueryParser(Version.LUCENE_35, "tenantID", analyzer);

    Query tenantQuery = null;
    Query identifierQuery = null;
    Query elderlyNameQuery = null;
    Query elderlyStatusQuery = null;
    Query deleteStatusQuery = null;
    Filter beHospitalizedDateFilter = null;

    try {
      tenantQuery = tenantParser.parse(tenantAccountService.getCurrentTenantID().toString());
      query.add(tenantQuery, Occur.MUST);
    } catch (ParseException e1) {
      e1.printStackTrace();
    }

    if (elderlyInfo.getName() != null) {
      String elderlyName = QueryParser.escape(elderlyInfo.getName());
      try {
        elderlyNameQuery = elderlyNameParser.parse(elderlyName);
      } catch (ParseException e) {
        e.printStackTrace();
      }
      query.add(elderlyNameQuery, Occur.MUST);
    }

    if (elderlyInfo.getIdentifier() != null) {
      String identifier = QueryParser.escape(elderlyInfo.getIdentifier());
      try {
        identifierQuery = identifierParser.parse(identifier);
      } catch (ParseException e) {
        e.printStackTrace();
      }
      query.add(identifierQuery, Occur.MUST);
    }

    if (elderlyInfo.getElderlyStatus() != null) {
        Term elderlyStatusTerm = new Term("elderlyStatus", elderlyInfo.getElderlyStatus().toString());
        elderlyStatusQuery = new TermQuery(elderlyStatusTerm);
        query.add(elderlyStatusQuery, Occur.MUST);
    }

    if (elderlyInfo.getDeleteStatus() != null) {
        Term deleteStatusTerm = new Term("deleteStatus", elderlyInfo.getDeleteStatus().toString());
        deleteStatusQuery = new TermQuery(deleteStatusTerm);
        query.add(deleteStatusQuery, Occur.MUST);
    }

    if (beHospitalizedBeginDate != null || beHospitalizedEndDate != null) {

      beHospitalizedDateFilter =
          new TermRangeFilter("beHospitalizedDate", DateTimeUtils.convertDateToString(
              beHospitalizedBeginDate, null), DateTimeUtils.convertDateToString(
              beHospitalizedEndDate, null), true, true);
    }

    if (LogUtil.isDebugEnabled(ConsultationServiceImpl.class)) {
      LogUtil
          .debug(
              ConsultationServiceImpl.class,
              "searchElderlyInfo",
              "Searching Elderly records with params,tenant ID=%s,identifier=%s,elderly name=%s,elderlyStatus=%s,start beHospitalizedDate=%s,end beHospitalizedDate=%s",
              tenantAccountService.getCurrentTenantID(), elderlyInfo.getIdentifier(),
              elderlyInfo.getName(), elderlyInfo.getElderlyStatus(), beHospitalizedBeginDate,
              beHospitalizedEndDate);
    }
    return search(query, pageable, analyzer, beHospitalizedDateFilter);

  }

  @Override
  @Transactional(readOnly = true)
  public List<ElderlyInfo> findByElderlyName(String nameKeys) {
    List<com.yly.framework.filter.Filter> filters = new ArrayList<com.yly.framework.filter.Filter>();
    if (StringUtils.isNotBlank(nameKeys)) {
      com.yly.framework.filter.Filter nameFilter =  new com.yly.framework.filter.Filter("name", com.yly.framework.filter.Filter.Operator.like, "%"+nameKeys+"%");
      filters.add(nameFilter);
    }
    return findList(null, null, filters, null);
  }
  

  /**
   * 根据搜索条件，返回查询结果个数
   */
  @Override
  public int countByFilter(ElderlyInfoSearchRequest elderlyInfoSearch) {
    IKAnalyzer analyzer = new IKAnalyzer();
    analyzer.setMaxWordLength(true);
    try {
      BooleanQuery query = getQuery(analyzer, elderlyInfoSearch);
      Filter beHospitalizedDateFilter = getFilter(elderlyInfoSearch);
      
      return elderlyInfoDao.count(query, analyzer, beHospitalizedDateFilter);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    return 0;
  }
  /**
   * 根据查询条件中的 开始时间和结束时间，返回对入院时间的过滤 
   * @return
   */
  private Filter getFilter(ElderlyInfoSearchRequest elderlyInfoSearch) {
    Filter beHospitalizedDateFilter = null;
    Date beHospitalizedBeginDate = elderlyInfoSearch.getBeHospitalizedBeginDateHiden();
    Date beHospitalizedEndDate = elderlyInfoSearch.getBeHospitalizedEndDateHidden();
    if (beHospitalizedBeginDate != null || beHospitalizedEndDate != null) {

      beHospitalizedDateFilter =
          new TermRangeFilter("beHospitalizedDate", DateTimeUtils.convertDateToString(
              beHospitalizedBeginDate, null), DateTimeUtils.convertDateToString(
              beHospitalizedEndDate, null), true, true);
    }
    return beHospitalizedDateFilter;
  }
  /**
   * 根据查询条件中的 老人编号，老人姓名，老人状态，返回带条件的查询Query
   * @return
   */
  private BooleanQuery getQuery(IKAnalyzer analyzer, ElderlyInfoSearchRequest elderlyInfoSearch) {

    try {
      BooleanQuery booleanQuery = new BooleanQuery();
      
      QueryParser queryParser = null;
      Query query = null;

      TermQuery termQuery = null;
      Term term = null;
      String identifier = elderlyInfoSearch.getIdentifierHidden();
      String name = elderlyInfoSearch.getNameHidden();
      String elderlyStatus = elderlyInfoSearch.getElderlyStatusHidden();
      String deleteStatus = elderlyInfoSearch.getDeleteStatus();
      try {
        queryParser = new QueryParser(Version.LUCENE_35, "tenantID", analyzer);
        query = queryParser.parse(tenantAccountService.getCurrentTenantID().toString());
        booleanQuery.add(query, Occur.MUST);
      } catch (ParseException e1) {
        e1.printStackTrace();
      }
      if (identifier != null) {
        try {
          queryParser = new QueryParser(Version.LUCENE_35, "identifier", analyzer);
          query = queryParser.parse(QueryParser.escape(identifier));
        } catch (ParseException e) {
          e.printStackTrace();
        }
        booleanQuery.add(query, Occur.MUST);
      }
      if (name != null) {
        try {
          queryParser = new QueryParser(Version.LUCENE_35, "name", analyzer);
          query = queryParser.parse(QueryParser.escape(name));
        } catch (ParseException e) {
          e.printStackTrace();
        }
        booleanQuery.add(query, Occur.MUST);
      }

      if (elderlyStatus != null) {
          term = new Term("elderlyStatus", elderlyStatus);
          termQuery = new TermQuery(term);
          booleanQuery.add(termQuery, Occur.MUST);
      }
      
      if (deleteStatus != null) {
          term = new Term("deleteStatus", deleteStatus);
          termQuery = new TermQuery(term);
          booleanQuery.add(termQuery, Occur.MUST);
      }
      return booleanQuery;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  
  
  }

  /**
   * 根据搜索条件，返回查询结果List
   */
  @Override
  public List<ElderlyInfo> searchListByFilter(ElderlyInfoSearchRequest elderlyInfoSearch) {
    IKAnalyzer analyzer = new IKAnalyzer();
    analyzer.setMaxWordLength(true);
    try {
      BooleanQuery query = getQuery(analyzer, elderlyInfoSearch);
      Filter returnVisitDateFilter = getFilter(elderlyInfoSearch);
      
      return elderlyInfoDao.searchList(query, analyzer, returnVisitDateFilter);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  
  /**
   * 准备数据，将list转化成HashMap,作为需要导出的数据
   * @return
   */
  @Override
  public List<Map<String, String>> prepareMap(List<ElderlyInfo> elderlyInfoList) {
    List<Map<String, String>> mapList = new ArrayList<Map<String,String>>();
    for (ElderlyInfo elderlyInfo : elderlyInfoList) {
      Map<String, String> elderlyInfoMap = new HashMap<String, String>();
      elderlyInfoMap.put("identifier", elderlyInfo.getIdentifier());
      elderlyInfoMap.put("name", elderlyInfo.getName());
      elderlyInfoMap.put("age", elderlyInfo.getAge()!=null?elderlyInfo.getAge().toString():"");
      elderlyInfoMap.put("elderlyPhoneNumber", elderlyInfo.getElderlyPhoneNumber());
      elderlyInfoMap.put("beHospitalizedDate", DateTimeUtils.getSimpleFormatString(DateTimeUtils.shortDateFormat, elderlyInfo.getBeHospitalizedDate()));
      if(elderlyInfo.getGender() == Gender.MALE) {
        elderlyInfoMap.put("gender", "男");
      } else if(elderlyInfo.getGender() == Gender.FEMALE) {
        elderlyInfoMap.put("gender", "女");
      }
      elderlyInfoMap.put("IDCard", elderlyInfo.getIDCard());
      elderlyInfoMap.put("bedLocation", elderlyInfo.getBedLocation());
      elderlyInfoMap.put("elderlyConsigner.consignerPhoneNumber", 
          elderlyInfo.getElderlyConsigner()!=null?elderlyInfo.getElderlyConsigner().getConsignerPhoneNumber():"");    
      if (elderlyInfo.getElderlyStatus() == ElderlyStatus.IN_NURSING_HOME) {
        elderlyInfoMap.put("elderlyStatus", "在院");
      }else if (elderlyInfo.getElderlyStatus() == ElderlyStatus.OUT_NURSING_HOME) {
        elderlyInfoMap.put("elderlyStatus", "出院");
      }else if (elderlyInfo.getElderlyStatus() == ElderlyStatus.IN_PROGRESS_CHECKIN) {
        elderlyInfoMap.put("elderlyStatus", "入院办理");
      }else if (elderlyInfo.getElderlyStatus() == ElderlyStatus.IN_PROGRESS_CHECKOUT) {
        elderlyInfoMap.put("elderlyStatus", "出院办理");
      }else if (elderlyInfo.getElderlyStatus() == ElderlyStatus.DEAD) {
        elderlyInfoMap.put("elderlyStatus", "过世");
      }else if (elderlyInfo.getElderlyStatus() == ElderlyStatus.IN_PROGRESS_CHECKIN_BILL) {
        elderlyInfoMap.put("elderlyStatus", "入院办理(已出账单未交费)");
      }else if (elderlyInfo.getElderlyStatus() == ElderlyStatus.IN_PROGRESS_EVALUATING) {
        elderlyInfoMap.put("elderlyStatus", "通过入院评估");
      }
      mapList.add(elderlyInfoMap);
    }
    return mapList;
  
  
  }

}
