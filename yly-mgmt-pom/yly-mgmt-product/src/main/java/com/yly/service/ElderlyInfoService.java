package com.yly.service;

import com.yly.entity.ElderlyInfo;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.framework.service.BaseService;

/**
 * 老人信息
 * 
 * @author shijun
 *
 */
public interface ElderlyInfoService extends BaseService<ElderlyInfo,Long>{
  
  /**
   * 老人信息查询
   * @param isTenant
   * @param realName
   * @param identifier
   * @param pageable
   * @return
   */
  Page<ElderlyInfo> elderlyInfoSearch(Boolean isTenant,String realName, String identifier,Pageable pageable);

}
