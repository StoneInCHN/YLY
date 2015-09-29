package com.yly.dao.impl;

import javax.persistence.FlushModeType;

import org.springframework.stereotype.Repository;

import com.yly.dao.OperationLogDao;
import com.yly.entity.OperationLog;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * 日志
 * 
 * @author shijun
 *
 */
@Repository("operationLogDaoImpl")
public class OperationLogDaoImpl extends BaseDaoImpl<OperationLog, Long> implements OperationLogDao {

  public void removeAll() {
    String jpql = "delete from Log log";
    entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT).executeUpdate();
  }

}
