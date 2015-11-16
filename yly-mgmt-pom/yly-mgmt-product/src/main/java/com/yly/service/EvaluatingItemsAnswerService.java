package com.yly.service;

import java.util.Date;

import com.yly.entity.ElderlyPhotoes;
import com.yly.entity.EvaluatingItemsAnswer;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.framework.service.BaseService;

/**
 * 每个项的评估选择结果Service
 * 
 * @author luzhang
 *
 */
public interface EvaluatingItemsAnswerService extends BaseService<EvaluatingItemsAnswer, Long> {
  Page<EvaluatingItemsAnswer> searchPageByFilter(String keysOfElderlyName, Date beginDate,
      Date endDate, Pageable pageable);
  void remove(EvaluatingItemsAnswer evaluatingItemsAnswer);
}
