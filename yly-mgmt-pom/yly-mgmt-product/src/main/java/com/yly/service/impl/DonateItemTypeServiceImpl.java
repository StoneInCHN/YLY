package com.yly.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.DonateItemTypeDao;
import com.yly.entity.DonateItemType;
import com.yly.framework.filter.Filter;
import com.yly.framework.ordering.Ordering;
import com.yly.framework.ordering.Ordering.Direction;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.DonateItemTypeService;
import com.yly.utils.FieldFilterUtils;

@Service("donateItemTypeServiceImpl")
public class DonateItemTypeServiceImpl extends BaseServiceImpl<DonateItemType, Long> implements DonateItemTypeService {

  @Resource(name = "donateItemTypeDaoImpl")
  private DonateItemTypeDao donateItemTypeDao;
  
  @Resource
  public void setBaseDao(DonateItemTypeDao donateItemTypeDao) {
    super.setBaseDao(donateItemTypeDao);
  }

  @Override
  public List<Map<String, Object>> findAllDonateItemTypes (Direction direction)
  {
    List<Ordering> orderings =new ArrayList<Ordering>();
    if (direction!=null) {
      Ordering ordering = new Ordering();
      ordering.setProperty("itemOrder");
      ordering.setDirection(direction);
      orderings.add(ordering);
   }
    List<DonateItemType> donateItemTypeList=donateItemTypeDao.findList (null, null, null, orderings);
    
    String[] propertys =
      {"id", "itemName"};
    return FieldFilterUtils.filterCollectionMap(propertys, donateItemTypeList);
  }



}
