package com.yly.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.yly.dao.IdentifierDao;
import com.yly.entity.Identifier;
import com.yly.entity.commonenum.CommonEnum.IdentifierType;

/**
 * 人员编号
 * 
 * @author shijun
 *
 */
@Repository("identifierDaoImpl")
public class IdentifierDaoImpl implements IdentifierDao{
  
  @PersistenceContext
  private EntityManager entityManager;
  
  public String generate(IdentifierType idType) {
    Assert.notNull(idType);
        
   long id =  getLastValue(idType);
   
   return String.valueOf(id);
  }

  /**
   * 数据库获取具体租户的编号最后值
   * 
   * @param type
   * @return
   */
  private long getLastValue(IdentifierType idType) {
    String jpql = "select identifier from Identifier identifier where identifier.idType = :idType";
    Identifier identifier =
        entityManager.createQuery(jpql, Identifier.class).setFlushMode(FlushModeType.COMMIT)
            .setLockMode(LockModeType.PESSIMISTIC_WRITE).setParameter("idType", idType)
            .getSingleResult();
    long lastValue = identifier.getLastValue();
    identifier.setLastValue(lastValue + 1);
    entityManager.merge(identifier);
    return lastValue;
  }
}
