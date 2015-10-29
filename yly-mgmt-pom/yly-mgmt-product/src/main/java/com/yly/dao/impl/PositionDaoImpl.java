package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.PositionDao;
import com.yly.entity.Position;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * 职位
 * @author huyong
 *
 */
@Repository("positionDaoImpl")
public class PositionDaoImpl extends BaseDaoImpl<Position, Long> implements PositionDao {

}
