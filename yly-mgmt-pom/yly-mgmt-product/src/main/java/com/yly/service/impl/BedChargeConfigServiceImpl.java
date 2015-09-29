package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.BedChargeConfigDao;
import com.yly.entity.BedChargeConfig;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.BedChargeConfigService;

/**
 * 床位收费标准配置
 * @author sujinxuan
 *
 */
@Service("bedChargeConfigServiceImpl")
public class BedChargeConfigServiceImpl extends BaseServiceImpl<BedChargeConfig, Long> implements BedChargeConfigService {

  @Resource(name = "bedChargeConfigDaoImpl")
  private BedChargeConfigDao bedChargeConfigDao;
  
  @Resource
  public void setBaseDao(BedChargeConfigDao bedChargeConfigDao) {
    super.setBaseDao(bedChargeConfigDao);
  }

}
