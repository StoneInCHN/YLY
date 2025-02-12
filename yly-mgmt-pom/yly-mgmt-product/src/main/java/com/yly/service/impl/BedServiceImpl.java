package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.BedDao;
import com.yly.entity.Bed;
import com.yly.entity.Building;
import com.yly.entity.Room;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.BedService;
import com.yly.service.TenantAccountService;

@Service("bedServiceImpl")
public class BedServiceImpl extends BaseServiceImpl<Bed, Long> implements BedService {

  @Resource(name = "bedDaoImpl")
  private BedDao bedDao;
  
  @Resource(name = "tenantAccountServiceImpl")
  protected TenantAccountService tenantAccountService;
  
  @Resource(name = "bedDaoImpl")
  public void setBaseDao(BedDao bedDao) {
    super.setBaseDao(bedDao);
  }

  @Override
  public Page<Bed> findPage(Building building, Room room, Pageable page,Boolean isTenant) {
    if (isTenant) {
      return bedDao.findPage(building, room, page ,tenantAccountService.getCurrentTenantID());
    }else{
      return bedDao.findPage(building, room, page ,null);
    }
    
  }

}
