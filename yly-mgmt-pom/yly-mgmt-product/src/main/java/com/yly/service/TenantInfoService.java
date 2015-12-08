package com.yly.service;

import java.util.List;
import java.util.Set;

import com.yly.entity.ConfigMeta;
import com.yly.entity.TenantInfo;
import com.yly.entity.VersionConfig;
import com.yly.framework.service.BaseService;

/**
 * 租户信息
 * 
 * @author shijun
 *
 */
public interface TenantInfoService extends BaseService<TenantInfo, Long> {

  /**
   * 通过机构代码找租户
   * 
   * @param orgCode
   * @return
   */
  public TenantInfo findTenantWithOrgCode(String orgCode);
  /**
   * 获取当前用户版本的功能包
   * @return
   */
  Set<ConfigMeta> getCurrentTenantVersionPackage();

}
