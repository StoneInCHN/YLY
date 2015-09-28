package com.yly.service;

import com.yly.entity.DrugsInfo;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.framework.service.BaseService;

public interface DrugsInfoService extends BaseService<DrugsInfo, Long> {

  Page<DrugsInfo> search (String keyword, Pageable pageable);
  
  void refreshIndex();
}
