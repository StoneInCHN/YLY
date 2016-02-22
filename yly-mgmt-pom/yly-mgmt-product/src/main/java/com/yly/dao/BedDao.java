package com.yly.dao;

import com.yly.entity.Bed;
import com.yly.entity.Building;
import com.yly.entity.Room;
import com.yly.framework.dao.BaseDao;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;

public interface BedDao extends BaseDao<Bed, Long> {

  /**
   * 按条件分页查询
   * @param buildingId 楼宇id
   * @param roomId 房间id
   * @param page
   * @return
   */
  Page<Bed> findPage(Building building, Room room, Pageable page,Long tenantId);
}
