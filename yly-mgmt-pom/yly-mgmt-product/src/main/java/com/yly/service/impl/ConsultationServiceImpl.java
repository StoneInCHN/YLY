package com.yly.service.impl;

import java.util.Date;

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
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.framework.service.impl.BaseServiceImpl;
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
}
