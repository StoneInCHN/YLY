package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.TenantInfoDao;
import com.yly.entity.TenantInfo;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.TenantInfoService;

/**
 * 租户信息
 * 
 * @author shijun
 *
 */
@Service("tenantInfoServiceImpl")
public class TenantInfoServiceImpl extends BaseServiceImpl<TenantInfo, Long> implements
    TenantInfoService {

  
  @Resource(name = "tenantInfoDaoImpl")
  private TenantInfoDao tenantInfoDao;
  
  @Override
  public TenantInfo findTenantWithOrgCode(String orgCode) {
    return tenantInfoDao.findTenantWithOrgCode(orgCode);
  }
}
