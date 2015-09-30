package com.yly.service;

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

  List<Map<String, Object>> findByConfigKey(ConfigKey configKey,Direction direction);
  
}
