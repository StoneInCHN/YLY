package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.ElderlyInfoDao;
import com.yly.entity.ElderlyInfo;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.ElderlyInfoService;

/**
 * 老人信息
 * 
 * @author shijun
 *
 */
@Service("elderlyInfoServiceImpl")
public class ElderlyInfoServiceImpl extends BaseServiceImpl<ElderlyInfo, Long> implements
    ElderlyInfoService {
  
  @Resource(name = "elderlyInfoDaoImpl")
  private ElderlyInfoDao elderlyInfoDao;
  
  @Resource
  public void setBaseDao(ElderlyInfoDao elderlyInfoDao){
    super.setBaseDao(elderlyInfoDao);
  }

}
