package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.TenantUserDao;
import com.yly.entity.TenantUser;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.TenantUserService;

@Service("tenantUserServiceImpl")
public class TenantUserServiceImpl extends BaseServiceImpl<TenantUser, Long> implements TenantUserService {

  @Resource(name = "tenantUserDaoImpl")
  private TenantUserDao tenantUserDao;
  
  @Resource
  public void setBaseDao(TenantUserDao tenantUserDao) {
    super.setBaseDao(tenantUserDao);
  }



}
