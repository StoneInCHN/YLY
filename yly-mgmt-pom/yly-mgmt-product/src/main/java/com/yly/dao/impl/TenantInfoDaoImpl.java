package com.yly.dao.impl;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.yly.dao.TenantInfoDao;
import com.yly.entity.TenantInfo;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * 租户信息
 * 
 * @author shijun
 *
 */
@Repository("tenantInfoDaoImpl")
public class TenantInfoDaoImpl extends BaseDaoImpl<TenantInfo, Long> implements TenantInfoDao {

  public TenantInfo findTenantWithOrgCode(String orgCode) {
    try {
      String jpql =
          "select tenantInfo from TenantInfo tenantInfo where tenantInfo.orgCode = :orgCode";
      return entityManager.createQuery(jpql, TenantInfo.class).setFlushMode(FlushModeType.COMMIT)
          .setParameter("orgCode", orgCode).getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }
}
