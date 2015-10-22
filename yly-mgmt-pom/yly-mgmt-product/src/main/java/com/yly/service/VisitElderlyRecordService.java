package com.yly.service;

import java.util.Date;

import com.yly.entity.VisitElderlyRecord;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.framework.service.BaseService;

/**
 * 老人探望
 * 
 * @author shijun
 *
 */
public interface VisitElderlyRecordService extends BaseService<VisitElderlyRecord, Long> {

  /**
   * 查询访问记录
   * 
   * @param visitDateBeginDate
   * @param visitDateEndDate
   * @param visitElderlyRecord
   * @param pageable
   * @return
   */
  public Page<VisitElderlyRecord> searchElderlyRecord(Date visitDateBeginDate,
      Date visitDateEndDate, VisitElderlyRecord visitElderlyRecord, Pageable pageable);

}
