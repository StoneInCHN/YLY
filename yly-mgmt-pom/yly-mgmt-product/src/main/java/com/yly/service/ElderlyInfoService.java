package com.yly.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yly.entity.ElderlyInfo;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.framework.service.BaseService;
import com.yly.json.request.ElderlyInfoSearchRequest;

/**
 * 老人信息
 * 
 * @author shijun
 *
 */
public interface ElderlyInfoService extends BaseService<ElderlyInfo, Long> {

  /**
   * 老人查询
   * 
   * @param beHospitalizedBeginDate
   * @param beHospitalizedEndDate
   * @param elderlyInfo
   * @param pageable
   * @return
   */
  Page<ElderlyInfo> searchElderlyInfo(Date beHospitalizedBeginDate, Date beHospitalizedEndDate,
      ElderlyInfo elderlyInfo, Pageable pageable);
  
  List<ElderlyInfo> findByElderlyName(String nameKeys);

  List<Map<String, String>> prepareMap(List<ElderlyInfo> elderlyInfoList);

  int countByFilter(ElderlyInfoSearchRequest elderlyInfoSearch);

  List<ElderlyInfo> searchListByFilter(ElderlyInfoSearchRequest elderlyInfoSearch);

}
