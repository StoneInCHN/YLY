package com.yly.service;

import com.yly.entity.OperationLog;
import com.yly.framework.service.BaseService;

/**
 * 日志
 */
public interface OperationLogService extends BaseService<OperationLog, Long> {

  /**
   * 清空日志
   */
  void clear();

}
