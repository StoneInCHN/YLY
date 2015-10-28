package com.yly.service.impl;

import java.util.Date;

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
                tenantAccountService.getCurrentTenantID(), blackList.getElderlyInfo().getName(), beginDate, endDate);
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
}
