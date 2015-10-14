package com.yly.framework.service.impl;

import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.ArrayUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.util.Version;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.yly.entity.base.BaseEntity;
import com.yly.entity.commonenum.CommonEnum.PaymentStatus;
import com.yly.framework.dao.BaseDao;
import com.yly.framework.filter.Filter;
import com.yly.framework.filter.Filter.Operator;
import com.yly.framework.ordering.Ordering;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.framework.service.BaseService;
import com.yly.service.TenantAccountService;
import com.yly.utils.DateTimeUtils;
import com.yly.utils.FieldFilterUtils;


@Transactional
public class BaseServiceImpl<T, ID extends Serializable> implements BaseService<T, ID> {

  /** 更新忽略属性 */
  private static final String[] UPDATE_IGNORE_PROPERTIES = new String[] {
      BaseEntity.ID_PROPERTY_NAME, BaseEntity.CREATE_DATE_PROPERTY_NAME,
      BaseEntity.MODIFY_DATE_PROPERTY_NAME};

  @Resource(name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;

  /** baseDao */
  private BaseDao<T, ID> baseDao;

  public void setBaseDao(BaseDao<T, ID> baseDao) {
    this.baseDao = baseDao;
  }

  @Transactional(readOnly = true)
  public T find(ID id) {
    return baseDao.find(id);
  }

  @Transactional(readOnly = true)
  public List<T> findAll() {
    return findList(null, null, null, null);
  }
  
  @Transactional(readOnly = true)
  public List<T> findAll(Boolean isTenant) {
    return findList(null, null, null,isTenant, null);
  }

  @Transactional(readOnly = true)
  public List<T> findList(ID... ids) {
    List<T> result = new ArrayList<T>();
    if (ids != null) {
      for (ID id : ids) {
        T entity = find(id);
        if (entity != null) {
          result.add(entity);
        }
      }
    }
    return result;
  }

  @Transactional(readOnly = true)
  public List<T> findList(Integer count, List<Filter> filters, List<Ordering> orderings) {
    return findList(null, count, filters, orderings);
  }

  @Transactional(readOnly = true)
  public List<T> findList(Integer first, Integer count, List<Filter> filters,
      List<Ordering> orderings) {
    return baseDao.findList(first, count, filters, orderings);
  }

  @Transactional(readOnly = true)
  public Page<T> findPage(Pageable pageable) {
    return baseDao.findPage(pageable);
  }

  @Transactional(readOnly = true)
  public long count() {
    return count(new Filter[] {});
  }

  @Transactional(readOnly = true)
  public long count(Filter... filters) {
    return baseDao.count(filters);
  }

  @Transactional(readOnly = true)
  public boolean exists(ID id) {
    return baseDao.find(id) != null;
  }

  @Transactional(readOnly = true)
  public boolean exists(Filter... filters) {
    return baseDao.count(filters) > 0;
  }

  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public void save(T entity) {
    baseDao.persist(entity);
  }
  
  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public void save(T entity,Boolean isTenant) {
    if (isTenant) {
      FieldFilterUtils.addFieldValue("tenantID", tenantAccountService.getCurrentTenantID(), entity);
    }
    baseDao.persist(entity);
  }

  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public T update(T entity) {
    return baseDao.merge(entity);
  }

  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public T update(T entity, String... ignoreProperties) {
    Assert.notNull(entity);
    if (baseDao.isManaged(entity)) {
      throw new IllegalArgumentException("Entity must not be managed");
    }
    T persistant = baseDao.find(baseDao.getIdentifier(entity));
    if (persistant != null) {
      copyProperties(entity, persistant,
          (String[]) ArrayUtils.addAll(ignoreProperties, UPDATE_IGNORE_PROPERTIES));
      return update(persistant);
    } else {
      return update(entity);
    }
  }

  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public void delete(ID id) {
    delete(baseDao.find(id));
  }

  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public void delete(ID... ids) {
    if (ids != null) {
      for (ID id : ids) {
        delete(baseDao.find(id));
      }
    }
  }

  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public void delete(T entity) {
    baseDao.remove(entity);
  }

  @SuppressWarnings({"unchecked", "rawtypes"})
  private void copyProperties(Object source, Object target, String[] ignoreProperties)
      throws BeansException {
    Assert.notNull(source, "Source must not be null");
    Assert.notNull(target, "Target must not be null");

    PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(target.getClass());
    List<String> ignoreList = (ignoreProperties != null) ? Arrays.asList(ignoreProperties) : null;
    for (PropertyDescriptor targetPd : targetPds) {
      if (targetPd.getWriteMethod() != null
          && (ignoreProperties == null || (!ignoreList.contains(targetPd.getName())))) {
        PropertyDescriptor sourcePd =
            BeanUtils.getPropertyDescriptor(source.getClass(), targetPd.getName());
        if (sourcePd != null && sourcePd.getReadMethod() != null) {
          try {
            Method readMethod = sourcePd.getReadMethod();
            if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
              readMethod.setAccessible(true);
            }
            Object sourceValue = readMethod.invoke(source);
            Object targetValue = readMethod.invoke(target);
            if (sourceValue != null && targetValue != null && targetValue instanceof Collection) {
              Collection collection = (Collection) targetValue;
              collection.clear();
              collection.addAll((Collection) sourceValue);
            } else {
              Method writeMethod = targetPd.getWriteMethod();
              if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                writeMethod.setAccessible(true);
              }
              writeMethod.invoke(target, sourceValue);
            }
          } catch (Throwable ex) {
            throw new FatalBeanException("Could not copy properties from source to target", ex);
          }
        }
      }
    }
  }

  @Transactional(readOnly = true)
  public List<T> findList(Integer count, List<Filter> filters, List<Ordering> orderings,
      Boolean isTenant, String flag) {
    if (isTenant) {
      if(filters == null){
        filters = new ArrayList<Filter>();
      }
      Filter tenantFilter =
          new Filter("tenantID", Operator.eq, tenantAccountService.getCurrentTenantID());
      filters.add(tenantFilter);
    }
    return findList(null, count, filters, orderings);
  }

  @Transactional(readOnly = true)
  public Page<T> findPage(Pageable pageable, Boolean isTenant) {
    if (isTenant) {
      List<Filter> filters = pageable.getFilters();
      Filter tenantFilter =
          new Filter("tenantID", Operator.eq, tenantAccountService.getCurrentTenantID());
      filters.add(tenantFilter);
    }
    return baseDao.findPage(pageable);
  }

  @Override
  public Page<T> search (Query query, Pageable pageable, Analyzer analyzer,org.apache.lucene.search.Filter filter)
  {
   
    return baseDao.search (query, pageable, analyzer,filter);
  }

  @Override
  public void refreshIndex ()
  {
   baseDao.refreshIndex ();    
  }

  @Override
  public Page<T> chargeRecordSearch(Date beginDate, Date endDate, String realName,
      String identifier,PaymentStatus status, Boolean isPeriod,Pageable pageable) {
    
    IKAnalyzer analyzer = new IKAnalyzer();
    analyzer.setMaxWordLength(true);

    try {
      BooleanQuery query = new BooleanQuery();
      if (realName != null) {
        String text = QueryParser.escape(realName);
        QueryParser filterParser = new QueryParser(Version.LUCENE_35, "elderlyInfo.name", analyzer);
        Query filterQuery = filterParser.parse(text);
        query.add(filterQuery,Occur.MUST);
      }
      if (identifier != null) {
        String text = QueryParser.escape(identifier);
        QueryParser filterParser = new QueryParser(Version.LUCENE_35, "elderlyInfo.identifier", analyzer);
        Query filterQuery = filterParser.parse(text);
        query.add(filterQuery,Occur.MUST);
      }
      if (status!=null) {
        String text = QueryParser.escape(status.toString());
        QueryParser filterParser = new QueryParser(Version.LUCENE_35, "chargeStatus", analyzer);
        Query filterQuery = filterParser.parse(text);
        query.add(filterQuery,Occur.MUST);
      }
      if (isPeriod) {
        if (beginDate != null) {
          TermRangeQuery tQuery = new TermRangeQuery ("periodStartDate",DateTimeUtils.convertDateToString (beginDate, null),null, true, false);
          query.add(tQuery, Occur.MUST);
        }
        if (endDate != null) {
          TermRangeQuery tQuery = new TermRangeQuery ("periodEndDate",null, DateTimeUtils.convertDateToString (endDate, null),false, false);
          query.add(tQuery, Occur.MUST);
        }
      }else{
         if (beginDate != null || endDate != null) {
           TermRangeQuery tQuery = new TermRangeQuery ("payTime",DateTimeUtils.convertDateToString (beginDate, null),DateTimeUtils.convertDateToString (endDate, null), true, true);
           query.add(tQuery, Occur.MUST);
        }
      }
      
      return baseDao.search(query, pageable, analyzer,null);
    } catch (Exception e) {
       e.printStackTrace();
    }
    return null;
  }
}
