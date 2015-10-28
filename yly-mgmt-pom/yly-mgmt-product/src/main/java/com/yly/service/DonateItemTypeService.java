package com.yly.service;

import java.util.List;
import java.util.Map;

import com.yly.entity.DonateItemType;
import com.yly.framework.ordering.Ordering.Direction;
import com.yly.framework.service.BaseService;

public interface DonateItemTypeService extends BaseService<DonateItemType, Long> {
  /**
   * 物品类型
   * @param configKey
   * @param direction
   * @return
   */
  List<Map<String, Object>> findAllDonateItemTypes(Direction direction);
  
} 
