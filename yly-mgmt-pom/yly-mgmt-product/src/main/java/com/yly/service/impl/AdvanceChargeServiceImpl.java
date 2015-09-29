package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.AdvanceChargeDao;
import com.yly.entity.AdvanceCharge;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.AdvanceChargeService;

/**
 * 预存款
 * @author sujinxuan
 *
 */
@Service("advanceChargeServiceImpl")
public class AdvanceChargeServiceImpl extends BaseServiceImpl<AdvanceCharge, Long> implements AdvanceChargeService {

  @Resource(name = "advanceChargeDaoImpl")
  private AdvanceChargeDao advanceChargeDao;
  
  @Resource
  public void setBaseDao(AdvanceChargeDao advanceChargeDao) {
    super.setBaseDao(advanceChargeDao);
  }

}
