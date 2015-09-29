package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.PersonalizedChargeDao;
import com.yly.entity.PersonalizedCharge;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.PersonalizedChargeService;

/**
 * 个性化服务收费记录
 * @author sujinxuan
 *
 */
@Service("personalizedChargeServiceImpl")
public class PersonalizedChargeServiceImpl extends BaseServiceImpl<PersonalizedCharge, Long> implements PersonalizedChargeService {

  @Resource(name = "personalizedChargeDaoImpl")
  private PersonalizedChargeDao personalizedChargeDao;
  
  @Resource
  public void setBaseDao(PersonalizedChargeDao personalizedChargeDao) {
    super.setBaseDao(personalizedChargeDao);
  }

}
