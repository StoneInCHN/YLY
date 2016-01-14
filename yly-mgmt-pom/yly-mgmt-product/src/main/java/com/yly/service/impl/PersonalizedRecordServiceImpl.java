package com.yly.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.yly.dao.PersonalizedRecordDao;
import com.yly.entity.PersonalizedRecord;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.json.request.ChargeSearchRequest;
import com.yly.service.PersonalizedRecordService;
import com.yly.utils.DateTimeUtils;
/**
 * 个性化服务服务详情记录 Service Implement
 * @author luzhang
 *
 */
@Service("personalizedRecordServiceImpl")
public class PersonalizedRecordServiceImpl extends BaseServiceImpl<PersonalizedRecord, Long>
    implements PersonalizedRecordService{
  
  @Resource(name = "personalizedRecordDaoImpl")
  private PersonalizedRecordDao personalizedRecordDao;
  
  @Resource
  public void setBaseDao(PersonalizedRecordDao personalizedRecordDao) {
    super.setBaseDao(personalizedRecordDao);
  }
  
  @Override
  public List<PersonalizedRecord> searchRecentRecords(ChargeSearchRequest chargeSearch) {
    IKAnalyzer analyzer = new IKAnalyzer();
    analyzer.setMaxWordLength(true);
    try {
      BooleanQuery query = getQuery(analyzer, chargeSearch);
      return personalizedRecordDao.searchList(query, analyzer, null);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
  /**
   * 根据查询条件中的老人id，一个月以内， 返回带条件的查询Query
   * @return
   */
  private BooleanQuery getQuery(IKAnalyzer analyzer, ChargeSearchRequest chargeSearch) {
    BooleanQuery booleanQuery  = new BooleanQuery();
    Query query = null;
    try {
      Long tennateId = tenantAccountService.getCurrentTenantID();
      if (tennateId != null) {
        QueryParser queryParser = new QueryParser(Version.LUCENE_35, "tenantID", analyzer);
        query = queryParser.parse(tennateId.toString());
        booleanQuery.add(query, Occur.MUST);
      }
      if (chargeSearch.getCheckoutDate() != null) {
        Date endDate = chargeSearch.getCheckoutDate();
        Date beginDate = DateTimeUtils.getRecentDate(chargeSearch.getCheckoutDate(), "MONTH", -1);//倒退一个月
        TermRangeQuery tQuery =
            new TermRangeQuery("serviceTime", DateTimeUtils.convertDateToString(beginDate, null),
                DateTimeUtils.convertDateToString(endDate, null), beginDate != null, endDate != null);
        booleanQuery.add(tQuery, Occur.MUST);
      }        
      if(chargeSearch.getElderlyId() != null){
        QueryParser queryParser = new QueryParser(Version.LUCENE_35, "elderlyInfo.id", analyzer);
        query = queryParser.parse(chargeSearch.getElderlyId().toString());
        booleanQuery.add(query, Occur.MUST);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return booleanQuery;
  }
}
