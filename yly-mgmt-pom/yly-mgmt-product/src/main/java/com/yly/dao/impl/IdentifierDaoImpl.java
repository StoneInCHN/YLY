package com.yly.dao.impl;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.yly.dao.IdentifierDao;
import com.yly.entity.Identifier;
import com.yly.entity.commonenum.CommonEnum.IdentifierType;
import com.yly.utils.FreemarkerUtils;

import freemarker.template.TemplateException;

/**
 * 人员编号
 * 
 * @author shijun
 *
 */
@Repository("identifierDaoImpl")
public class IdentifierDaoImpl implements IdentifierDao {

  @Value("${identifier.elderlyinfo.defaultMaxLo}")
  private int DEFAULT_MAXLO;

  private HiloOptimizer elderlyInfoIdOptimizer = new HiloOptimizer(
      IdentifierType.ELDERLYINFO_IDENTIFIER, DEFAULT_MAXLO);

  @PersistenceContext
  private EntityManager entityManager;

  public String generate(IdentifierType idType) {
    Assert.notNull(idType);
    if (idType == IdentifierType.ELDERLYINFO_IDENTIFIER) {
      return elderlyInfoIdOptimizer.generate();
    }
    return null;
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

  /**
   * 高低位算法
   */
  private class HiloOptimizer {

    private IdentifierType idType;
    private String prefix;
    private int maxLo;
    private int lo;
    private long hi;
    private long lastValue;

    @SuppressWarnings("unused")
    public HiloOptimizer(IdentifierType idType, String prefix, int maxLo) {
      this.idType = idType;
      this.prefix = prefix != null ? prefix.replace("{", "${") : "";
      this.maxLo = maxLo;
      this.lo = maxLo + 1;
    }

    public HiloOptimizer(IdentifierType idType, int maxLo) {
      this.idType = idType;
      this.maxLo = maxLo;
      this.lo = maxLo + 1;
    }

    public synchronized String generate() {
      if (lo > maxLo) {
        lastValue = getLastValue(idType);
        lo = lastValue == 0 ? 1 : 0;
        hi = lastValue * (maxLo + 1);
      }
      try {
        if (prefix != null) {
          return FreemarkerUtils.process(prefix, null) + (hi + lo++);
        } else {
          return String.valueOf(hi + lo++);
        }

      } catch (IOException e) {
        e.printStackTrace();
      } catch (TemplateException e) {
        e.printStackTrace();
      }
      return String.valueOf(hi + lo++);
    }
  }
}
