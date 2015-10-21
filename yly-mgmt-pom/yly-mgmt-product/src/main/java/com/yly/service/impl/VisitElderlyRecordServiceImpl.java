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

import com.yly.common.log.LogUtil;
import com.yly.dao.VisitElderlyRecordDao;
import com.yly.entity.VisitElderlyRecord;
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
              "search",
              "Searching visiting records with params,visitor=%s,elderly=%s,start visitdate=%s,end visitdate=%s",
              visitElderlyRecord.getVisitor(), visitElderlyRecord.getElderlyInfo().getName(),
              visitDateBeginDate, visitDateEndDate);
    }

    return search(query, pageable, analyzer, visitDateFilter);
  }
}
