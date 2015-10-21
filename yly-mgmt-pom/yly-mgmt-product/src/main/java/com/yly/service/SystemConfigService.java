package com.yly.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yly.entity.SystemConfig;
import com.yly.entity.commonenum.CommonEnum.ConfigKey;
import com.yly.framework.ordering.Ordering.Direction;
import com.yly.framework.service.BaseService;


/**
 * 数据字典
 * @author sujinxuan
 *
 */
public interface SystemConfigService extends BaseService<SystemConfig, Long> {

  /**
   * 获取系统配置
   * @param configKey
   * @param direction
   * @return
   */
  List<Map<String, Object>> findByConfigKey(ConfigKey configKey,Direction direction);
  
  /**
   * 根据当前日期获取下一个结算日期
   * @param currentDate
   * @return
   */
  String getBillingDate(Date currentDate);
  
}
