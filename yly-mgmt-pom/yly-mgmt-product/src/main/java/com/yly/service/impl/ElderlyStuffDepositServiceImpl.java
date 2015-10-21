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

import com.yly.dao.ElderlyStuffDepositDao;
import com.yly.entity.ElderlyStuffDeposit;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.ElderlyStuffDepositService;
import com.yly.utils.DateTimeUtils;

/**
 * 物品寄存 Service Implement
 * 
 * @author luzhang
 *
 */
@Service("elderlyStuffDepositServiceImpl")
public class ElderlyStuffDepositServiceImpl extends BaseServiceImpl<ElderlyStuffDeposit, Long> implements
ElderlyStuffDepositService {
  
  @Resource(name = "elderlyStuffDepositDaoImpl")
  private ElderlyStuffDepositDao elderlyStuffDepositDao;
  
  @Resource
  public void setBaseDao(ElderlyStuffDepositDao elderlyStuffDepositDao) {
    super.setBaseDao(elderlyStuffDepositDao);
  }

  @Override
  public Page<ElderlyStuffDeposit> searchPageByFilter(String keysOfStuffName,String keysOfStuffNumber, String keysOfElderlyName, Date beginDate,
      Date endDate, Pageable pageable) {
    IKAnalyzer analyzer = new IKAnalyzer();
    analyzer.setMaxWordLength(true);

    try {
      BooleanQuery query = new BooleanQuery();
      if (keysOfElderlyName != null) {
        String text = QueryParser.escape(keysOfElderlyName);
        QueryParser filterParser = new QueryParser(Version.LUCENE_35, "elderlyInfo.name", analyzer);
        Query filterQuery = filterParser.parse(text);
        query.add(filterQuery,Occur.MUST);
      }  
      if (keysOfStuffName != null) {
        String text = QueryParser.escape(keysOfStuffName);
        QueryParser filterParser = new QueryParser(Version.LUCENE_35, "name", analyzer);
        Query filterQuery = filterParser.parse(text);
        query.add(filterQuery,Occur.MUST);
      }     
      if (keysOfStuffNumber != null) {
        String text = QueryParser.escape(keysOfStuffNumber);
        QueryParser filterParser = new QueryParser(Version.LUCENE_35, "stuffNumber", analyzer);
        Query filterQuery = filterParser.parse(text);
        query.add(filterQuery,Occur.MUST);
      } 
      if (beginDate != null) {
        TermRangeQuery tQuery = new TermRangeQuery ("putinDate",DateTimeUtils.convertDateToString (beginDate, null),null, true, false);
        query.add(tQuery, Occur.MUST);
      }
      if (endDate != null) {
        TermRangeQuery tQuery = new TermRangeQuery ("takeAlwayDate",null, DateTimeUtils.convertDateToString (endDate, null),false, true);
        query.add(tQuery, Occur.MUST);
      }
      return elderlyStuffDepositDao.search(query, pageable, analyzer,null);
    } catch (Exception e) {
       e.printStackTrace();
    }
    return null;
  }
}
