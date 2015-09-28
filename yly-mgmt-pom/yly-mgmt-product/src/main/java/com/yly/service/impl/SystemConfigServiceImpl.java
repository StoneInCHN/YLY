package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.SystemConfigDao;
import com.yly.entity.SystemConfig;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.SystemConfigService;

/**
 * 数据字典
 * @author sujinxuan
 *
 */
@Service("systemConfigServiceImpl")
public class SystemConfigServiceImpl extends BaseServiceImpl<SystemConfig, Long> implements SystemConfigService {

  @Resource(name = "systemConfigDaoImpl")
  private SystemConfigDao systemConfigDao;
  
  @Resource
  public void setBaseDao(SystemConfigDao systemConfigDao) {
    super.setBaseDao(systemConfigDao);
  }

}
