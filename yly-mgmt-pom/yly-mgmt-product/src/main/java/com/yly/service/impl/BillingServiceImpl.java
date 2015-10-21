package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.BillingDao;
import com.yly.entity.Billing;
import com.yly.service.BillingService;

/**
 * 日常缴费账单
 * @author sujinxuan
 *
 */
@Service("billingServiceImpl")
public class BillingServiceImpl extends ChargeRecordServiceImpl<Billing, Long> implements BillingService {

  @Resource(name = "billingDaoImpl")
  private BillingDao billingDao;
  
  @Resource
  public void setBaseDao(BillingDao billingDao) {
    super.setBaseDao(billingDao);
  }

}
