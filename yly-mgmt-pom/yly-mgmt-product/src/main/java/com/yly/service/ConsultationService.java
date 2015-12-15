package com.yly.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yly.entity.ConsultationRecord;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.framework.service.BaseService;
import com.yly.json.request.ConsultationRecordSearchRequest;

/**
 * 咨询
 * 
 * @author shijun
 *
 */
public interface ConsultationService extends BaseService<ConsultationRecord, Long>{
  
  public Page<ConsultationRecord> consultationSearch(Date returnVisitDateBeginDate , Date returnVisitDateEndDate, ConsultationRecord consultationRecord,Pageable pageable);

  public int countByFilter(ConsultationRecordSearchRequest consultationSearch);
  
  public List<ConsultationRecord> searchListByFilter(ConsultationRecordSearchRequest consultationSearch);
  
  public List<Map<String, String>> prepareMap(List<ConsultationRecord> consultationList);
}
