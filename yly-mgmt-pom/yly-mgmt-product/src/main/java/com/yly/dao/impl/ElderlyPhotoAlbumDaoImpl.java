package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.ElderlyPhotoAlbumDao;
import com.yly.entity.ElderlyPhotoAlbum;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * 老人相册Dao Implement
 * 
 * @author luzhang
 *
 */
@Repository("elderlyPhotoAlbumDaoImpl")
public class ElderlyPhotoAlbumDaoImpl extends BaseDaoImpl<ElderlyPhotoAlbum, Long> implements
    ElderlyPhotoAlbumDao {

}
