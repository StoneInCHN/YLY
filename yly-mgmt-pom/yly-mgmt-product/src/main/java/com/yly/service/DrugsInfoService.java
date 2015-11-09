package com.yly.service;

import java.util.Date;

import com.yly.entity.DrugsInfo;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.framework.service.BaseService;

public interface DrugsInfoService extends BaseService<DrugsInfo, Long> {

  Page<DrugsInfo> drugsSerach(Date beginDate, Date endDate,
      String drugName, Pageable pageable);
}
