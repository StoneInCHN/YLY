package com.yly.service;

import java.util.Date;

import com.yly.entity.EvaluatingForm;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.framework.service.BaseService;

/**
 * 评估表Service
 * 
 * @author luzhang
 *
 */
public interface EvaluatingFormService extends BaseService<EvaluatingForm, Long> {
  Page<EvaluatingForm> searchPageByFilter(String keysOfElderlyName, Date beginDate,
      Date endDate, Pageable pageable);
}
