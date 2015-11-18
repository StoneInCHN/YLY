package com.yly.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yly.entity.PhysicalExaminationItemConfig;
import com.yly.entity.commonenum.CommonEnum.ConfigKey;
import com.yly.framework.ordering.Ordering.Direction;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.framework.service.BaseService;

public interface PhysicalExaminationItemConfigService extends BaseService<PhysicalExaminationItemConfig, Long> {
  List<Map<String, Object>> findByConfigKey(ConfigKey configKey,Direction direction);
  Page<PhysicalExaminationItemConfig> searchByConfigKey(ConfigKey configKey,Pageable pageable,Date beginDate,Date endDate,String searchItemName);
}
