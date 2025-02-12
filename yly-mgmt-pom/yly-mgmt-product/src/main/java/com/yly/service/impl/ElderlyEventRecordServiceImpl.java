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

import com.yly.dao.ElderlyEventRecordDao;
import com.yly.entity.ElderlyEventRecord;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.ElderlyEventRecordService;
import com.yly.utils.DateTimeUtils;

/**
 * 老人事件 Service Implement
 * 
 * @author luzhang
 *
 */
@Service("elderlyEventRecordServiceImpl")
public class ElderlyEventRecordServiceImpl extends BaseServiceImpl<ElderlyEventRecord, Long>
    implements ElderlyEventRecordService {

  @Resource(name = "elderlyEventRecordDaoImpl")
  private ElderlyEventRecordDao elderlyEventRecordDao;

  @Resource
  public void setBaseDao(ElderlyEventRecordDao elderlyEventRecordDao) {
    super.setBaseDao(elderlyEventRecordDao);
  }

  @Override
  public Page<ElderlyEventRecord> searchPageByFilter(String keysOfElderlyName, String elderlyEventType, Date beginDate,
      Date endDate, Pageable pageable) {
    IKAnalyzer analyzer = new IKAnalyzer();
    analyzer.setMaxWordLength(true);
    try {
      BooleanQuery query = getQuery(analyzer, keysOfElderlyName, elderlyEventType, beginDate, endDate);
      return elderlyEventRecordDao.search(query, pageable, analyzer, null);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
  
  private BooleanQuery getQuery(IKAnalyzer analyzer, String keysOfElderlyName, String elderlyEventType, Date beginDate, Date endDate) {
    try {
      BooleanQuery query = new BooleanQuery();
      if (keysOfElderlyName != null) {
        String text = QueryParser.escape(keysOfElderlyName);
        QueryParser filterParser = new QueryParser(Version.LUCENE_35, "elderlyInfo.name", analyzer);
        Query filterQuery = filterParser.parse(text);
        query.add(filterQuery, Occur.MUST);
      }
      if (elderlyEventType != null) {
        Term elderlyStatusTerm = new Term("elderlyEventType", elderlyEventType);
        TermQuery termQuery = new TermQuery(elderlyStatusTerm);
        query.add(termQuery, Occur.MUST);
     }
      if (beginDate != null || endDate != null) {
        TermRangeQuery tQuery =
            new TermRangeQuery("eventDate", DateTimeUtils.convertDateToString(beginDate, null),
                DateTimeUtils.convertDateToString(endDate, null), beginDate != null, endDate != null);
        query.add(tQuery, Occur.MUST);
      }
      return query;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
   
  }



}
