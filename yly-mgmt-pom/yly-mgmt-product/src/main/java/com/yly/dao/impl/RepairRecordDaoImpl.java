package com.yly.dao.impl;

import org.springframework.stereotype.Repository;
import com.yly.dao.RepairRecordDao;
import com.yly.entity.RepairRecord;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * DaoImpl - 维修记录
 * @author pengyanan
 *
 */
@Repository("repairRecordDaoImpl")
public class RepairRecordDaoImpl extends BaseDaoImpl<RepairRecord, Long> implements RepairRecordDao {

}
