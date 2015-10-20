package com.yly.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermRangeFilter;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.yly.common.log.LogUtil;
import com.yly.controller.DrugsInfoController;
import com.yly.dao.BlackListDao;
import com.yly.entity.BlackList;
import com.yly.framework.paging.Page;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.BlackListService;
import com.yly.utils.DateTimeUtils;

/**
 * 
 * @author pengyanan
 *
 */
@Service("blackListServiceImpl")
public class BlackListServiceImpl extends BaseServiceImpl<BlackList, Long> implements BlackListService{

  @Resource(name="blackListDaoImpl")
  private BlackListDao blackListDao;
  
  @Resource
  private void SetBasedDao(BlackListDao blackListDao){
    super.setBaseDao(blackListDao);
  }

  @Override
  public Page<BlackList> searchList(Date beginDate, Date endDate, String blackListName) {
    String beginDateStr = null, endDateStr = null;
    if (beginDate != null) {
      beginDateStr = DateTimeUtils.convertDateToString(beginDate, null);
    }
    if (endDate != null) {
      endDateStr = DateTimeUtils.convertDateToString(endDate, null);
    }
    
    IKAnalyzer analyzer = new IKAnalyzer ();
    analyzer.setMaxWordLength (true);
    BooleanQuery query = new BooleanQuery ();

    QueryParser nameParser = new QueryParser (Version.LUCENE_35, "name",
        analyzer);
    Query nameQuery = null;
    Filter filter = null;

    if (blackListName != null)
    {
      String text = QueryParser.escape (blackListName);
      try
      {
        nameQuery = nameParser.parse (text);
        query.add (nameQuery, Occur.SHOULD);
        
        if (LogUtil.isDebugEnabled (DrugsInfoController.class))
        {
          LogUtil.debug (DrugsInfoController.class, "search", "Search name: "
              + null + ", start date: " + beginDateStr + ", end date: "
              + endDateStr);
        }
        if (beginDateStr != null || endDateStr != null)
        {
          filter = new TermRangeFilter ("createDate", beginDateStr,
              endDateStr, true, true);
        }

        return super.search (query, null, analyzer,filter);
      }
      catch (ParseException e)
      {
        e.printStackTrace ();
      }
    }
    return null;
  }
}
