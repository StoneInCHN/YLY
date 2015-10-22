package com.yly.service;

import java.util.Date;

import com.yly.beans.SearchParameter;
import com.yly.entity.ElderlyStuffDeposit;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.framework.service.BaseService;

/**
 * 物品寄存Service
 * 
 * @author luzhang
 *
 */
public interface ElderlyStuffDepositService extends BaseService<ElderlyStuffDeposit, Long> {
  Page<ElderlyStuffDeposit> searchPageByFilter(SearchParameter searchParameter, Pageable pageable);
}
