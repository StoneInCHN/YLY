package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.FixedAssetsDao;
import com.yly.entity.FixedAssets;
import com.yly.framework.dao.impl.BaseDaoImpl;

@Repository("fixedAssetsDaoImpl")
public class FixedAssetsDaoImpl extends BaseDaoImpl<FixedAssets, Long> implements FixedAssetsDao {

}
