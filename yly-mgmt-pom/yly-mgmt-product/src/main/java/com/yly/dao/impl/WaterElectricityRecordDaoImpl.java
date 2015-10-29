package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.WaterElectricityRecordDao;
import com.yly.entity.WaterElectricityRecord;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * DaoImpl - 水电抄表记录
 * @author pengyanan
 *
 */
@Repository("waterElectricityRecordDaoImpl")
public class WaterElectricityRecordDaoImpl extends BaseDaoImpl<WaterElectricityRecord, Long>
    implements WaterElectricityRecordDao {

}
