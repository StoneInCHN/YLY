package com.yly.service.impl;

import java.io.Serializable;
import java.util.Date;

import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.util.Version;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.yly.entity.commonenum.CommonEnum.BudgetType;
import com.yly.entity.commonenum.CommonEnum.PaymentStatus;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.ChargeRecordService;
import com.yly.utils.DateTimeUtils;


public class ChargeRecordServiceImpl<T, ID extends Serializable> extends BaseServiceImpl<T, ID>
    implements ChargeRecordService<T, ID> {
  
  @Override
  public Page<T> chargeRecordSearch(Boolean isTenant,Date beginDate, Date endDate, String realName,
      String identifier, PaymentStatus status, BudgetType budgetType, Boolean isPeriod,
      Pageable pageable) {

    IKAnalyzer analyzer = new IKAnalyzer();
    analyzer.setMaxWordLength(true);

    try {
      BooleanQuery query = new BooleanQuery();
      if (isTenant) {
        Term term = new Term("tenantID", tenantAccountService.getCurrentTenantID().toString());
        Query filterQuery = new TermQuery(term);
        query.add(filterQuery, Occur.MUST);
      }
      if (realName != null) {
        String text = QueryParser.escape(realName);
        QueryParser filterParser = new QueryParser(Version.LUCENE_35, "elderlyInfo.name", analyzer);
        Query filterQuery = filterParser.parse(text);
        query.add(filterQuery, Occur.MUST);
      }
      if (identifier != null) {
        String text = QueryParser.escape(identifier);
        QueryParser filterParser =
            new QueryParser(Version.LUCENE_35, "elderlyInfo.identifier", analyzer);
        Query filterQuery = filterParser.parse(text);
        query.add(filterQuery, Occur.MUST);
      }
      if (status != null) {
        Term term = new Term("chargeStatus", status.toString());
        Query filterQuery = new TermQuery(term);
        // String text = QueryParser.escape(status.toString());
        // QueryParser filterParser = new QueryParser(Version.LUCENE_35, "chargeStatus", analyzer);
        // Query filterQuery = filterParser.parse(text);
        query.add(filterQuery, Occur.MUST);
      }
      if (budgetType != null) {
        Term term = new Term("budgetType", budgetType.toString());
        Query filterQuery = new TermQuery(term);
        // String text = QueryParser.escape(budgetType.toString());
        // QueryParser filterParser = new QueryParser(Version.LUCENE_35, "budgetType", analyzer);
        // Query filterQuery = filterParser.parse(text);
        query.add(filterQuery, Occur.MUST);
      }
      if (isPeriod) {
        if (beginDate != null) {
          TermRangeQuery tQuery =
              new TermRangeQuery("periodStartDate", DateTimeUtils.convertDateToString(beginDate,
                  null), null, true, false);
          query.add(tQuery, Occur.MUST);
        }
        if (endDate != null) {
          TermRangeQuery tQuery =
              new TermRangeQuery("periodEndDate", null, DateTimeUtils.convertDateToString(endDate,
                  null), false, false);
          query.add(tQuery, Occur.MUST);
        }
      } else {
        if (beginDate != null || endDate != null) {
          TermRangeQuery tQuery =
              new TermRangeQuery("payTime", DateTimeUtils.convertDateToString(beginDate, null),
                  DateTimeUtils.convertDateToString(endDate, null), true, true);
          query.add(tQuery, Occur.MUST);
        }
      }

      return super.search(query, pageable, analyzer, null);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

}
