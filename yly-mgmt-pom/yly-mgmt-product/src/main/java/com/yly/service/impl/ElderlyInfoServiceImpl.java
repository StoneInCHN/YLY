package com.yly.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TermRangeFilter;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.yly.common.log.LogUtil;
import com.yly.dao.ElderlyInfoDao;
import com.yly.entity.ElderlyInfo;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.ElderlyInfoService;
import com.yly.utils.DateTimeUtils;

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
  public void setBaseDao(ElderlyInfoDao elderlyInfoDao) {
    super.setBaseDao(elderlyInfoDao);
  }

  public Page<ElderlyInfo> searchElderlyInfo(Date beHospitalizedBeginDate,
      Date beHospitalizedEndDate, ElderlyInfo elderlyInfo, Pageable pageable) {


    IKAnalyzer analyzer = new IKAnalyzer();
    analyzer.setMaxWordLength(true);
    BooleanQuery query = new BooleanQuery();

    QueryParser identifierParser = new QueryParser(Version.LUCENE_35, "identifier", analyzer);
    QueryParser elderlyNameParser = new QueryParser(Version.LUCENE_35, "name", analyzer);
    QueryParser tenantParser = new QueryParser(Version.LUCENE_35, "tenantID", analyzer);

    Query tenantQuery = null;
    Query identifierQuery = null;
    Query elderlyNameQuery = null;
    Query elderlyStatusQuery = null;
    Query deleteStatusQuery = null;
    Filter beHospitalizedDateFilter = null;

    try {
      tenantQuery = tenantParser.parse(tenantAccountService.getCurrentTenantID().toString());
      query.add(tenantQuery, Occur.MUST);
    } catch (ParseException e1) {
      e1.printStackTrace();
    }

    if (elderlyInfo.getName() != null) {
      String elderlyName = QueryParser.escape(elderlyInfo.getName());
      try {
        elderlyNameQuery = elderlyNameParser.parse(elderlyName);
      } catch (ParseException e) {
        e.printStackTrace();
      }
      query.add(elderlyNameQuery, Occur.MUST);
    }

    if (elderlyInfo.getIdentifier() != null) {
      String identifier = QueryParser.escape(elderlyInfo.getIdentifier());
      try {
        identifierQuery = identifierParser.parse(identifier);
      } catch (ParseException e) {
        e.printStackTrace();
      }
      query.add(identifierQuery, Occur.MUST);
    }

    if (elderlyInfo.getElderlyStatus() != null) {
        Term elderlyStatusTerm = new Term("elderlyStatus", elderlyInfo.getElderlyStatus().toString());
        elderlyStatusQuery = new TermQuery(elderlyStatusTerm);
        query.add(elderlyStatusQuery, Occur.MUST);
    }

    if (elderlyInfo.getDeleteStatus() != null) {
        Term deleteStatusTerm = new Term("deleteStatus", elderlyInfo.getDeleteStatus().toString());
        deleteStatusQuery = new TermQuery(deleteStatusTerm);
        query.add(deleteStatusQuery, Occur.MUST);
    }

    if (beHospitalizedBeginDate != null || beHospitalizedEndDate != null) {

      beHospitalizedDateFilter =
          new TermRangeFilter("beHospitalizedDate", DateTimeUtils.convertDateToString(
              beHospitalizedBeginDate, null), DateTimeUtils.convertDateToString(
              beHospitalizedEndDate, null), true, true);
    }

    if (LogUtil.isDebugEnabled(ConsultationServiceImpl.class)) {
      LogUtil
          .debug(
              ConsultationServiceImpl.class,
              "searchElderlyInfo",
              "Searching Elderly records with params,tenant ID=%s,identifier=%s,elderly name=%s,elderlyStatus=%s,start beHospitalizedDate=%s,end beHospitalizedDate=%s",
              tenantAccountService.getCurrentTenantID(), elderlyInfo.getIdentifier(),
              elderlyInfo.getName(), elderlyInfo.getElderlyStatus(), beHospitalizedBeginDate,
              beHospitalizedEndDate);
    }
    return search(query, pageable, analyzer, beHospitalizedDateFilter);

  }



}
