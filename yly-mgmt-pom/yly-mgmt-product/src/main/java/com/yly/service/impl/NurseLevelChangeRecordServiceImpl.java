package com.yly.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.yly.dao.NurseLevelChangeRecordDao;
import com.yly.entity.NurseLevelChangeRecord;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.NurseLevelChangeRecordService;
import com.yly.utils.DateTimeUtils;

@Service("nurseLevelChangeRecordServiceImpl")
public class NurseLevelChangeRecordServiceImpl extends
    BaseServiceImpl<NurseLevelChangeRecord, Long> implements NurseLevelChangeRecordService {

  @Resource(name = "nurseLevelChangeRecordDaoImpl")
  private NurseLevelChangeRecordDao nurseLevelChangeRecordDao;

  @Resource(name = "nurseLevelChangeRecordDaoImpl")
  public void setBaseDao(NurseLevelChangeRecordDao nurseLevelChangeRecordDao) {
    super.setBaseDao(nurseLevelChangeRecordDao);
  }

  @Override
  public Page<NurseLevelChangeRecord> searchPageByFilter(Long nurseChangeElderlyID,
      Date nurseChangeStartDate, Date nurseChangeEndDate, String nurseChangeStatus,
      Pageable pageable, Boolean isTenant) {

    IKAnalyzer analyzer = new IKAnalyzer();
    analyzer.setMaxWordLength(true);

    try {
      BooleanQuery query = new BooleanQuery();
      if (isTenant) {
        QueryParser queryParser = new QueryParser(Version.LUCENE_35, "tenantID", analyzer);
        Query queryTenantID = queryParser.parse(tenantAccountService.getCurrentTenantID().toString());
        query.add(queryTenantID, Occur.MUST);
      }
      if (nurseChangeStatus != null) {
        Term statusTerm = new Term("nurseChangeStatus", nurseChangeStatus);
        TermQuery statusQuery = new TermQuery(statusTerm);
        query.add(statusQuery, Occur.MUST);
      }
      if (nurseChangeStartDate != null) {
        TermRangeQuery tQuery =
            new TermRangeQuery("changeDate", DateTimeUtils.convertDateToString(nurseChangeStartDate, null),null, true, true);
        query.add(tQuery, Occur.MUST);
      }
      if(nurseChangeEndDate != null){
        TermRangeQuery tQuery =
            new TermRangeQuery("changeDate", null, DateTimeUtils.convertDateToString(nurseChangeEndDate, null), true, true);
        query.add(tQuery, Occur.MUST);
      }
      if(nurseChangeElderlyID != null){
          QueryParser queryParser = new QueryParser(Version.LUCENE_35, "elderlyInfo.id", analyzer);
          Query queryElderlyInfoID = queryParser.parse(nurseChangeElderlyID.toString());
          query.add(queryElderlyInfoID, Occur.MUST);
      }
      return nurseLevelChangeRecordDao.search(query, pageable, analyzer, null);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  
  }




}
