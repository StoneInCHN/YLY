package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.NurseChargeConfigDao;
import com.yly.entity.NurseChargeConfig;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.NurseChargeConfigService;

/**
 * 护理收费标准配置
 * @author sujinxuan
 *
 */
@Service("nurseChargeConfigServiceImpl")
public class NurseChargeConfigServiceImpl extends BaseServiceImpl<NurseChargeConfig, Long> implements NurseChargeConfigService {

  @Resource(name = "nurseChargeConfigDaoImpl")
  private NurseChargeConfigDao nurseChargeConfigDao;
  
  @Resource
  public void setBaseDao(NurseChargeConfigDao nurseChargeConfigDao) {
    super.setBaseDao(nurseChargeConfigDao);
  }

}
