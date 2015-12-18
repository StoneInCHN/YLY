package com.yly.service.impl;

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

import com.yly.dao.WaterElectricityChargeDao;
import com.yly.entity.WaterElectricityCharge;
import com.yly.entity.commonenum.CommonEnum.PaymentStatus;
import com.yly.json.request.WaterElectricitySearchRequest;
import com.yly.service.WaterElectricityChargeService;
import com.yly.utils.DateTimeUtils;

/**
 * 水电费收费记录
 * @author sujinxuan
 *
 */
@Service("waterElectricityChargeServiceImpl")
public class WaterElectricityChargeServiceImpl extends ChargeRecordServiceImpl<WaterElectricityCharge, Long> implements WaterElectricityChargeService {

  @Resource(name = "waterElectricityChargeDaoImpl")
  private WaterElectricityChargeDao waterElectricityChargeDao;
  
  @Resource
  public void setBaseDao(WaterElectricityChargeDao waterElectricityChargeDao) {
    super.setBaseDao(waterElectricityChargeDao);
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
      
      return waterElectricityChargeDao.count(query, analyzer, null);
      
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
  public List<WaterElectricityCharge> searchListByFilter(
      WaterElectricitySearchRequest waterElectricitySearch) {
    IKAnalyzer analyzer = new IKAnalyzer();
    analyzer.setMaxWordLength(true);
    try {
      BooleanQuery query = getQuery(analyzer, waterElectricitySearch);
      
      return waterElectricityChargeDao.searchList(query, analyzer, null);
      
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
  public List<Map<String, String>> prepareMap(
      List<WaterElectricityCharge> waterElectricityChargeList) {
    
    List<Map<String, String>> mapList = new ArrayList<Map<String,String>>();
    for (WaterElectricityCharge waterElectricity : waterElectricityChargeList) {
      Map<String, String> waterElectricityMap = new HashMap<String, String>();
      if (waterElectricity.getElderlyInfo()!=null) {
        waterElectricityMap.put("elderlyInfo.name", waterElectricity.getElderlyInfo().getName());
        waterElectricityMap.put("elderlyInfo.identifier", waterElectricity.getElderlyInfo().getIdentifier());
        waterElectricityMap.put("elderlyInfo.bedLocation", waterElectricity.getElderlyInfo().getBedLocation());
        if (waterElectricity.getElderlyInfo().getNursingLevel() != null) {
          waterElectricityMap.put("elderlyInfo.nursingLevel.configValue", waterElectricity.getElderlyInfo().getNursingLevel().getConfigValue());
        }else {
          waterElectricityMap.put("elderlyInfo.nursingLevel.configValue", "");
        }
        
      }else {
        waterElectricityMap.put("elderlyInfo.name", "");
        waterElectricityMap.put("elderlyInfo.identifier", "");
        waterElectricityMap.put("elderlyInfo.bedLocation", "");
        waterElectricityMap.put("elderlyInfo.nursingLevel.configValue", "");
      }
      if (waterElectricity.getWaterAmount()!=null) {
        waterElectricityMap.put("waterAmount", waterElectricity.getWaterAmount().toString());
      }else {
        waterElectricityMap.put("waterAmount", "");
      }
      if (waterElectricity.getElectricityAmount()!=null) {
        waterElectricityMap.put("electricityAmount", waterElectricity.getElectricityAmount().toString());
      }else {
        waterElectricityMap.put("electricityAmount", "");
      }
      if (waterElectricity.getTotalAmount()!=null) {
        waterElectricityMap.put("totalAmount", waterElectricity.getTotalAmount().toString());
      }else {
        waterElectricityMap.put("totalAmount", "");
      }
     
      waterElectricityMap.put("operator", waterElectricity.getOperator());
      if (waterElectricity.getPeriodStartDate()!=null) {
        waterElectricityMap.put("periodStartDate", DateTimeUtils.getSimpleFormatString(DateTimeUtils.shortDateFormat, waterElectricity.getPeriodStartDate()));
      }else {
        waterElectricityMap.put("periodStartDate", "");
      }
      
      if(waterElectricity.getChargeStatus() == PaymentStatus.PAID){
        waterElectricityMap.put("chargeStatus", "已缴费");
      }else if(waterElectricity.getChargeStatus() == PaymentStatus.UNPAID){
        waterElectricityMap.put("chargeStatus", "未缴费");
      }else if(waterElectricity.getChargeStatus() == PaymentStatus.UNPAID_ADJUSTMENT){
        waterElectricityMap.put("chargeStatus", "未缴费(调账)");
      }else if(waterElectricity.getChargeStatus() == PaymentStatus.REFUNDED){
        waterElectricityMap.put("chargeStatus", "已退款");
      }

      mapList.add(waterElectricityMap);
    }
    return mapList;
  
  }

}
