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

import com.yly.dao.ConsultationDao;
import com.yly.entity.ConsultationRecord;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.ConsultationService;
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
  
  @Resource
  public void setBaseDao(ConsultationDao consultationDao) {
    super.setBaseDao(consultationDao);
  }

  public Page<ConsultationRecord> consultationSearch(Date returnVisitDateBeginDate , Date returnVisitDateEndDate,ConsultationRecord consultationRecord,Pageable pageable) {
    
    IKAnalyzer analyzer = new IKAnalyzer();
    analyzer.setMaxWordLength(true);
    BooleanQuery query = new BooleanQuery();

    QueryParser vistorParser = new QueryParser(Version.LUCENE_35, "vistor", analyzer);
    QueryParser elderlyNameParser = new QueryParser(Version.LUCENE_35, "elderlyName", analyzer);

    Query vistorQuery = null;
    Query elderlyNameQuery = null;
    Query infoChannelQuery = null;
    Query checkinIntentionQuery = null;
    Filter returnVisitDateFilter = null;

    if (consultationRecord.getVistor() != null) {
      String vistorName = QueryParser.escape(consultationRecord.getVistor());
      try {
        vistorQuery = vistorParser.parse(vistorName);
      } catch (ParseException e) {
        e.printStackTrace();
      }
      query.add(vistorQuery, Occur.MUST);
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
    return search(query, pageable, analyzer, returnVisitDateFilter);
  }
  
  
}
