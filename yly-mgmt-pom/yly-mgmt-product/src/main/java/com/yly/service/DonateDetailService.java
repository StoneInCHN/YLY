package com.yly.service;

import com.yly.entity.DonateDetail;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.framework.service.BaseService;

public interface DonateDetailService extends BaseService<DonateDetail, Long> {

  public Page<DonateDetail> findDonateDetailByRecordId(Pageable pageable,Long recordId);
} 
