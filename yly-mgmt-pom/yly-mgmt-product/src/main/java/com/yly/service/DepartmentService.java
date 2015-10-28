package com.yly.service;

import java.util.List;
import java.util.Map;

import com.yly.entity.Department;
import com.yly.framework.service.BaseService;


/**
 * 部门
 * @author huyong
 *
 */
public interface DepartmentService extends BaseService<Department, Long> {
  /**
   * 获取所有部门，用于下拉菜单
   * @return
   */
  List<Map<String, Object>> findAllDepartments();
}
