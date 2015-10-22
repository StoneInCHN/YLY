package com.yly.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.BillingDao;
import com.yly.entity.BedChargeConfig;
import com.yly.entity.Billing;
import com.yly.entity.ElderlyInfo;
import com.yly.entity.NurseChargeConfig;
import com.yly.framework.filter.Filter;
import com.yly.framework.filter.Filter.Operator;
import com.yly.service.BedChargeConfigService;
import com.yly.service.BillingService;
import com.yly.service.NurseChargeConfigService;
import com.yly.utils.FieldFilterUtils;

/**
 * 日常缴费账单
 * @author sujinxuan
 *
 */
@Service("billingServiceImpl")
public class BillingServiceImpl extends ChargeRecordServiceImpl<Billing, Long> implements BillingService {

  @Resource(name = "billingDaoImpl")
  private BillingDao billingDao;
  
  @Resource(name = "bedChargeConfigServiceImpl")
  private BedChargeConfigService bedChargeConfigService;
  
  @Resource(name = "nurseChargeConfigServiceImpl")
  private NurseChargeConfigService nurseChargeConfigService;
  
  @Resource
  public void setBaseDao(BillingDao billingDao) {
    super.setBaseDao(billingDao);
  }

@Override
public List<Map<String, Object>> getBedNurseConfigByElderly(
		String[] properties, ElderlyInfo elderlyInfo) {
	 List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		if (elderlyInfo.getBed()!=null) {
			List<Filter> filters = new ArrayList<Filter>();
			Filter filter=new Filter("chargeItem",Operator.eq,elderlyInfo.getBed().getRoom().getRoomType());
			filters.add(filter);
			List<BedChargeConfig> bedChargeConfigs = bedChargeConfigService.findList(null, filters, null, true, null);
			if (bedChargeConfigs!=null&&bedChargeConfigs.size()==1) {
				Map<String, Object> bedChargeMap = FieldFilterUtils.filterEntityMap(properties, bedChargeConfigs.get(0));
				list.add(bedChargeMap);
			}
		
		}
		
		if (elderlyInfo.getNursingLevel()!=null) {
			List<Filter> filters = new ArrayList<Filter>();
			Filter filter=new Filter("chargeItem",Operator.eq,elderlyInfo.getNursingLevel());
			filters.add(filter);
			List<NurseChargeConfig> nurseChargeConfigs = nurseChargeConfigService.findList(null, filters, null, true, null);
			if (nurseChargeConfigs!=null&&nurseChargeConfigs.size()==1) {
				Map<String, Object> nurseChargeConfigMap = FieldFilterUtils.filterEntityMap(properties, nurseChargeConfigs.get(0));
				list.add(nurseChargeConfigMap);
			}
		
		}
	return list;
}

}
