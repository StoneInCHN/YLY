package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.BedNurseChargeDao;
import com.yly.entity.BedNurseCharge;
import com.yly.service.BedNurseChargeService;

/**
 * 床位护理收费记录
 * @author sujinxuan
 *
 */
@Service("bedNurseChargeServiceImpl")
public class BedNurseChargeServiceImpl extends ChargeRecordServiceImpl<BedNurseCharge, Long> implements BedNurseChargeService {

  @Resource(name = "bedNurseChargeDaoImpl")
  private BedNurseChargeDao bedNurseChargeDao;
  
  @Resource
  public void setBaseDao(BedNurseChargeDao bedNurseChargeDao) {
    super.setBaseDao(bedNurseChargeDao);
  }

}
