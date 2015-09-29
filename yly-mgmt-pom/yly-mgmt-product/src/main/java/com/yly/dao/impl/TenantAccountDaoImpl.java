package com.yly.dao.impl;

import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yly.dao.TenantAccountDao;
import com.yly.entity.TenantAccount;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * Dao - 租户用户
 * 
 */
@Repository("tenantAccountDaoImpl")
public class TenantAccountDaoImpl extends BaseDaoImpl<TenantAccount, Long> implements
    TenantAccountDao {

  public boolean usernameExists(String username) {
    if (username == null) {
      return false;
    }
    String jpql = "select count(*) from Admin admin where lower(admin.username) = lower(:username)";
    Long count =
        entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT)
            .setParameter("username", username).getSingleResult();
    return count > 0;
  }

  public boolean emailExists(String email) {
    if (email == null) {
      return false;
    }
    String jpql = "select count(*) from Admin admin where lower(admin.email) = lower(:email)";
    Long count =
        entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT)
            .setParameter("email", email).getSingleResult();
    return count > 0;
  }

  @Transactional
  // @Cacheable(value = "admin",key="'username='+#username ")
  public TenantAccount findByUsername(String username) {
    if (username == null) {
      return null;
    }
    try {
      String jpql =
          "select tenantAccount from TenantAccount tenantAccount  where lower(tenantAccount.username) = lower(:username)";
      return entityManager.createQuery(jpql, TenantAccount.class)
          .setFlushMode(FlushModeType.COMMIT).setParameter("username", username).getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }

  public TenantAccount findByNameAndOrgCode(String username, String orgCode) {
    try {
      String jpql =
          "select tenantAccount from TenantAccount tenantAccount,TenantInfo tenantInfo where tenantAccount.tenantID = tenantInfo.id and tenantInfo.orgCode = :orgCode and tenantAccount.userName = :username";
      return entityManager.createQuery(jpql, TenantAccount.class)
          .setFlushMode(FlushModeType.COMMIT).setParameter("username", username)
          .setParameter("orgCode", orgCode).getSingleResult();
    } catch (NoResultException e) {
      return null;
    }
  }

  public List<TenantAccount> findByName(String name) {
    if (name == null) {
      return null;
    }
    try {
      String jpql = "select admin from Admin admin where lower(admin.name) like lower(:name)";
      return entityManager.createQuery(jpql, TenantAccount.class)
          .setFlushMode(FlushModeType.COMMIT).setParameter("name", "%" + name + "%")
          .getResultList();

    } catch (NoResultException e) {
      return null;
    }
  }

  @Transactional
  // @Cacheable(value = "admin",key="'name='+#name ")
  public TenantAccount findByNameAccurate(String name) {
    String jpql = "select admin from Admin admin where lower(admin.name)= lower(:name)";
    try {
      return entityManager.createQuery(jpql, TenantAccount.class)
          .setFlushMode(FlushModeType.COMMIT).setParameter("name", name).getSingleResult();
    } catch (NoResultException e) {
      return null;
    }

  }

  /*
   * @Override
   * 
   * @Transactional
   * 
   * @Cacheable(value = "admin",key="'admin.id='+#id ") public Admin find(Long id) { return
   * super.find(id); }
   * 
   * @Override
   * 
   * @Transactional
   * 
   * @Cacheable(value = "admin",key="'admin.id='+#id +',lockModeType='+#lockModeType") public Admin
   * find(Long id, LockModeType lockModeType) { return super.find(id, lockModeType); }
   * 
   * @Override
   * 
   * @Transactional
   * 
   * @CachePut(value = "admin",key="'admin.id='+#entity.id") public Admin merge(Admin entity) {
   * return super.merge(entity); }
   * 
   * @Override
   * 
   * @Transactional
   * 
   * @CacheEvict(value = "admin",key="'admin.id='+#entity.id") public void remove(Admin entity) {
   * super.remove(entity); }
   */



}
