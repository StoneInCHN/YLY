package com.yly.service;

import java.util.Date;
import java.util.List;

import com.yly.entity.ElderlyPhotoAlbum;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.framework.service.BaseService;

/**
 * 老人相册Service
 * 
 * @author luzhang
 *
 */
public interface ElderlyPhotoAlbumService extends BaseService<ElderlyPhotoAlbum, Long> {
  //Page<ElderlyPhotoAlbum> SearchPageByFilter(String keysOfElderlyName, String keysOfPhotoAlbumName, Pageable pageable);
  
  List<ElderlyPhotoAlbum> searchByFilter(String keysOfPhotoAlbumName, Boolean isTenant);
}
