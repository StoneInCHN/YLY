package com.yly.service;

import java.util.List;
import java.util.Map;

import com.yly.entity.PersonalizedCharge;
import com.yly.json.request.WaterElectricitySearchRequest;


/**
 * 个性化服务收费记录
 * @author sujinxuan
 *
 */
public interface PersonalizedChargeService extends ChargeRecordService<PersonalizedCharge, Long> {

  int countByFilter(WaterElectricitySearchRequest waterElectricitySearch);

  List<PersonalizedCharge> searchListByFilter(WaterElectricitySearchRequest waterElectricitySearch);

  List<Map<String, String>> prepareMap(List<PersonalizedCharge> personalizedChargeList);

}
