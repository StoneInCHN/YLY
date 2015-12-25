package com.yly.service;

import java.util.List;
import java.util.Map;

import com.yly.entity.WaterElectricityCharge;
import com.yly.json.request.WaterElectricitySearchRequest;


/**
 * 水电费收费记录
 * @author sujinxuan
 *
 */
public interface WaterElectricityChargeService extends ChargeRecordService<WaterElectricityCharge, Long> {

  int countByFilter(WaterElectricitySearchRequest waterElectricitySearch);

  List<WaterElectricityCharge> searchListByFilter(
      WaterElectricitySearchRequest waterElectricitySearch);

  List<Map<String, String>> prepareMap(List<WaterElectricityCharge> waterElectricityChargeList);

}
