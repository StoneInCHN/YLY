package com.yly.service;

import java.util.Date;

import com.yly.entity.WaterElectricityRecord;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.framework.service.BaseService;

/**
 * Service - 水电抄表记录
 * 
 * @author pengyanan
 *
 */
public interface WaterElectricityRecordService extends BaseService<WaterElectricityRecord, Long> {

  /**
   * 通过关键字搜索
   * @param pageable
   * @param beginDate
   * @param endDate
   * @param repairRecord
   * @return
   */
  public Page<WaterElectricityRecord> searchListByFilter(Pageable pageable, Date beginDate,
      Date endDate, WaterElectricityRecord waterElectricityRecord);
}
