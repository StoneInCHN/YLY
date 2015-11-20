package com.yly.service;

import java.util.Date;

import com.yly.entity.EvaluatingItemOptions;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.framework.service.BaseService;

/**
 * 选项表Service
 * 
 * @author luzhang
 *
 */
public interface EvaluatingItemOptionsService extends BaseService<EvaluatingItemOptions, Long> {
  Page<EvaluatingItemOptions> searchPageByFilter(String keysOfElderlyName, Date beginDate,
      Date endDate, Pageable pageable);
}
