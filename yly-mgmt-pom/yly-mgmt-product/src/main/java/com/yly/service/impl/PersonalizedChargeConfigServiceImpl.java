package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.BillingDao;
import com.yly.dao.PersonalizedChargeConfigDao;
import com.yly.entity.PersonalizedChargeConfig;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.PersonalizedChargeConfigService;

/**
 * 个性化服务收费标准配置
 * @author sujinxuan
 *
 */
@Service("personalizedChargeConfigServiceImpl")
public class PersonalizedChargeConfigServiceImpl extends BaseServiceImpl<PersonalizedChargeConfig, Long> implements PersonalizedChargeConfigService {

  @Resource(name = "personalizedChargeConfigDaoImpl")
  private PersonalizedChargeConfigDao personalizedChargeConfigDao;
  
  @Resource
  public void setBaseDao(BillingDao billingDao) {
    super.setBaseDao(personalizedChargeConfigDao);
  }

}
