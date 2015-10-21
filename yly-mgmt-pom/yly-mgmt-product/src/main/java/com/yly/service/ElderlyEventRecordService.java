package com.yly.service;

import java.util.Date;

import com.yly.entity.ElderlyEventRecord;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.framework.service.BaseService;

/**
 * 老人事件Service
 * @author luzhang
 *
 */
public interface ElderlyEventRecordService extends BaseService<ElderlyEventRecord, Long> {
    Page<ElderlyEventRecord> SearchPageByFilter(String keysOfElderlyName,Date beginDate, Date endDate,Pageable pageable);
}
