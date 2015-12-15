package com.yly.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.yly.common.log.LogUtil;
import com.yly.dao.ConsultationDao;
import com.yly.entity.ConsultationRecord;
import com.yly.entity.commonenum.CommonEnum.CheckinIntention;
import com.yly.entity.commonenum.CommonEnum.Gender;
import com.yly.entity.commonenum.CommonEnum.InfoChannel;
import com.yly.entity.commonenum.CommonEnum.Relation;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.json.request.ConsultationRecordSearchRequest;
import com.yly.service.ConsultationService;
import com.yly.service.TenantAccountService;
import com.yly.utils.DateTimeUtils;

/**
 * 咨询
 * 
 * @author shijun
 *
 */
@Service("consultationServiceImpl")
public class ConsultationServiceImpl extends BaseServiceImpl<ConsultationRecord, Long> implements
    ConsultationService {

  @Resource(name = "consultationDaoImpl")
  private ConsultationDao consultationDao;

  @Resource(name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;

  @Resource
  public void setBaseDao(ConsultationDao consultationDao) {
    super.setBaseDao(consultationDao);
  }

  public Page<ConsultationRecord> consultationSearch(Date returnVisitDateBeginDate,
      Date returnVisitDateEndDate, ConsultationRecord consultationRecord, Pageable pageable) {

    IKAnalyzer analyzer = new IKAnalyzer();
    analyzer.setMaxWordLength(true);

    BooleanQuery query = new BooleanQuery();

    QueryParser visitorParser = new QueryParser(Version.LUCENE_35, "visitor", analyzer);
    QueryParser elderlyNameParser = new QueryParser(Version.LUCENE_35, "elderlyName", analyzer);
    QueryParser tenantParser = new QueryParser(Version.LUCENE_35, "tenantID", analyzer);

    Query tenantQuery = null;
    Query visitorQuery = null;
    Query elderlyNameQuery = null;
    Query infoChannelQuery = null;
    Query checkinIntentionQuery = null;
    Filter returnVisitDateFilter = null;

    try {
      tenantQuery = tenantParser.parse(tenantAccountService.getCurrentTenantID().toString());
      query.add(tenantQuery, Occur.MUST);
    } catch (ParseException e1) {
      e1.printStackTrace();
    }

    if (consultationRecord.getVisitor() != null) {
      String visitorName = QueryParser.escape(consultationRecord.getVisitor());
      try {
        visitorQuery = visitorParser.parse(visitorName);
      } catch (ParseException e) {
        e.printStackTrace();
      }
      query.add(visitorQuery, Occur.MUST);
    }

    if (consultationRecord.getElderlyName() != null) {
      String elderlyName = QueryParser.escape(consultationRecord.getElderlyName());
      try {
        elderlyNameQuery = elderlyNameParser.parse(elderlyName);
      } catch (ParseException e) {
        e.printStackTrace();
      }
      query.add(elderlyNameQuery, Occur.MUST);
    }

    if (consultationRecord.getCheckinIntention() != null) {
      Term checkinIntentionTerm =
          new Term("checkinIntention", consultationRecord.getCheckinIntention().toString());
      checkinIntentionQuery = new TermQuery(checkinIntentionTerm);
      query.add(checkinIntentionQuery, Occur.MUST);
    }

    if (consultationRecord.getInfoChannel() != null) {
      Term infoChannelTerm =
          new Term("infoChannel", consultationRecord.getInfoChannel().toString());
      infoChannelQuery = new TermQuery(infoChannelTerm);
      query.add(infoChannelQuery, Occur.MUST);
    }

    if (returnVisitDateBeginDate != null || returnVisitDateEndDate != null) {

      returnVisitDateFilter =
          new TermRangeFilter("returnVisitDate", DateTimeUtils.convertDateToString(
              returnVisitDateBeginDate, null), DateTimeUtils.convertDateToString(
              returnVisitDateEndDate, null), true, true);
    }

    if (LogUtil.isDebugEnabled(ConsultationServiceImpl.class)) {
      LogUtil
          .debug(
              ConsultationServiceImpl.class,
              "consultationSearch",
              "Searching consultation records with params,tenant ID=%s,visitor=%s,elderly name=%s,infoChannel=%s,checkinIntention=%s,start returnVisitDate=%s,end returnVisitDate=%s",
              tenantAccountService.getCurrentTenantID(), consultationRecord.getVisitor(),
              consultationRecord.getElderlyName(), consultationRecord.getInfoChannel(),
              consultationRecord.getCheckinIntention(), returnVisitDateBeginDate,
              returnVisitDateEndDate);
    }
    return search(query, pageable, analyzer, returnVisitDateFilter);
  }

  @Override
  public int countByFilter(ConsultationRecordSearchRequest consultationSearch) {

    IKAnalyzer analyzer = new IKAnalyzer();
    analyzer.setMaxWordLength(true);
    try {
      BooleanQuery query = getQuery(analyzer, consultationSearch);
      Filter returnVisitDateFilter = getFilter(consultationSearch);
      
      return consultationDao.count(query, analyzer, returnVisitDateFilter);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    return 0;
  
  }

  @Override
  public List<ConsultationRecord> searchListByFilter(ConsultationRecordSearchRequest consultationSearch) {
    IKAnalyzer analyzer = new IKAnalyzer();
    analyzer.setMaxWordLength(true);
    try {
      BooleanQuery query = getQuery(analyzer, consultationSearch);
      Filter returnVisitDateFilter = getFilter(consultationSearch);
      
      return consultationDao.searchList(query, analyzer, returnVisitDateFilter);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
  /**
   * 根据查询条件中的 开始时间和结束时间，返回对回访时间的过滤 
   * @param returnVisitDateBeginDate
   * @param returnVisitDateEndDate
   * @return
   */
  private Filter getFilter(ConsultationRecordSearchRequest consultationSearch){
    Filter returnVisitDateFilter = null;
    Date returnVisitDateBeginDate = consultationSearch.getReturnVisitDateBeginDateHidden();
    Date returnVisitDateEndDate = consultationSearch.getReturnVisitDateEndDateHidden();
    if (returnVisitDateBeginDate != null || returnVisitDateEndDate != null) {
      returnVisitDateFilter =
          new TermRangeFilter("returnVisitDate", DateTimeUtils.convertDateToString(
              returnVisitDateBeginDate, null), DateTimeUtils.convertDateToString(
              returnVisitDateEndDate, null), true, true);
    }
    if (LogUtil.isDebugEnabled(ConsultationServiceImpl.class)) {
      LogUtil
          .debug(
              ConsultationServiceImpl.class,
              "consultationSearch",
              "Searching consultation records with params,tenant ID=%s,start returnVisitDate=%s,end returnVisitDate=%s",
              tenantAccountService.getCurrentTenantID(), returnVisitDateBeginDate,
              returnVisitDateEndDate);
    }
    return returnVisitDateFilter;
  }
  /**
   * 根据查询条件中的 咨询人，老人姓名，入住意向，信息来源，返回带条件的查询Query
   * @param analyzer
   * @param consultationRecord
   * @return
   */
  private BooleanQuery getQuery(IKAnalyzer analyzer, ConsultationRecordSearchRequest consultationSearch) {
    try {
      BooleanQuery booleanQuery = new BooleanQuery();
      
      QueryParser queryParser = null;
      Query query = null;
      
      TermQuery termQuery = null;
      Term term = null;
      
      String visitor = consultationSearch.getVisitorHidden();
      String elderlyName = consultationSearch.getElderlyNameHidden();
      String infoChannel = consultationSearch.getInfoChannelHidden();
      String checkinIntention = consultationSearch.getCheckinIntentionHidden();

      if (visitor != null) {
        try {
          queryParser = new QueryParser(Version.LUCENE_35, "visitor", analyzer);
          query = queryParser.parse(QueryParser.escape(visitor));
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

      if (checkinIntention != null) {
        term = new Term("checkinIntention", checkinIntention);
        termQuery = new TermQuery(term);
        booleanQuery.add(termQuery, Occur.MUST);
      }

      if (infoChannel != null) {
        term = new Term("infoChannel", infoChannel);
        termQuery = new TermQuery(term);
        booleanQuery.add(termQuery, Occur.MUST);
      }

      if (LogUtil.isDebugEnabled(ConsultationServiceImpl.class)) {
        LogUtil
            .debug(
                ConsultationServiceImpl.class,
                "consultationSearch Export Excel",
                "Searching consultation records with params,tenant ID=%s,visitor=%s,elderly name=%s,infoChannel=%s,checkinIntention=%s",
                tenantAccountService.getCurrentTenantID(), visitor,
                elderlyName, infoChannel, checkinIntention);
      }
      return booleanQuery;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  
  /**
   * 准备数据，将list转化成HashMap,作为需要导出的数据
   * @param eventRecordList
   * @return
   */
  @Override
  public List<Map<String, String>> prepareMap(List<ConsultationRecord> consultationList){
    
    List<Map<String, String>> mapList = new ArrayList<Map<String,String>>();
    
    for (ConsultationRecord consultation : consultationList) {
      Map<String, String> consultationMap = new HashMap<String, String>();
      consultationMap.put("visitor", consultation.getVisitor());
      consultationMap.put("phoneNumber", consultation.getPhoneNumber());
      consultationMap.put("elderlyName", consultation.getElderlyName());
      if(consultation.getGender() == Gender.MALE) {
        consultationMap.put("gender", "男");
      } else if(consultation.getGender() == Gender.FEMALE) {
        consultationMap.put("gender", "女");
      }
      if(consultation.getCheckinIntention() == CheckinIntention.CONFIRMED){
        consultationMap.put("checkinIntention", "确认入住");
      }else if(consultation.getCheckinIntention() == CheckinIntention.WILL_TO_CHECKIN_STRONGLY){
        consultationMap.put("checkinIntention", "入住意愿强");
      }else if(consultation.getCheckinIntention() == CheckinIntention.WILL_TO_CHECKIN_NOT_STRONGLY){
        consultationMap.put("checkinIntention", "入住意愿不强");
      }else if(consultation.getCheckinIntention() == CheckinIntention.WILL_NOT_CHECKIN){
        consultationMap.put("checkinIntention", "不入住");
      }
      if(consultation.getRelation() == Relation.SELF){
        consultationMap.put("relation", "本人");
      }else if(consultation.getRelation() == Relation.CHILDREN){
        consultationMap.put("relation", "子女");
      }else if(consultation.getRelation() == Relation.MARRIAGE_RELATIONSHIP){
        consultationMap.put("relation", "夫妻");
      }else if(consultation.getRelation() == Relation.GRANDPARENTS_AND_GRANDCHILDREN){
        consultationMap.put("relation", "祖孙关系");
      }else if(consultation.getRelation() == Relation.BROTHERS_OR_SISTERS){
        consultationMap.put("relation", "兄弟或姐妹");
      }else if(consultation.getRelation() == Relation.DAUGHTERINLAW_OR_SONINLAW){
        consultationMap.put("relation", "儿媳或女婿");
      }else if(consultation.getRelation() == Relation.FRIEND){
        consultationMap.put("relation", "朋友");
      }else if(consultation.getRelation() == Relation.OTHER){
        consultationMap.put("relation", "其它");
      }
      if(consultation.getInfoChannel() == InfoChannel.NETWORK){
        consultationMap.put("infoChannel", "网络");
      }else if(consultation.getInfoChannel() == InfoChannel.COMMUNITY){
        consultationMap.put("infoChannel", "社区");
      }else if(consultation.getInfoChannel() == InfoChannel.OHTER_INTRODUCT){
        consultationMap.put("infoChannel", "他人介绍");
      }else if(consultation.getInfoChannel() == InfoChannel.ADVERTISEMENT){
        consultationMap.put("infoChannel", "广告");
      }else if(consultation.getInfoChannel() == InfoChannel.OTHER){
        consultationMap.put("infoChannel", "其它");
      }
      if(consultation.getReturnVisit() == true){
        consultationMap.put("returnVisit", "是");
      }else{
        consultationMap.put("returnVisit", "否");
      }
      consultationMap.put("returnVisitDate", DateTimeUtils.getSimpleFormatString(DateTimeUtils.shortDateFormat, consultation.getReturnVisitDate()));
      mapList.add(consultationMap);
    }
    return mapList;
  }

}
