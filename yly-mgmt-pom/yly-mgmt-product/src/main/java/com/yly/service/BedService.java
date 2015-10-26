package com.yly.service;

import com.yly.entity.Bed;
import com.yly.entity.Building;
import com.yly.entity.Room;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.framework.service.BaseService;


public interface BedService extends BaseService<Bed, Long> {

  /**
   * 按条件分页查询
   * @param building 楼宇
   * @param room 房间
   * @param page
   * @return
   */
  Page<Bed> findPage(Building building, Room room, Pageable page);
  
}
