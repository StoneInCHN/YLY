package com.yly.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TermRangeFilter;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.yly.common.log.LogUtil;
import com.yly.controller.DrugsInfoController;
import com.yly.dao.DrugsInfoDao;
import com.yly.entity.DrugsInfo;
import com.yly.entity.commonenum.CommonEnum.DrugStatus;
import com.yly.framework.filter.Filter.Operator;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.DrugsInfoService;
import com.yly.utils.DateTimeUtils;

@Service("drugsInfoServiceImpl")
public class DrugsInfoServiceImpl extends BaseServiceImpl<DrugsInfo, Long> implements DrugsInfoService {

  @Resource(name = "drugsInfoDaoImpl")
  private DrugsInfoDao drugsInfoDao;
  
  @Resource
  public void setBaseDao(DrugsInfoDao drugsDao) {
    super.setBaseDao(drugsDao);
  }

  @Override
  public Page<DrugsInfo> drugsSerach (Date beginDate, Date endDate,
      String drugName, Pageable pageable)
  {
    String startDateStr = null;
    String endDateStr = null;

    IKAnalyzer analyzer = new IKAnalyzer ();
    analyzer.setMaxWordLength (true);
    BooleanQuery query = new BooleanQuery ();

    QueryParser nameParser = new QueryParser (Version.LUCENE_35, "name",
        analyzer);
    QueryParser phoneticCodeParser = new QueryParser (Version.LUCENE_35, "phoneticCode",
        analyzer);
    QueryParser aliasParser = new QueryParser (Version.LUCENE_35, "phoneticCode",
        analyzer);
    Query nameQuery = null;
    Query phoneticCodeQuery = null;
    Query aliasQuery = null;
    
    Filter filter = null;
    if (beginDate != null)
    {
      startDateStr = DateTimeUtils.convertDateToString (beginDate, null);
    }
    if (endDate != null)
    {
      endDateStr = DateTimeUtils.convertDateToString (endDate, null);
    }
    if (drugName != null)
    {
//      String text = QueryParser.escape (drugName);
      String text= drugName+"*";
      try
      {
        BooleanQuery shouldQuery =new BooleanQuery ();
        nameQuery = nameParser.parse (text);
        phoneticCodeQuery=phoneticCodeParser.parse (text);
        aliasQuery = aliasParser.parse (text);
        
        shouldQuery.add (nameQuery, Occur.SHOULD);
        shouldQuery.add (phoneticCodeQuery,Occur.SHOULD);
        shouldQuery.add (aliasQuery,Occur.SHOULD);
        
        
        Query drugsStatusQuery = new TermQuery(new Term("drugStatus", DrugStatus.ENABLE.toString ()));
        
        
        query.add (shouldQuery,Occur.MUST);
        query.add (drugsStatusQuery,Occur.MUST);
        if (LogUtil.isDebugEnabled (DrugsInfoController.class))
        {
          LogUtil.debug (DrugsInfoController.class, "search", "Search name: "
              + null + ", start date: " + startDateStr + ", end date: "
              + endDateStr);
        }
        if (startDateStr != null || endDateStr != null)
        {
          filter = new TermRangeFilter ("createDate", startDateStr,
              endDateStr, true, true);
        }

        return this.search (query, null, analyzer,filter);
      }
      catch (ParseException e)
      {
        e.printStackTrace ();
      }
    }
    List<com.yly.framework.filter.Filter> filterList= new ArrayList<com.yly.framework.filter.Filter>();
    com.yly.framework.filter.Filter statusFilter= new com.yly.framework.filter.Filter ();
    statusFilter.setProperty ("drugStatus");
    statusFilter.setValue (DrugStatus.ENABLE);
    statusFilter.setOperator (Operator.eq);
    filterList.add (statusFilter);
    pageable.setFilters (filterList);
    
    return this.findPage (pageable);
  }



}
