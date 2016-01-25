package com.yly.service;

import java.util.Date;

import com.yly.entity.NurseArrangement;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.framework.service.BaseService;

/**
 * 护理员安排Service
 * 
 * @author luzhang
 *
 */
public interface NurseArrangementService extends BaseService<NurseArrangement, Long> {
  Page<NurseArrangement> searchPageByFilter(String keysOfElderlyName, Date beginDate,
      Date endDate, Pageable pageable);
}
