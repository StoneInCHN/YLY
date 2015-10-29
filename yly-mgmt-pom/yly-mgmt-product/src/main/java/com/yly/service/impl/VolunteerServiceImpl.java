package com.yly.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.yly.common.log.LogUtil;
import com.yly.dao.VolunteerDao;
import com.yly.entity.Volunteer;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.TenantAccountService;
import com.yly.service.VolunteerService;
import com.yly.utils.DateTimeUtils;

/**
 * 
 * @author chenyoujun
 *
 */
@Service("volunteerServiceImpl")
public class VolunteerServiceImpl extends BaseServiceImpl<Volunteer, Long> implements
    VolunteerService {

  @Resource(name = "volunteerDaoImpl")
  private VolunteerDao volunteerDao;

  @Resource(name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;

  @Resource
  public void setBaseDao(VolunteerDao volunteerDao) {
    super.setBaseDao(volunteerDao);
  }

  @Override
  public Page<Volunteer> searchList(Date beginDate, Date endDate, Volunteer volunteer, Pageable pageable) {
    IKAnalyzer analyzer = new IKAnalyzer();
    analyzer.setMaxWordLength(true);
    BooleanQuery query = new BooleanQuery();
    Query nameQuery = null;
    Query typeQuery = null;

    try {
      //关键字-自愿者名字
      if (volunteer.getVolunteerName() != null) {
        String text = QueryParser.escape(volunteer.getVolunteerName());
        QueryParser nameParser = new QueryParser(Version.LUCENE_35, "volunteerName", analyzer);
        nameQuery = nameParser.parse(text);
        query.add(nameQuery, Occur.MUST);
      }
      
      //关键字-自愿者类型
      if(volunteer.getVolunteerType() != null){
        Term typeTerm =
            new Term("volunteerType", volunteer.getVolunteerType().name());
        typeQuery = new TermQuery(typeTerm);
        query.add(typeQuery, Occur.MUST);
      }

      //关键字-活动时间范围
      if (beginDate != null || endDate != null) {
        TermRangeQuery tQuery =
            new TermRangeQuery("activityTime", DateTimeUtils.convertDateToString(beginDate, null),
                DateTimeUtils.convertDateToString(endDate, null), beginDate != null,
                endDate != null);
        query.add(tQuery, Occur.MUST);
      }

      if (LogUtil.isDebugEnabled(VolunteerServiceImpl.class)) {
        LogUtil
            .debug(
                VolunteerServiceImpl.class,
                "VolunteerSearch",
                "Search volunteer with params, tenant ID=%s, volunteerName=%s, volunteerType=%s, startTime=%s, endTime=%s",
                tenantAccountService.getCurrentTenantID(), volunteer.getVolunteerName(), volunteer.getVolunteerType().name(), DateTimeUtils.convertDateToString(
                    beginDate, null), DateTimeUtils.convertDateToString(
                        endDate, null));
      }
      return super.search(query, pageable, analyzer, null);

    } catch (ParseException e) {
      e.printStackTrace();
    }
    return null;
  }

}
