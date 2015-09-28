package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.RoomDao;
import com.yly.entity.Room;
import com.yly.framework.dao.impl.BaseDaoImpl;

@Repository("roomDaoImpl")
public class RoomDaoImpl extends BaseDaoImpl<Room, Long> implements RoomDao {

}
