package com.yly.service;

import java.util.Date;

import com.yly.entity.EvaluatingItemsOptions;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.framework.service.BaseService;

/**
 * 评估项与选项关系表Service
 * 
 * @author luzhang
 *
 */
public interface EvaluatingItemsOptionsService extends BaseService<EvaluatingItemsOptions, Long> {
  Page<EvaluatingItemsOptions> searchPageByFilter(String keysOfElderlyName, Date beginDate,
      Date endDate, Pageable pageable);
}
