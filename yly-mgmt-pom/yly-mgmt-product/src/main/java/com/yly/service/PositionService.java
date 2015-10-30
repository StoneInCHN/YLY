package com.yly.service;

import java.util.List;
import java.util.Map;

import com.yly.entity.Position;
import com.yly.framework.service.BaseService;


/**
 * 职位
 * @author huyong
 *
 */
public interface PositionService extends BaseService<Position, Long> {
  /**
   * 获取所有部门，用于下拉菜单
   * @return
   */
  List<Map<String, Object>> findAllPositions();
}
