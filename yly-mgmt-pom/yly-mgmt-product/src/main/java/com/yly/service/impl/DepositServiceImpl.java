package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.DepositDao;
import com.yly.entity.Deposit;
import com.yly.service.DepositService;

/**
 * 押金
 * @author sujinxuan
 *
 */
@Service("depositServiceImpl")
public class DepositServiceImpl extends ChargeRecordServiceImpl<Deposit, Long> implements DepositService {

  @Resource(name = "depositDaoImpl")
  private DepositDao depositDao;
  
  @Resource
  public void setBaseDao(DepositDao depositDao) {
    super.setBaseDao(depositDao);
  }

}
