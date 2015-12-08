package com.yly.service.impl;

import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.TenantInfoDao;
import com.yly.entity.ConfigMeta;
import com.yly.entity.TenantInfo;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.ConfigMetaService;
import com.yly.service.TenantAccountService;
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
  
  @Resource(name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;
  @Override
  public TenantInfo findTenantWithOrgCode(String orgCode) {
    return tenantInfoDao.findTenantWithOrgCode(orgCode);
  }
  
  /**
   * 获取租户的功能包
   */
  @Override
  public Set<ConfigMeta> getCurrentTenantVersionPackage ()
  {
    TenantInfo tenantInfo = tenantAccountService.getCurrentTenantInfo ();
    tenantInfo=tenantInfoDao.find (tenantInfo.getId ());
    if (tenantInfo != null)
    {
      return tenantInfo.getVersionConfig ().getConfigMeta ();
    }
    return null;
  }
}
