package com.yly.service;

import java.util.Date;
import java.util.List;

import com.yly.entity.Billing;
import com.yly.json.request.ChargeSearchRequest;


public interface CheckoutService {
  
  public List<Billing> searchListByFilter(ChargeSearchRequest chargeSearch);

  public List<Billing> calculateCheckout(List<Billing> filteredBillings, Date checkoutDate);

  public void checkoutBillTransaction(ChargeSearchRequest chargeSearch, Billing checkoutBill);

  public void checkoutBillPayTransaction(Billing checkoutBill)throws Exception;
}
