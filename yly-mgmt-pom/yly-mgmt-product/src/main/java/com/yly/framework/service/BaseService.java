package com.yly.framework.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.search.Query;

import com.yly.entity.commonenum.CommonEnum.PaymentStatus;
import com.yly.framework.filter.Filter;
import com.yly.framework.ordering.Ordering;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;

/**
 * Service 基类
 * 
 * @param <T>
 * @param <ID>
 */
public interface BaseService<T, ID extends Serializable> {

  /**
   * 查找实体对象
   * 
   * @param id ID
   * @return 实体对象，若不存在则返回null
   */
  T find(ID id);

  /**
   * 查找所有实体对象集合
   * 
   * @return 所有实体对象集合
   */
  List<T> findAll();
  
  /**
   * 查找所有实体对象集合
   * @param isTenant 是否按租户查找
   * @return 所有实体对象集合
   */
  List<T> findAll(Boolean isTenant);
  

  /**
   * 查找实体对象集合
   * 
   * @param ids ID
   * @return 实体对象集合
   */
  List<T> findList(ID... ids);

  /**
   * 查找实体对象集合
   * 
   * @param count 数量
   * @param filters 筛选
   * @param orders 排序
   * @return 实体对象集合
   */
  List<T> findList(Integer count, List<Filter> filters, List<Ordering> orders);
  
  /**
   * 查找实体对象集合
   * 
   * @param count 数量
   * @param filters 筛选
   * @param orders 排序
   * @param isTenant 是否按租户查找
   * @return 实体对象集合
   */
  List<T> findList(Integer count, List<Filter> filters, List<Ordering> orders,Boolean isTenant,String flag);

  /**
   * 查找实体对象集合
   * 
   * @param first 起始记录
   * @param count 数量
   * @param filters 筛选
   * @param orders 排序
   * @return 实体对象集合
   */
  List<T> findList(Integer first, Integer count, List<Filter> filters, List<Ordering> orders);

  /**
   * 查找实体对象分页
   * 
   * @param pageable 分页信息
   * @return 实体对象分页
   */
  Page<T> findPage(Pageable pageable);
  
  /**
   * 查找实体对象分页
   * 
   * @param pageable 分页信息
   * @param isTenant 是否按租户查找
   * @return 实体对象分页
   */
  Page<T> findPage(Pageable pageable,Boolean isTenant);

  /**
   * 查询实体对象总数
   * 
   * @return 实体对象总数
   */
  long count();

  /**
   * 查询实体对象数量
   * 
   * @param filters 筛选
   * @return 实体对象数量
   */
  long count(Filter... filters);

  /**
   * 判断实体对象是否存在
   * 
   * @param id ID
   * @return 实体对象是否存在
   */
  boolean exists(ID id);

  /**
   * 判断实体对象是否存在
   * 
   * @param filters 筛选
   * @return 实体对象是否存在
   */
  boolean exists(Filter... filters);

  /**
   * 保存实体对象
   * 
   * @param entity 实体对象
   */
  void save(T entity);
  
  /**
   * 保存实体对象
   * @param isTenant 根据租户保存
   * @param entity 实体对象
   */
  void save(T entity,Boolean isTenant);

  /**
   * 更新实体对象
   * 
   * @param entity 实体对象
   * @return 实体对象
   */
  T update(T entity);

  /**
   * 更新实体对象
   * 
   * @param entity 实体对象
   * @param ignoreProperties 忽略属性
   * @return 实体对象
   */
  T update(T entity, String... ignoreProperties);

  /**
   * 删除实体对象
   * 
   * @param id ID
   */
  void delete(ID id);

  /**
   * 删除实体对象
   * 
   * @param ids ID
   */
  void delete(ID... ids);

  /**
   * 删除实体对象
   * 
   * @param entity 实体对象
   */
  void delete(T entity);
  /**
   * 关键字搜索
   */
  Page<T>  search(Query query, Pageable pageable, Analyzer analyzer,org.apache.lucene.search.Filter filter);
  /**
   * 重建索引
   */
  void refreshIndex();
  
  /**
   * 缴费记录查询
   * @param beginDate
   * @param endDate
   * @param realName
   * @param identifier
   * @param status
   * @param pageable
   * @return
   */
  Page<T> chargeRecordSearch(Date beginDate, Date endDate,
      String realName, String identifier,PaymentStatus status, Boolean isPeriod,Pageable pageable);
}
