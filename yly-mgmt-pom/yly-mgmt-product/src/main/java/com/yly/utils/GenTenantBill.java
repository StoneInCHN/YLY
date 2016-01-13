package com.yly.utils;

import java.util.Date;

import javax.annotation.Resource;

import com.yly.service.BillingService;

public class GenTenantBill extends Thread {
  
      @Resource(name = "billServiceImpl")
      private BillingService billingService;
    
      public Date billDate;
      
      public Long tenantId;
      
      public GenTenantBill(Date billDate,Long tenantId){
        this.billDate = billDate;
        this.tenantId = tenantId;
      }

      @Override
      public void run() {
        billingService.genBillByTenantBillDate(billDate,tenantId);
      }
      
}
