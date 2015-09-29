package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.RoomDao;
import com.yly.entity.Room;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.RoomService;

@Service("roomServiceImpl")
public class RoomServiceImpl extends BaseServiceImpl<Room, Long> implements RoomService {

  @Resource(name = "roomDaoImpl")
  public void setBaseDao(RoomDao roomDao) {
    super.setBaseDao(roomDao);
  }



}
