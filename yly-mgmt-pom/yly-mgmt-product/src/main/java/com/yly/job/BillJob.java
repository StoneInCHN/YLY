package com.yly.job;

import javax.annotation.Resource;

import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.yly.common.log.LogUtil;
import com.yly.service.BillingService;

@Component("BillJob")
@Lazy(false)
public class BillJob
{
  
  @Resource(name = "billingServiceImpl")
  private BillingService billingService;
  
  @Scheduled(cron="${job.monthly.bill.cron}") 
  public void genMonthlyBill()
  {
    
    LogUtil.debug (BillJob.class, "Bill", "generate monthly bill start!");
    billingService.genMonthlyBill();
    LogUtil.debug (BillJob.class, "Bill", "generate monthly bill end!");
  }
}
