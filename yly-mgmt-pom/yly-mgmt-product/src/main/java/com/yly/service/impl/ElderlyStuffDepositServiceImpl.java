package com.yly.service.impl;

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
import com.yly.json.request.StuffDepositSearchRequest;
import com.yly.service.ElderlyStuffDepositService;
import com.yly.utils.DateTimeUtils;

/**
 * 物品寄存 Service Implement
 * 
 * @author luzhang
 *
 */
@Service("elderlyStuffDepositServiceImpl")
public class ElderlyStuffDepositServiceImpl extends BaseServiceImpl<ElderlyStuffDeposit, Long>
    implements ElderlyStuffDepositService {

  @Resource(name = "elderlyStuffDepositDaoImpl")
  private ElderlyStuffDepositDao elderlyStuffDepositDao;

  @Resource
  public void setBaseDao(ElderlyStuffDepositDao elderlyStuffDepositDao) {
    super.setBaseDao(elderlyStuffDepositDao);
  }

  @Override
  public Page<ElderlyStuffDeposit> searchPageByFilter(StuffDepositSearchRequest searchParameter,
      Pageable pageable) {
    IKAnalyzer analyzer = new IKAnalyzer();
    analyzer.setMaxWordLength(true);

    try {
      BooleanQuery query = new BooleanQuery();
      if (searchParameter.getKeysOfElderlyName() != null) {
        String text = QueryParser.escape((searchParameter.getKeysOfElderlyName()));
        QueryParser filterParser = new QueryParser(Version.LUCENE_35, "elderlyInfo.name", analyzer);
        Query filterQuery = filterParser.parse(text);
        query.add(filterQuery, Occur.MUST);
      }
      if (searchParameter.getKeysOfStuffName() != null) {
        String text = QueryParser.escape(searchParameter.getKeysOfStuffName());
        QueryParser filterParser = new QueryParser(Version.LUCENE_35, "name", analyzer);
        Query filterQuery = filterParser.parse(text);
        query.add(filterQuery, Occur.MUST);
      }
      if (searchParameter.getKeysOfStuffNumber() != null) {
        String text = QueryParser.escape(searchParameter.getKeysOfStuffNumber());
        QueryParser filterParser = new QueryParser(Version.LUCENE_35, "stuffNumber", analyzer);
        Query filterQuery = filterParser.parse(text);
        query.add(filterQuery, Occur.MUST);
      }
      if (searchParameter.getBeginPutInDate() != null || searchParameter.getEndPutInDate() != null) {
        TermRangeQuery tQuery =
            new TermRangeQuery("putinDate", DateTimeUtils.convertDateToString(
                searchParameter.getBeginPutInDate(), null), DateTimeUtils.convertDateToString(
                searchParameter.getEndPutInDate(), null),
                searchParameter.getBeginPutInDate() != null,
                searchParameter.getEndPutInDate() != null);
        query.add(tQuery, Occur.MUST);
      }
      if (searchParameter.getBeginTakeAwayDate() != null
          || searchParameter.getEndTakeAwayDate() != null) {
        TermRangeQuery tQuery =
            new TermRangeQuery("takeAlwayDate", DateTimeUtils.convertDateToString(
                searchParameter.getBeginTakeAwayDate(), null), DateTimeUtils.convertDateToString(
                searchParameter.getEndTakeAwayDate(), null),
                searchParameter.getBeginTakeAwayDate() != null,
                searchParameter.getEndTakeAwayDate() != null);
        query.add(tQuery, Occur.MUST);
      }
      return elderlyStuffDepositDao.search(query, pageable, analyzer, null);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
