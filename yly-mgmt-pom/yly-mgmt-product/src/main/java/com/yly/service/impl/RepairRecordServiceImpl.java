package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.yly.dao.RepairRecordDao;
import com.yly.entity.RepairRecord;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.RepairRecordService;

/**
 * Service 维修记录
 * @author pengyanan
 *
 */
@Repository("repairRecordServiceImpl")
public class RepairRecordServiceImpl extends BaseServiceImpl<RepairRecord, Long> implements RepairRecordService  {

  @Resource(name="repairRecordDaoImpl")
  private RepairRecordDao repairRecordDao;
  
  @Resource
  private void setBaseDao(RepairRecordDao repairRecordDao){
    super.setBaseDao(repairRecordDao);
  }
}
