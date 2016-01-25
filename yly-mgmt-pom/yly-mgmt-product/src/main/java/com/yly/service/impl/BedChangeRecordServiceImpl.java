package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.BedChangeRecordDao;
import com.yly.entity.BedChangeRecord;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.BedChangeRecordService;

@Service("bedChangeRecordServiceImpl")
public class BedChangeRecordServiceImpl extends BaseServiceImpl<BedChangeRecord, Long> implements
    BedChangeRecordService {

  @Resource(name = "bedChangeRecordDaoImpl")
  private BedChangeRecordDao bedChangeRecordDao;

  @Resource(name = "bedChangeRecordDaoImpl")
  public void setBaseDao(BedChangeRecordDao bedChangeRecordDao) {
    super.setBaseDao(bedChangeRecordDao);
  }


}
