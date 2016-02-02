package com.yly.service;

import java.util.Date;

import com.yly.entity.NurseArrangement;
import com.yly.entity.NurseLevelChangeRecord;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.framework.service.BaseService;


public interface NurseLevelChangeRecordService extends BaseService<NurseLevelChangeRecord, Long> {

  Page<NurseLevelChangeRecord> searchPageByFilter(Long nurseChangeElderlyID,
      Date nurseChangeStartDate, Date nurseChangeEndDate, String nurseChangeStatus,
      Pageable pageable, Boolean isTenant);
}
