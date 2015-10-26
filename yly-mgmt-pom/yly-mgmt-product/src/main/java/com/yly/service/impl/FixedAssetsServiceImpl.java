package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.FixedAssetsDao;
import com.yly.entity.FixedAssets;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.FixedAssetsService;

@Service("fixedAssetsServiceImpl")
public class FixedAssetsServiceImpl extends BaseServiceImpl<FixedAssets, Long> implements FixedAssetsService {

  @Resource(name = "fixedAssetsDaoImpl")
  private FixedAssetsDao fixedAssetsDao;
  
  @Resource
  public void setBaseDao(FixedAssetsDao fixedAssetsDao) {
    super.setBaseDao(fixedAssetsDao);
  }

}
