package com.yly.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.yly.common.log.LogUtil;
import com.yly.dao.BlackListDao;
import com.yly.entity.BlackList;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.BlackListService;
import com.yly.utils.DateTimeUtils;

/**
 * 
 * @author pengyanan
 *
 */
@Service("blackListServiceImpl")
public class BlackListServiceImpl extends BaseServiceImpl<BlackList, Long> implements
    BlackListService {

  @Resource(name = "blackListDaoImpl")
  private BlackListDao blackListDao;

  @Resource
  private void SetBasedDao(BlackListDao blackListDao) {
    super.setBaseDao(blackListDao);
  }

  @Override
  public Page<BlackList> searchList(Date beginDate, Date endDate, Pageable pageable,BlackList blackList) {
    IKAnalyzer analyzer = new IKAnalyzer();
    analyzer.setMaxWordLength(true);
    BooleanQuery query = new BooleanQuery();
    Query nameQuery = null;
      
    try {
      if(blackList.getElderlyInfo().getName()!=null){
        String text = QueryParser.escape(blackList.getElderlyInfo().getName());
        QueryParser nameParser = new QueryParser(Version.LUCENE_35, "elderlyInfo.name", analyzer);
        nameQuery = nameParser.parse(text);
        query.add(nameQuery, Occur.MUST);
      }
      if (beginDate != null || endDate != null) {
        TermRangeQuery tQuery =
            new TermRangeQuery("createDate", DateTimeUtils.convertDateToString(beginDate, null),
                DateTimeUtils.convertDateToString(endDate, null), beginDate != null,
                endDate != null);
        query.add(tQuery, Occur.MUST);
      }

      if (LogUtil.isDebugEnabled(BlackListServiceImpl.class)) {
        LogUtil
            .debug(
                BlackListServiceImpl.class,
                "BlackListSearch",
                "Search volunteer with params, tenant ID=%s, blackListName=%s, startTime=%s, endTime=%s",
                tenantAccountService.getCurrentTenantID(), blackList.getElderlyInfo().getName(), DateTimeUtils.convertDateToString(
                    beginDate, null), DateTimeUtils.convertDateToString(
                        endDate, null));
      }
      return super.search(query, pageable, analyzer, null);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    
    return null;
  }

  public BlackListDao getBlackListDao() {
    return blackListDao;
  }

  public void setBlackListDao(BlackListDao blackListDao) {
    this.blackListDao = blackListDao;
  }
  /**
   * 根据搜索条件，返回查询结果个数
   */
  @Override
  public int countByFilter(String nameHidden, Date beginDateHidden, Date endDateHidden) {
    IKAnalyzer analyzer = new IKAnalyzer();
    analyzer.setMaxWordLength(true);
    try {
      BooleanQuery booleanQuery = getQuery(analyzer, nameHidden, beginDateHidden, endDateHidden);
      return blackListDao.count(booleanQuery, analyzer, null);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return 0;
  }


  /**
   * 根据搜索条件，返回查询结果List
   */
  @Override
  public List<BlackList> searchListByFilter(String nameHidden, Date beginDateHidden,
      Date endDateHidden) {
    IKAnalyzer analyzer = new IKAnalyzer();
    analyzer.setMaxWordLength(true);
    try {
      BooleanQuery query = getQuery(analyzer, nameHidden, beginDateHidden, endDateHidden);
      return blackListDao.searchList(query, analyzer, null);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
  /**
   * 根据查询条件中的 老人姓名，录入开始时间和结束时间，返回带条件的查询Query
   * @return
   */
  private BooleanQuery getQuery(IKAnalyzer analyzer, String nameHidden, Date beginDateHidden,
      Date endDateHidden) {
    BooleanQuery booleanQuery = new BooleanQuery();
    Query query = null;
      
    try {
      
      Long tennateId = tenantAccountService.getCurrentTenantID();
      if (tennateId != null) {
        QueryParser queryParser = new QueryParser(Version.LUCENE_35, "tenantID", analyzer);
        query = queryParser.parse(tennateId.toString());
        booleanQuery.add(query, Occur.MUST);
      }
      
      if(nameHidden!=null){
        String text = QueryParser.escape(nameHidden);
        QueryParser nameParser = new QueryParser(Version.LUCENE_35, "elderlyInfo.name", analyzer);
        query = nameParser.parse(text);
        booleanQuery.add(query, Occur.MUST);
      }
      if (beginDateHidden != null || endDateHidden != null) {
        TermRangeQuery tQuery =
            new TermRangeQuery("createDate", DateTimeUtils.convertDateToString(beginDateHidden, null),
                DateTimeUtils.convertDateToString(endDateHidden, null), beginDateHidden != null,
                    endDateHidden != null);
        booleanQuery.add(tQuery, Occur.MUST);
      }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return booleanQuery;
      
  }
  /**
   * 准备数据，将list转化成HashMap,作为需要导出的数据
   * @return
   */
  @Override
  public List<Map<String, String>> prepareMap(List<BlackList> blackListList) {
    List<Map<String, String>> mapList = new ArrayList<Map<String,String>>();
    for (BlackList blackList : blackListList) {
      Map<String, String> blackListMap = new HashMap<String, String>();
      blackListMap.put("elderlyInfo.name", blackList.getElderlyInfo()!=null?blackList.getElderlyInfo().getName():"");
      if (blackList.getBlackDate() != null) {
        blackListMap.put("blackDate", DateTimeUtils.getSimpleFormatString(DateTimeUtils.shortDateFormat, blackList.getBlackDate()));
      }else {
        blackListMap.put("blackDate","");
      }
      blackListMap.put("elderlyInfo.elderlyPhoneNumber", blackList.getElderlyInfo()!=null?blackList.getElderlyInfo().getElderlyPhoneNumber():"");
      if (blackList.getElderlyInfo()!=null && blackList.getElderlyInfo().getAge()!=null) {
        blackListMap.put("elderlyInfo.age", blackList.getElderlyInfo().getAge().toString());
      }else {
        blackListMap.put("elderlyInfo.age", "");
      }
      blackListMap.put("cause", blackList.getCause());
      mapList.add(blackListMap);
    }
    return mapList;
  
  }
}
