package com.yly.service;

import java.util.Date;

import com.yly.entity.RepairRecord;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.framework.service.BaseService;

/**
 * Service 维修记录
 * 
 * @author pengyanan
 *
 */
public interface RepairRecordService extends BaseService<RepairRecord, Long> {

  /**
   * 通过关键字搜索
   * @param pageable
   * @param begainDate
   * @param endDate
   * @param repairRecord
   * @return
   */
  public Page<RepairRecord> searchListByFilter(Pageable pageable, Date begainDate, Date endDate,
      RepairRecord repairRecord);

}
