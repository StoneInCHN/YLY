package com.yly.service;

import java.util.Date;

import com.yly.entity.EvaluatingSection;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.framework.service.BaseService;

/**
 * 评估模块Service
 * 
 * @author luzhang
 *
 */
public interface EvaluatingSectionService extends BaseService<EvaluatingSection, Long> {
  Page<EvaluatingSection> searchPageByFilter(String keysOfElderlyName, Date beginDate,
      Date endDate, Pageable pageable);
}
