package com.yly.service.impl;


import javax.annotation.Resource;

import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.yly.dao.NurseArrangementRecordDao;
import com.yly.entity.NurseArrangementRecord;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.NurseArrangementRecordService;

/**
 * 护理员安排明细 Service Implement
 * 
 * @author luzhang
 *
 */
@Service("nurseArrangementRecordServiceImpl")
public class NurseArrangementRecordServiceImpl extends BaseServiceImpl<NurseArrangementRecord, Long>
    implements NurseArrangementRecordService {

  @Resource(name = "nurseArrangementRecordDaoImpl")
  private NurseArrangementRecordDao nurseArrangementRecordDao;
  
  @Resource
  public void setBaseDao(NurseArrangementRecordDao nurseArrangementRecordDao) {
    super.setBaseDao(nurseArrangementRecordDao);
  }

  @Override
  public Page<NurseArrangementRecord> searchPageByFilter(String nurseNameSearch, Long nurseArrangemenIDSearch,
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
      if (nurseNameSearch != null) {
        String text = QueryParser.escape(nurseNameSearch);
        QueryParser filterParser = new QueryParser(Version.LUCENE_35, "nurseName", analyzer);
        Query filterQuery = filterParser.parse(text);
        query.add(filterQuery, Occur.MUST);
      }
      if(nurseArrangemenIDSearch != null){
        QueryParser queryParser = new QueryParser(Version.LUCENE_35, "nurseArrangement.id", analyzer);
        Query queryNurseArrangemenID = queryParser.parse(nurseArrangemenIDSearch.toString());
        query.add(queryNurseArrangemenID, Occur.MUST);
      }
      return nurseArrangementRecordDao.search(query, pageable, analyzer, null);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
