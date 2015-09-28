package com.yly.dao;

import com.yly.entity.OperationLog;
import com.yly.framework.dao.BaseDao;

/**
 * 日志
 * 
 * @author shijun
 *
 */
public interface OperationLogDao extends BaseDao<OperationLog, Long> {

  /**
   * 删除所有日志
   */
  void removeAll();

}
