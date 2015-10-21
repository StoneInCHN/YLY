package com.yly.service;

import java.io.Serializable;

import com.yly.beans.QueryParam;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.framework.service.BaseService;



public interface ChargeRecordService<T, ID extends Serializable> extends BaseService<T, ID> {

//  /**
//   * 缴费记录查询
//   * @param isTenant
//   * @param beginDate
//   * @param endDate
//   * @param realName
//   * @param identifier
//   * @param status
//   * @param budgetType
//   * @param isPeriod
//   * @param pageable
//   * @return
//   */
//  Page<T> chargeRecordSearch(Boolean isTenant,Date beginDate, Date endDate,
//      String realName, String identifier,PaymentStatus status,BudgetType budgetType,Boolean isPeriod,Pageable pageable);
  
  /**
   * 缴费记录查询
   * @param queryParam
   * @param pageable
   * @return
   */
  Page<T> chargeRecordSearch(QueryParam queryParam,Pageable pageable);
}
