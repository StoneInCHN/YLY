package com.yly.service;


import com.yly.entity.EvaluatingItems;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.framework.service.BaseService;

/**
 * 题目表Service
 * 
 * @author luzhang
 *
 */
public interface EvaluatingItemsService extends BaseService<EvaluatingItems, Long> {
  Page<EvaluatingItems> searchPageByFilter(String keysOfItemName, Pageable pageable);
}
