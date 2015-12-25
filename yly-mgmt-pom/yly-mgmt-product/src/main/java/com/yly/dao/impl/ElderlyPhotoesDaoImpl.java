package com.yly.dao.impl;


import org.springframework.stereotype.Repository;

import com.yly.dao.ElderlyPhotoesDao;
import com.yly.entity.ElderlyPhotoes;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * 老人照片 Dao Implement
 * 
 * @author luzhang
 *
 */
@Repository("elderlyPhotoesDaoImpl")
public class ElderlyPhotoesDaoImpl extends BaseDaoImpl<ElderlyPhotoes, Long> implements
    ElderlyPhotoesDao {

}
