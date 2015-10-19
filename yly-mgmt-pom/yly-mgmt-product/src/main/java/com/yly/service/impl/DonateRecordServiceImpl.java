package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.DonateRecordDao;
import com.yly.entity.DonateRecord;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.DonateRecordService;

@Service("donateRecordServiceImpl")
public class DonateRecordServiceImpl extends BaseServiceImpl<DonateRecord, Long> implements DonateRecordService {

  @Resource(name = "donateRecordDaoImpl")
  private DonateRecordDao donateRecordDao;
  
  @Resource
  public void setBaseDao(DonateRecordDao donateRecordDao) {
    super.setBaseDao(donateRecordDao);
  }



}
