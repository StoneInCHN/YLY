package com.yly.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.lucene.document.DateTools;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.QueryWrapperFilter;
import org.apache.lucene.search.TermRangeFilter;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.util.BytesRef;
import org.apache.lucene.util.Version;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.springframework.stereotype.Repository;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.yly.common.log.LogUtil;
import com.yly.dao.DrugsInfoDao;
import com.yly.entity.DrugsInfo;
import com.yly.framework.dao.impl.BaseDaoImpl;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;

@Repository("drugsInfoDaoImpl")
public class DrugsInfoDaoImpl extends BaseDaoImpl<DrugsInfo, Long> implements DrugsInfoDao {

  /**
   * 关键字搜索
   */
  @Override
  public Page<DrugsInfo> search(String keyword, Pageable pageable,String startDate, String endDate) {
    Page<DrugsInfo> DrugsInfoPage = null;
    
    if (pageable == null) {
      pageable = new Pageable();
    }
    String text = QueryParser.escape(keyword);
    List<?> list;
    List<DrugsInfo> drugsInfoList = new ArrayList<DrugsInfo>();
    IKAnalyzer analyzer=new IKAnalyzer();
    analyzer.setMaxWordLength(true);
    try {

      QueryParser name =
          new QueryParser(Version.LUCENE_35, "name", analyzer);
      Query nameQuery = name.parse(text);
      
      
      TermRangeQuery tQuery=new TermRangeQuery ("createDate", startDate, endDate, true, true);
     
      BooleanQuery query = new BooleanQuery();
      query.add (nameQuery,Occur.MUST);
      query.add (tQuery,Occur.MUST);
      
      FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
      FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(query, DrugsInfo.class);

      
      
      list = fullTextQuery.getResultList();
      for (Object o : list) {
        if (o instanceof DrugsInfo) {
          if (LogUtil.isDebugEnabled(DrugsInfoDaoImpl.class)) {
            LogUtil.debug(DrugsInfoDaoImpl.class, "search", "Found Lawyer %s",
                ((DrugsInfo) o).getName ());
          }
          if (!drugsInfoList.contains((DrugsInfo) o)) {
            drugsInfoList.add((DrugsInfo) o);
          }
        }
      }
      return new Page<DrugsInfo>(drugsInfoList, fullTextQuery.getResultSize(), pageable);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return DrugsInfoPage;

  }

  /**
   * 从数据库初始化Index
   */
  public void refreshIndex() {

    FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
    try {
      fullTextEntityManager.createIndexer().startAndWait();
    } catch (InterruptedException e) {
//      if (LogUtil.isDebugEnabled(SearchDaoImpl.class)) {
//        LogUtil.debug(SearchDaoImpl.class, "refreshIndex", "Error during refresh Lucene index");
//      }
    }
  }
}
