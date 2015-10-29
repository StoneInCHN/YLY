package com.yly.service;

import java.util.Date;

import com.yly.entity.Volunteer;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.framework.service.BaseService;

/**
 * 志愿者
 * 
 * @author chenyoujun
 *
 */
public interface VolunteerService extends BaseService<Volunteer, Long>{

  /**
   * 对 volunteer name进行lucene搜索 
   * @param beginDate
   * @param endDate
   * @param blackListName
   * @return
   */
  public Page<Volunteer> searchList(Date beginDate, Date endDate, Volunteer volunteer, Pageable pageable);
  
}
