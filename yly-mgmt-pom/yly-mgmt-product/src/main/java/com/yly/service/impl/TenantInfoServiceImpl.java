package com.yly.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executor;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.TenantInfoDao;
import com.yly.entity.ConfigMeta;
import com.yly.entity.SystemConfig;
import com.yly.entity.TenantInfo;
import com.yly.entity.commonenum.CommonEnum.ConfigKey;
import com.yly.framework.filter.Filter;
import com.yly.framework.filter.Filter.Operator;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.BillingService;
import com.yly.service.SystemConfigService;
import com.yly.service.TenantAccountService;
import com.yly.service.TenantInfoService;
import com.yly.utils.DateTimeUtils;
import com.yly.utils.GenTenantBill;

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

  @Resource(name = "tenantInfoDaoImpl")
  public void setBaseDao(TenantInfoDao tenantInfoDao) {
    super.setBaseDao(tenantInfoDao);
  }

  @Resource(name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;

  @Resource(name = "threadPoolExecutor")
  private Executor threadPoolExecutor;

  @Resource(name = "systemConfigServiceImpl")
  private SystemConfigService systemConfigService;

  @Resource(name = "billingServiceImpl")
  private BillingService billingService;

  @Override
  public void genMonthlyBill() {
    List<Filter> filters = new ArrayList<Filter>();
    Filter keyFilter = new Filter("configKey", Operator.eq, ConfigKey.BILLDAY);
    Filter enableFilter = new Filter("isEnabled", Operator.eq, true);
    filters.add(enableFilter);
    filters.add(keyFilter);
    List<SystemConfig> systemConfigs = systemConfigService.findList(null, filters, null);
    Date now = DateTimeUtils.getDayStart(new Date());
    Calendar c = Calendar.getInstance();
    c.setTime(now);
    for (SystemConfig config : systemConfigs) {
      Integer billDay = Integer.parseInt(config.getConfigValue());
      if (billDay == c.get(Calendar.DAY_OF_MONTH)) {
        GenTenantBill ex = new GenTenantBill(c.getTime(), config.getTenantID(), billingService);
        threadPoolExecutor.execute(ex);
      }
    }

  }

  @Override
  public TenantInfo findTenantWithOrgCode(String orgCode) {
    return tenantInfoDao.findTenantWithOrgCode(orgCode);
  }

  /**
   * 获取租户的功能包
   */
  @Override
  public Set<ConfigMeta> getCurrentTenantVersionPackage() {
    TenantInfo tenantInfo = tenantAccountService.getCurrentTenantInfo();
    tenantInfo = tenantInfoDao.find(tenantInfo.getId());
    if (tenantInfo != null) {
      return tenantInfo.getVersionConfig().getConfigMeta();
    }
    return null;
  }
}
