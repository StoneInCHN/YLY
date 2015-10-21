package com.yly.service.impl;

import javax.annotation.Resource;

import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.yly.dao.ElderlyInfoDao;
import com.yly.entity.ElderlyInfo;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.ElderlyInfoService;

/**
 * 老人信息
 * 
 * @author shijun
 *
 */
@Service("elderlyInfoServiceImpl")
public class ElderlyInfoServiceImpl extends BaseServiceImpl<ElderlyInfo, Long> implements
    ElderlyInfoService {
  
  @Resource(name = "elderlyInfoDaoImpl")
  private ElderlyInfoDao elderlyInfoDao;
  
  @Resource
  public void setBaseDao(ElderlyInfoDao elderlyInfoDao){
    super.setBaseDao(elderlyInfoDao);
  }

  @Override
  public Page<ElderlyInfo> elderlyInfoSearch(Boolean isTenant,String realName, String identifier, Pageable pageable) {
    IKAnalyzer analyzer = new IKAnalyzer();
    analyzer.setMaxWordLength(true);
    try {
      BooleanQuery query = new BooleanQuery();
      if (isTenant) {
        Term term = new Term("tenantID", tenantAccountService.getCurrentTenantID().toString());
        Query filterQuery = new TermQuery(term);
        query.add(filterQuery, Occur.MUST);
      }
      if (realName != null) {
        String text = QueryParser.escape(realName);
        QueryParser filterParser = new QueryParser(Version.LUCENE_35, "name", analyzer);
        Query filterQuery = filterParser.parse(text);
        query.add(filterQuery,Occur.MUST);
      }
      if (identifier != null) {
        String text = QueryParser.escape(identifier);
        QueryParser filterParser = new QueryParser(Version.LUCENE_35, "identifier", analyzer);
        Query filterQuery = filterParser.parse(text);
        query.add(filterQuery,Occur.MUST);
      }
      
      return elderlyInfoDao.search(query, pageable, analyzer,null);
    } catch (Exception e) {
       e.printStackTrace();
    }
    return null;
  }
  
  

}
