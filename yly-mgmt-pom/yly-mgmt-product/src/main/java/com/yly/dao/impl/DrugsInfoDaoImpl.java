package com.yly.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermRangeQuery;
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

//  /**
//   * 关键字搜索
//   */
//  @Override
//  public Page<DrugsInfo> search(Pageable pageable, ) {
//    Page<DrugsInfo> DrugsInfoPage = null;
//    
//    if (pageable == null) {
//      pageable = new Pageable();
//    }
//    String text = QueryParser.escape(keyword);
//    List<?> list;
//    List<DrugsInfo> drugsInfoList = new ArrayList<DrugsInfo>();
//    IKAnalyzer analyzer=new IKAnalyzer();
//    analyzer.setMaxWordLength(true);
//
//      QueryParser name =
//          new QueryParser(Version.LUCENE_35, "name", analyzer);
//      Query nameQuery = name.parse(text);
//      
//      
//      TermRangeQuery tQuery=new TermRangeQuery ("createDate", startDate, endDate, true, true);
//     
//      BooleanQuery query = new BooleanQuery();
//      query.add (nameQuery,Occur.MUST);
//      query.add (tQuery,Occur.MUST);
//      
//      
//
//  }

}
