package com.yly.service;

import com.yly.entity.ElderlyStuffDeposit;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.framework.service.BaseService;
import com.yly.json.request.StuffDepositSearchRequest;

/**
 * 物品寄存Service
 * 
 * @author luzhang
 *
 */
public interface ElderlyStuffDepositService extends BaseService<ElderlyStuffDeposit, Long> {
  Page<ElderlyStuffDeposit> searchPageByFilter(StuffDepositSearchRequest searchParameter, Pageable pageable);
}
