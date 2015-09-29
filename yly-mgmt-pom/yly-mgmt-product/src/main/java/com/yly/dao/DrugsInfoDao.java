package com.yly.dao;

import java.util.Date;

import com.yly.entity.DrugsInfo;
import com.yly.framework.dao.BaseDao;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;

public interface DrugsInfoDao extends BaseDao<DrugsInfo, Long> {

  Page<DrugsInfo> search (String keyword, Pageable pageable,String startDate, String endDate);
  void refreshIndex();
}
