package com.yly.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.DonateDetailDao;
import com.yly.dao.DonateRecordDao;
import com.yly.entity.DonateDetail;
import com.yly.entity.DonateRecord;
import com.yly.framework.filter.Filter;
import com.yly.framework.filter.Filter.Operator;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.DonateDetailService;

@Service("donateDetailServiceImpl")
public class DonateDetailServiceImpl extends BaseServiceImpl<DonateDetail, Long> implements DonateDetailService {

  @Resource(name = "donateDetailDaoImpl")
  private DonateDetailDao donateDetailDao;
  @Resource(name = "donateRecordDaoImpl")
  private DonateRecordDao donateRecordDao;
  
  @Resource
  public void setBaseDao(DonateDetailDao donateDetailDao) {
    super.setBaseDao(donateDetailDao);
  }

  @Override
  public Page<DonateDetail> findDonateDetailByRecordId (Pageable pageable, Long recordId)
  {
    List<Filter> filters =new ArrayList<Filter>();
    DonateRecord record = donateRecordDao.find (recordId);
    Filter recordIdFilter= new Filter ("donateRecord", Operator.eq, record);
    filters.add (recordIdFilter);
    pageable.setFilters (filters);
    Page<DonateDetail> detailPage = donateDetailDao.findPage (pageable);
    return detailPage;
  }



}
