package com.yly.service;

import java.util.Date;

import com.yly.entity.ConsultationRecord;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.framework.service.BaseService;

/**
 * 咨询
 * 
 * @author shijun
 *
 */
public interface ConsultationService extends BaseService<ConsultationRecord, Long>{
  
  public Page<ConsultationRecord> consultationSearch(Date returnVisitDateBeginDate , Date returnVisitDateEndDate, ConsultationRecord consultationRecord,Pageable pageable);

}
