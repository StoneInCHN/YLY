package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.impl.OperationLogDaoImpl;
import com.yly.entity.OperationLog;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.OperationLogService;

/**
 * 日志
 * 
 * @author shijun
 *
 */
@Service("operationLogServiceImpl")
public class OperationLogServiceImpl extends BaseServiceImpl<OperationLog, Long> implements OperationLogService {

  @Resource(name = "operationLogDaoImpl")
  private OperationLogDaoImpl operationLogDaoImpl;

  @Resource(name = "operationLogDaoImpl")
  public void setBaseDao(OperationLogDaoImpl operationLogDaoImpl) {
    super.setBaseDao(operationLogDaoImpl);
  }

  public void clear() {
    operationLogDaoImpl.removeAll();
  }

}
