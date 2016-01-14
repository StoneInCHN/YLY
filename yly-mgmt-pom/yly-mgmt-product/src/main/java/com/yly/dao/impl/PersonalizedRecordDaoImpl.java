package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.PersonalizedRecordDao;
import com.yly.entity.PersonalizedRecord;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * 个性化服务服务详情记录 Dao Implement
 * @author luzhang
 *
 */
@Repository("personalizedRecordDaoImpl")
public class PersonalizedRecordDaoImpl extends BaseDaoImpl<PersonalizedRecord, Long> 
  implements PersonalizedRecordDao{

}
