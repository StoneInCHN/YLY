package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.ConfigTypeDao;
import com.yly.entity.ConfigType;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.ConfigTypeService;

@Service("configTypeServiceImpl")
public class ConfigTypeServiceImpl extends BaseServiceImpl<ConfigType, Long> implements ConfigTypeService {

  @Resource(name = "configTypeDaoImpl")
  private ConfigTypeDao configTypeDao;
  
  @Resource
  public void setBaseDao(ConfigTypeDao configTypeDao) {
    super.setBaseDao(configTypeDao);
  }

}
