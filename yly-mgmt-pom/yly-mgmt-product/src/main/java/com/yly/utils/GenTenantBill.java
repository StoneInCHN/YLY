package com.yly.utils;

import java.util.Date;

import com.yly.service.BillingService;

public class GenTenantBill extends Thread {


  public BillingService billingService;

  public Date billDate;

  public Long tenantId;

  public GenTenantBill(Date billDate, Long tenantId, BillingService billingService) {
    this.billDate = billDate;
    this.tenantId = tenantId;
    this.billingService = billingService;
  }

  @Override
  public void run() {
    billingService.genBillByTenantBillDate(billDate, tenantId);
  }

}
