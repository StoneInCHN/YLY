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

import com.yly.dao.EvaluatingItemOptionsDao;
import com.yly.entity.EvaluatingItemOptions;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.EvaluatingItemOptionsService;
import com.yly.utils.DateTimeUtils;

/**
 * 选项表 Service Implement
 * 
 * @author luzhang
 *
 */
@Service("evaluatingItemOptionsServiceImpl")
public class EvaluatingItemOptionsServiceImpl extends BaseServiceImpl<EvaluatingItemOptions, Long>
    implements EvaluatingItemOptionsService {

  @Resource(name = "evaluatingItemOptionsDaoImpl")
  private EvaluatingItemOptionsDao evaluatingItemOptionsDao;

  @Resource
  public void setBaseDao(EvaluatingItemOptionsDao evaluatingItemOptionsDao) {
    super.setBaseDao(evaluatingItemOptionsDao);
  }

  @Override
  public Page<EvaluatingItemOptions> searchPageByFilter(String keysOfElderlyName, Date beginDate,
      Date endDate, Pageable pageable) {
    IKAnalyzer analyzer = new IKAnalyzer();
    analyzer.setMaxWordLength(true);

    try {
      BooleanQuery query = new BooleanQuery();
      if (keysOfElderlyName != null) {
        String text = QueryParser.escape(keysOfElderlyName);
        QueryParser filterParser = new QueryParser(Version.LUCENE_35, "elderlyInfo.name", analyzer);
        Query filterQuery = filterParser.parse(text);
        query.add(filterQuery, Occur.MUST);
      }
      if (beginDate != null || endDate != null) {
        TermRangeQuery tQuery =
            new TermRangeQuery("eventDate", DateTimeUtils.convertDateToString(beginDate, null),
                DateTimeUtils.convertDateToString(endDate, null), beginDate != null, endDate != null);
        query.add(tQuery, Occur.MUST);
      }

      return evaluatingItemOptionsDao.search(query, pageable, analyzer, null);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
