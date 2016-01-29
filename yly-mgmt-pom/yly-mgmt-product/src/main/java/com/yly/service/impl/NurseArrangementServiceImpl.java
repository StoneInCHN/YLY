package com.yly.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.yly.dao.NurseArrangementDao;
import com.yly.entity.NurseArrangement;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.NurseArrangementService;
import com.yly.utils.DateTimeUtils;

/**
 * 护理员安排 Service Implement
 * 
 * @author luzhang
 *
 */
@Service("nurseArrangementServiceImpl")
public class NurseArrangementServiceImpl extends BaseServiceImpl<NurseArrangement, Long>
    implements NurseArrangementService {

  @Resource(name = "nurseArrangementDaoImpl")
  private NurseArrangementDao nurseArrangementDao;

  @Resource
  public void setBaseDao(NurseArrangementDao nurseArrangementDao) {
    super.setBaseDao(nurseArrangementDao);
  }

  @Override
  public Page<NurseArrangement> searchPageByFilter(String nurseNameSearch, Date nurseStartDateSearch,
      Date nurseEndDateSearch, Long elderlyIDSearch, Long nurseAssistantIDSearch, Pageable pageable, Boolean isTenant) {
    IKAnalyzer analyzer = new IKAnalyzer();
    analyzer.setMaxWordLength(true);

    try {
      BooleanQuery query = new BooleanQuery();
      if (isTenant) {
        QueryParser queryParser = new QueryParser(Version.LUCENE_35, "tenantID", analyzer);
        Query queryTenantID = queryParser.parse(tenantAccountService.getCurrentTenantID().toString());
        query.add(queryTenantID, Occur.MUST);
      }
      if (nurseNameSearch != null) {
        String text = QueryParser.escape(nurseNameSearch);
        QueryParser filterParser = new QueryParser(Version.LUCENE_35, "nurseName", analyzer);
        Query filterQuery = filterParser.parse(text);
        query.add(filterQuery, Occur.MUST);
      }
      if (nurseStartDateSearch != null) {
        TermRangeQuery tQuery =
            new TermRangeQuery("nurseStartDate", DateTimeUtils.convertDateToString(nurseStartDateSearch, null),null, true, true);
        query.add(tQuery, Occur.MUST);
      }
      if(nurseEndDateSearch != null){
        TermRangeQuery tQuery =
            new TermRangeQuery("nurseEndDate", null, DateTimeUtils.convertDateToString(nurseEndDateSearch, null), true, true);
        query.add(tQuery, Occur.MUST);
      }
      if(elderlyIDSearch != null){
          QueryParser queryParser = new QueryParser(Version.LUCENE_35, "elderlyInfo.id", analyzer);
          Query queryElderlyInfoID = queryParser.parse(elderlyIDSearch.toString());
          query.add(queryElderlyInfoID, Occur.MUST);
      }
      if(nurseAssistantIDSearch != null){
        QueryParser queryParser = new QueryParser(Version.LUCENE_35, "nurseAssistant.id", analyzer);
        Query queryNurseAssistantID = queryParser.parse(nurseAssistantIDSearch.toString());
        query.add(queryNurseAssistantID, Occur.MUST);
      }
      return nurseArrangementDao.search(query, pageable, analyzer, null);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
