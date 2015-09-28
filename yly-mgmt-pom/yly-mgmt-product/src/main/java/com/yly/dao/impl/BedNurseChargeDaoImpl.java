package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.BedNurseChargeDao;
import com.yly.entity.BedNurseCharge;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * 床位护理收费记录
 * @author sujinxuan
 *
 */
@Repository("bedNurseChargeDaoImpl")
public class BedNurseChargeDaoImpl extends BaseDaoImpl<BedNurseCharge, Long> implements BedNurseChargeDao {

}
