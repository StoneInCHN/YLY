package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.NurseLevelChangeRecordDao;
import com.yly.entity.NurseLevelChangeRecord;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.NurseLevelChangeRecordService;

@Service("nurseLevelChangeRecordServiceImpl")
public class NurseLevelChangeRecordServiceImpl extends
    BaseServiceImpl<NurseLevelChangeRecord, Long> implements NurseLevelChangeRecordService {

  @Resource(name = "nurseLevelChangeRecordDaoImpl")
  private NurseLevelChangeRecordDao nurseLevelChangeRecordDao;

  @Resource(name = "nurseLevelChangeRecordDaoImpl")
  public void setBaseDao(NurseLevelChangeRecordDao nurseLevelChangeRecordDao) {
    super.setBaseDao(nurseLevelChangeRecordDao);
  }


}
