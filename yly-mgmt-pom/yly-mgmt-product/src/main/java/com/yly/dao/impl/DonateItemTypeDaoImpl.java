package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.DonateItemTypeDao;
import com.yly.entity.DonateItemType;
import com.yly.framework.dao.impl.BaseDaoImpl;

@Repository("donateItemTypeDaoImpl")
public class DonateItemTypeDaoImpl extends BaseDaoImpl<DonateItemType, Long> implements DonateItemTypeDao {


}
