package com.yly.service;

import java.util.Date;

import com.yly.entity.NurseArrangementRecord;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.framework.service.BaseService;

/**
 * 护理员安排明细Service
 * 
 * @author luzhang
 *
 */
public interface NurseArrangementRecordService extends BaseService<NurseArrangementRecord, Long> {
  Page<NurseArrangementRecord> searchPageByFilter(String nurseNameSearch, Pageable pageable, Boolean isTenant);
}
