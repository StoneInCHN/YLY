package com.yly.service;

import java.util.Date;

import com.yly.entity.ElderlyPhotoes;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.framework.service.BaseService;

/**
 * 老人照片Service
 * 
 * @author luzhang
 *
 */
public interface ElderlyPhotoesService extends BaseService<ElderlyPhotoes, Long> {
  Page<ElderlyPhotoes> SearchPageByFilter(String keysOfElderlyName, Date beginDate,
      Date endDate, Pageable pageable);
  void remove(ElderlyPhotoes elderlyPhotoes);
}
