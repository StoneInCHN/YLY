package com.yly.service;

import java.util.Date;

import com.yly.entity.ElderlyStuffDeposit;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.framework.service.BaseService;

/**
 * 物品寄存Service
 * @author luzhang
 *
 */
public interface ElderlyStuffDepositService extends BaseService<ElderlyStuffDeposit, Long> {
    Page<ElderlyStuffDeposit> searchPageByFilter(String keysOfStuffName,String keysOfStuffNumber,String keysOfElderlyName,Date beginDate, Date endDate,Pageable pageable);
}
