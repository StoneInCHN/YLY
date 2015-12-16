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
import com.yly.dao.VisitElderlyRecordDao;
import com.yly.entity.VisitElderlyRecord;
import com.yly.entity.commonenum.CommonEnum.Relation;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.VisitElderlyRecordService;
import com.yly.utils.DateTimeUtils;

/**
 * 老人探望
 * 
 * @author shijun
 *
 */
@Service("visitElderlyRecordServiceImpl")
public class VisitElderlyRecordServiceImpl extends BaseServiceImpl<VisitElderlyRecord, Long>
    implements VisitElderlyRecordService {

  @Resource(name = "visitElderlyRecordDaoImpl")
  private VisitElderlyRecordDao visitElderlyRecordDao;

  @Resource
  public void setBaseDao(VisitElderlyRecordDao visitElderlyRecordDao) {
    super.setBaseDao(visitElderlyRecordDao);
  }

  /**
   * 查询访问记录
   */
  public Page<VisitElderlyRecord> searchElderlyRecord(Date visitDateBeginDate,
      Date visitDateEndDate, VisitElderlyRecord visitElderlyRecord, Pageable pageable) {

    IKAnalyzer analyzer = new IKAnalyzer();
    analyzer.setMaxWordLength(true);
    
    BooleanQuery query = new BooleanQuery();

    QueryParser visitorParser = new QueryParser(Version.LUCENE_35, "visitor", analyzer);
    QueryParser elderlyNameParser = new QueryParser(Version.LUCENE_35, "elderlyInfo.name", analyzer);
    QueryParser tenantParser = new QueryParser(Version.LUCENE_35, "tenantID", analyzer);

    Query tenantQuery = null;
    Query visitorQuery = null;
    Query elderlyNameQuery = null;
    Filter visitDateFilter = null;

    try {
      tenantQuery = tenantParser.parse(tenantAccountService.getCurrentTenantID().toString());
      query.add(tenantQuery, Occur.MUST);
    } catch (ParseException e1) {
      e1.printStackTrace();
    }

    if (visitElderlyRecord.getVisitor() != null) {
      String visitorName = QueryParser.escape(visitElderlyRecord.getVisitor());
      try {
        visitorQuery = visitorParser.parse(visitorName);
      } catch (ParseException e) {
        e.printStackTrace();
      }
      query.add(visitorQuery, Occur.MUST);
    }

    if (visitElderlyRecord.getElderlyInfo().getName() != null) {
      String elderlyName = QueryParser.escape(visitElderlyRecord.getElderlyInfo().getName());
      try {
        elderlyNameQuery = elderlyNameParser.parse(elderlyName);
      } catch (ParseException e) {
        e.printStackTrace();
      }
      query.add(elderlyNameQuery, Occur.MUST);
    }

    if (visitDateBeginDate != null || visitDateEndDate != null) {

      visitDateFilter =
          new TermRangeFilter("visitDate", DateTimeUtils.convertDateToString(visitDateBeginDate,
              null), DateTimeUtils.convertDateToString(visitDateEndDate, null), true, true);
    }

    if (LogUtil.isDebugEnabled(VisitElderlyRecordServiceImpl.class)) {
      LogUtil
          .debug(
              VisitElderlyRecordServiceImpl.class,
              "searchElderlyRecord",
              "Searching visiting records with params,tenant ID=%s,visitor=%s,elderly=%s,start visitdate=%s,end visitdate=%s",tenantAccountService.getCurrentTenantID(),
              visitElderlyRecord.getVisitor(), visitElderlyRecord.getElderlyInfo().getName(),
              visitDateBeginDate, visitDateEndDate);
    }

    return search(query, pageable, analyzer, visitDateFilter);
  }


  /**
   * 根据搜索条件，返回查询结果个数
   */
  @Override
  public int countByFilter(String elderlyName, String vistor,
      Date visitDateBeginDate, Date visitDateEndDate) {
    IKAnalyzer analyzer = new IKAnalyzer();
    analyzer.setMaxWordLength(true);
    try {
      BooleanQuery booleanQuery = getQuery(analyzer, vistor, elderlyName);
      Filter returnVisitDateFilter = getFilter(visitDateBeginDate, visitDateEndDate);
      
      return visitElderlyRecordDao.count(booleanQuery, analyzer, returnVisitDateFilter);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    return 0;
  }


  /**
   * 根据搜索条件，返回查询结果List
   */
  @Override
  public List<VisitElderlyRecord> searchListByFilter(String elderlyName, String vistor,
      Date visitDateBeginDate, Date visitDateEndDate) {
    IKAnalyzer analyzer = new IKAnalyzer();
    analyzer.setMaxWordLength(true);
    try {
      BooleanQuery query = getQuery(analyzer, vistor, elderlyName);
      Filter visitDateFilter = getFilter(visitDateBeginDate, visitDateEndDate);
      
      return visitElderlyRecordDao.searchList(query, analyzer, visitDateFilter);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
  /**
  * 根据查询条件中的 开始时间和结束时间，返回对来访时间的过滤 
  * @param visitDateBeginDate
  * @param visitDateEndDate
  * @return
  */
  private Filter getFilter(Date visitDateBeginDate, Date visitDateEndDate) {
    Filter visitDateFilter = null;
    if (visitDateBeginDate != null || visitDateEndDate != null) {
      visitDateFilter =
          new TermRangeFilter("visitDate", DateTimeUtils.convertDateToString(visitDateBeginDate,
              null), DateTimeUtils.convertDateToString(visitDateEndDate, null), true, true);
    }
    return visitDateFilter;
  }
  /**
  * 根据查询条件中的 被探望老人，来访者，返回带条件的查询Query
  * @param analyzer
  * @param consultationRecord
  * @return
  */
  private BooleanQuery getQuery(IKAnalyzer analyzer, String elderlyName, String vistor){
    BooleanQuery booleanQuery = new BooleanQuery();
    QueryParser queryParser = null;
    Query query = null;
    
    try {
      queryParser = new QueryParser(Version.LUCENE_35, "tenantID", analyzer);
      query = queryParser.parse(tenantAccountService.getCurrentTenantID().toString());
      booleanQuery.add(query, Occur.MUST);
    } catch (ParseException e1) {
      e1.printStackTrace();
    }

    if (vistor != null) {
      try {
        queryParser = new QueryParser(Version.LUCENE_35, "visitor", analyzer);
        query = queryParser.parse(QueryParser.escape(vistor));
      } catch (ParseException e) {
        e.printStackTrace();
      }
      booleanQuery.add(query, Occur.MUST);
    }

    if (elderlyName != null) {
      try {
        queryParser = new QueryParser(Version.LUCENE_35, "elderlyInfo.name", analyzer);
        query = queryParser.parse(QueryParser.escape(elderlyName));
      } catch (ParseException e) {
        e.printStackTrace();
      }
      booleanQuery.add(query, Occur.MUST);
    }
    return booleanQuery;
  }
  /**
   * 准备数据，将list转化成HashMap,作为需要导出的数据
   * @return
   */
  @Override
  public List<Map<String, String>> prepareMap(List<VisitElderlyRecord> visitElderlyRecordList) {
    List<Map<String, String>> mapList = new ArrayList<Map<String,String>>();
    for (VisitElderlyRecord visitElderlyRecord : visitElderlyRecordList) {
      Map<String, String> visitElderlyMap = new HashMap<String, String>();
      visitElderlyMap.put("elderlyInfo.name", visitElderlyRecord.getElderlyInfo()!=null?visitElderlyRecord.getElderlyInfo().getName():"");
      visitElderlyMap.put("visitor", visitElderlyRecord.getVisitor());
      visitElderlyMap.put("IDCard", visitElderlyRecord.getIDCard());
      visitElderlyMap.put("phoneNumber", visitElderlyRecord.getPhoneNumber());
      visitElderlyMap.put("visitPersonnelNumber", visitElderlyRecord.getVisitPersonnelNumber().toString());      
      if(visitElderlyRecord.getRelation() == Relation.SELF){
        visitElderlyMap.put("relation", "本人");
      }else if(visitElderlyRecord.getRelation() == Relation.CHILDREN){
        visitElderlyMap.put("relation", "子女");
      }else if(visitElderlyRecord.getRelation() == Relation.MARRIAGE_RELATIONSHIP){
        visitElderlyMap.put("relation", "夫妻");
      }else if(visitElderlyRecord.getRelation() == Relation.GRANDPARENTS_AND_GRANDCHILDREN){
        visitElderlyMap.put("relation", "祖孙关系");
      }else if(visitElderlyRecord.getRelation() == Relation.BROTHERS_OR_SISTERS){
        visitElderlyMap.put("relation", "兄弟或姐妹");
      }else if(visitElderlyRecord.getRelation() == Relation.DAUGHTERINLAW_OR_SONINLAW){
        visitElderlyMap.put("relation", "儿媳或女婿");
      }else if(visitElderlyRecord.getRelation() == Relation.FRIEND){
        visitElderlyMap.put("relation", "朋友");
      }else if(visitElderlyRecord.getRelation() == Relation.OTHER){
        visitElderlyMap.put("relation", "其它");
      }
      visitElderlyMap.put("visitDate", DateTimeUtils.getSimpleFormatString(DateTimeUtils.shortDateFormat, visitElderlyRecord.getVisitDate()));
      visitElderlyMap.put("dueLeaveDate", DateTimeUtils.getSimpleFormatString(DateTimeUtils.shortDateFormat, visitElderlyRecord.getDueLeaveDate()));
      visitElderlyMap.put("reasonForVisit", visitElderlyRecord.getReasonForVisit());
      visitElderlyMap.put("remark", visitElderlyRecord.getRemark());
      
      mapList.add(visitElderlyMap);
    }
    return mapList;
  }
}
