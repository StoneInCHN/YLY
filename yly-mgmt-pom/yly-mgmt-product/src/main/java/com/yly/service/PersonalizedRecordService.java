package com.yly.service;

import java.util.List;

import com.yly.entity.PersonalizedRecord;
import com.yly.framework.service.BaseService;
import com.yly.json.request.ChargeSearchRequest;

public interface PersonalizedRecordService extends BaseService<PersonalizedRecord, Long> {
  
  public List<PersonalizedRecord> searchRecentRecords(ChargeSearchRequest chargeSearch);
  
}
