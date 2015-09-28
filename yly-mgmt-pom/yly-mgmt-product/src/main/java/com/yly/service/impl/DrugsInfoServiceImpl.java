package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.DrugsInfoDao;
import com.yly.entity.DrugsInfo;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.DrugsInfoService;

@Service("drugsInfoServiceImpl")
public class DrugsInfoServiceImpl extends BaseServiceImpl<DrugsInfo, Long> implements DrugsInfoService {

  @Resource(name = "drugsInfoDaoImpl")
  private DrugsInfoDao drugsInfoDao;
  
  @Resource
  public void setBaseDao(DrugsInfoDao drugsDao) {
    super.setBaseDao(drugsDao);
  }

  @Override
  public Page<DrugsInfo> search (String keyword, Pageable pageable)
  {
    return drugsInfoDao.search (keyword, pageable);
  }

  @Override
  public void refreshIndex ()
  {
    drugsInfoDao.refreshIndex ();
  }

}
