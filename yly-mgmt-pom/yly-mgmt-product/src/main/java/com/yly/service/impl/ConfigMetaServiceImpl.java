package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.ConfigMetaDao;
import com.yly.entity.ConfigMeta;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.ConfigMetaService;

@Service("configMetaServiceImpl")
public class ConfigMetaServiceImpl extends BaseServiceImpl<ConfigMeta, Long> implements ConfigMetaService {

  @Resource(name = "configMetaDaoImpl")
  private ConfigMetaDao configMetaDao;
  
  @Resource
  public void setBaseDao(ConfigMetaDao configMetaDao) {
    super.setBaseDao(configMetaDao);
  }

}
