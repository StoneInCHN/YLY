package com.yly.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yly.entity.BlackList;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.framework.service.BaseService;

/**
 * Service - 黑名单
 * @author pengyanan
 *
 */
public interface BlackListService extends BaseService<BlackList, Long>{

  /**
   * 对 blackListName 进行lucene 搜索
   * @param beginDate
   * @param endDate
   * @param blackListName
   * @return
   */
  public Page<BlackList> searchList(Date beginDate, Date endDate, Pageable pageable,  BlackList blackList);

  public int countByFilter(String nameHidden, Date beginDateHidden, Date endDateHidden);

  public List<BlackList> searchListByFilter(String nameHidden, Date beginDateHidden,
      Date endDateHidden);

  public List<Map<String, String>> prepareMap(List<BlackList> blackListList);
}
