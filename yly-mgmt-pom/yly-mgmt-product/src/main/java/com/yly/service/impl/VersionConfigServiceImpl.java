package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.VersionConfigDao;
import com.yly.entity.VersionConfig;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.ConfigMetaService;
import com.yly.service.VersionConfigService;

@Service ("versionConfigServiceImpl")
public class VersionConfigServiceImpl extends
    BaseServiceImpl<VersionConfig, Long> implements VersionConfigService
{

  @Resource (name = "versionConfigDaoImpl")
  private VersionConfigDao versionConfigDao;
  @Resource(name = "configMetaServiceImpl")
  private ConfigMetaService configMetaService;
  
  @Resource
  public void setBaseDao (VersionConfigDao versionConfigDao)
  {
    super.setBaseDao (versionConfigDao);
  }

}
