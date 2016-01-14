package com.yly.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.yly.dao.PersonalizedChargeDao;
import com.yly.entity.PersonalizedCharge;
import com.yly.entity.PersonalizedRecord;
import com.yly.entity.commonenum.CommonEnum.PaymentStatus;
import com.yly.json.request.WaterElectricitySearchRequest;
import com.yly.service.PersonalizedChargeService;
import com.yly.utils.DateTimeUtils;

/**
 * 个性化服务收费记录
 * @author sujinxuan
 *
 */
@Service("personalizedChargeServiceImpl")
public class PersonalizedChargeServiceImpl extends ChargeRecordServiceImpl<PersonalizedCharge, Long> implements PersonalizedChargeService {

  @Resource(name = "personalizedChargeDaoImpl")
  private PersonalizedChargeDao personalizedChargeDao;
  
  @Resource
  public void setBaseDao(PersonalizedChargeDao personalizedChargeDao) {
    super.setBaseDao(personalizedChargeDao);
  }
  /**
   * 根据搜索条件，返回查询结果个数
   */
  @Override
  public int countByFilter(WaterElectricitySearchRequest waterElectricitySearch) {
    IKAnalyzer analyzer = new IKAnalyzer();
    analyzer.setMaxWordLength(true);
    try {
      BooleanQuery query = getQuery(analyzer, waterElectricitySearch);
      
      return personalizedChargeDao.count(query, analyzer, null);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    return 0;
  }
  /**
   * 根据查询条件中的 老人编号，老人姓名，订单状态，收费时间段，返回带条件的查询Query
   * @return
   */
  private BooleanQuery getQuery(IKAnalyzer analyzer,
      WaterElectricitySearchRequest waterElectricitySearch) {
    BooleanQuery query = new BooleanQuery();
    try {
      Long tenantId = tenantAccountService.getCurrentTenantID();
      if (tenantId != null) {
        Term term = new Term("tenantID", tenantId.toString());
        Query filterQuery = new TermQuery(term);
        query.add(filterQuery, Occur.MUST);
      }

      if (waterElectricitySearch.getNameHidden() != null) {
        String text = QueryParser.escape(waterElectricitySearch.getNameHidden());
        QueryParser filterParser = new QueryParser(Version.LUCENE_35, "elderlyInfo.name", analyzer);
        Query filterQuery = filterParser.parse(text);
        query.add(filterQuery, Occur.MUST);
      }
      if (waterElectricitySearch.getIdentifierHidden() != null) {
        String text = QueryParser.escape(waterElectricitySearch.getIdentifierHidden());
        QueryParser filterParser =
            new QueryParser(Version.LUCENE_35, "elderlyInfo.identifier", analyzer);
        Query filterQuery = filterParser.parse(text);
        query.add(filterQuery, Occur.MUST);
      }
      if (waterElectricitySearch.getStatusHidden() != null) {
        Term term = new Term("chargeStatus", waterElectricitySearch.getStatusHidden());
        Query filterQuery = new TermQuery(term);
        query.add(filterQuery, Occur.MUST);
      }
      if (waterElectricitySearch.getBeginDateHidden() != null || waterElectricitySearch.getEndDateHidden() != null) {
        Date beginDateHidden = waterElectricitySearch.getBeginDateHidden();
        Date endDateHidden = waterElectricitySearch.getEndDateHidden();
        TermRangeQuery tQuery = new TermRangeQuery("periodStartDate", DateTimeUtils.convertDateToString(
                beginDateHidden, null), null, true, false);
        query.add(tQuery, Occur.MUST);
        tQuery = new TermRangeQuery("periodEndDate", null, DateTimeUtils.convertDateToString(
                endDateHidden, null), false, false);
        query.add(tQuery, Occur.MUST);
      }
      }catch (Exception e) {
        e.printStackTrace();
      }
    return query;
  }
  /**
   * 根据搜索条件，返回查询结果List
   */
  @Override
  public List<PersonalizedCharge> searchListByFilter(
      WaterElectricitySearchRequest waterElectricitySearch) {
    IKAnalyzer analyzer = new IKAnalyzer();
    analyzer.setMaxWordLength(true);
    try {
      BooleanQuery query = getQuery(analyzer, waterElectricitySearch);
      
      return personalizedChargeDao.searchList(query, analyzer, null);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
  /**
   * 准备数据，将list转化成HashMap,作为需要导出的数据
   * @return
   */
  @Override
  public List<Map<String, String>> prepareMap(List<PersonalizedCharge> personalizedChargeList) {
    
    List<Map<String, String>> mapList = new ArrayList<Map<String,String>>();
    for (PersonalizedCharge personalizedCharge : personalizedChargeList) {
      Map<String, String> personalizedChargeMap = new HashMap<String, String>();
      if (personalizedCharge.getElderlyInfo()!=null) {
        personalizedChargeMap.put("elderlyInfo.name", personalizedCharge.getElderlyInfo().getName());
        personalizedChargeMap.put("elderlyInfo.identifier", personalizedCharge.getElderlyInfo().getIdentifier());
        personalizedChargeMap.put("elderlyInfo.bedLocation", personalizedCharge.getElderlyInfo().getBedLocation());
        if (personalizedCharge.getElderlyInfo().getNursingLevel() != null) {
          personalizedChargeMap.put("elderlyInfo.nursingLevel.configValue", personalizedCharge.getElderlyInfo().getNursingLevel().getConfigValue());
        }else {
          personalizedChargeMap.put("elderlyInfo.nursingLevel.configValue", "");
        }
        
      }else {
        personalizedChargeMap.put("elderlyInfo.name", "");
        personalizedChargeMap.put("elderlyInfo.identifier", "");
        personalizedChargeMap.put("elderlyInfo.bedLocation", "");
        personalizedChargeMap.put("elderlyInfo.nursingLevel.configValue", "");
      }
      if (personalizedCharge.getPersonalizedAmount()!=null) {
        personalizedChargeMap.put("personalizedAmount", personalizedCharge.getPersonalizedAmount().toString());
      }else {
        personalizedChargeMap.put("personalizedAmount", "");
      }

      personalizedChargeMap.put("operator", personalizedCharge.getOperator());
      if (personalizedCharge.getPeriodStartDate()!=null) {
        personalizedChargeMap.put("periodStartDate", DateTimeUtils.getSimpleFormatString(DateTimeUtils.shortDateFormat, personalizedCharge.getPeriodStartDate()));
      }else {
        personalizedChargeMap.put("periodStartDate", "");
      }
      
      if(personalizedCharge.getChargeStatus() == PaymentStatus.PAID){
        personalizedChargeMap.put("chargeStatus", "已缴费");
      }else if(personalizedCharge.getChargeStatus() == PaymentStatus.UNPAID){
        personalizedChargeMap.put("chargeStatus", "未缴费");
      }else if(personalizedCharge.getChargeStatus() == PaymentStatus.UNPAID_ADJUSTMENT){
        personalizedChargeMap.put("chargeStatus", "未缴费(调账)");
      }else if(personalizedCharge.getChargeStatus() == PaymentStatus.REFUNDED){
        personalizedChargeMap.put("chargeStatus", "已退款");
      }

      mapList.add(personalizedChargeMap);
    }
    return mapList;
  
  }
  @Override
  public List<Map<String, Object>> getServiceDetailsByBill(PersonalizedCharge record) {
    if (record == null) {
      return null;
    }
    List<Map<String, Object>> serviceDetails = new ArrayList<Map<String,Object>>();
    for(PersonalizedRecord personalizedRecord:record.getPersonalizedRecords()){
      Boolean flag=false;
      Map<String, Object> serviceItem = new HashMap<String, Object>();
      for(Map<String , Object> map:serviceDetails){
        if (map.get("serviceName").equals(personalizedRecord.getNurseContent())) {
            map.put("serviceCount", ((BigDecimal)map.get("serviceCount")).add(new BigDecimal(1)));
            map.put("serviceAmount", ((BigDecimal)map.get("serviceUnitPrice")).multiply((BigDecimal)map.get("serviceCount")));
            flag=true;
            break;
        }
      }
      if (flag) {
        continue;
      }
      serviceItem.put("serviceName", personalizedRecord.getNurseContent());
      serviceItem.put("serviceUnitPrice", personalizedRecord.getPersonalizedNurse().getServicePrice());
      serviceItem.put("serviceAmount", personalizedRecord.getPersonalizedNurse().getServicePrice());
      serviceItem.put("serviceCount", new BigDecimal(1));
      serviceDetails.add(serviceItem);
    }
    return serviceDetails;
  }
}
