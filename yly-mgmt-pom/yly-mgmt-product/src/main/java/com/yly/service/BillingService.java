package com.yly.service;

import java.util.List;
import java.util.Map;

import com.yly.entity.Billing;
import com.yly.entity.ElderlyInfo;


/**
 * 缴费账单
 * @author sujinxuan
 *
 */
public interface BillingService extends ChargeRecordService<Billing, Long> {
	
	/**
	 * 根据老人获取床位护理费用
	 * @param properties
	 * @param elderlyInfo
	 * @return
	 */
	List<Map<String, Object>> getBedNurseConfigByElderly(String[] properties,ElderlyInfo elderlyInfo);

}
