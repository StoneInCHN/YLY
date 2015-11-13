package com.yly.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.PositionDao;
import com.yly.entity.Department;
import com.yly.entity.Position;
import com.yly.framework.filter.Filter;
import com.yly.framework.filter.Filter.Operator;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.PositionService;
import com.yly.utils.FieldFilterUtils;

/**
 * 职位
 * @author huyong
 *
 */
@Service("positionServiceImpl")
public class PositionServiceImpl extends BaseServiceImpl<Position, Long> implements PositionService {

  @Resource(name = "positionDaoImpl")
  private PositionDao positionDao;
  
  @Resource
  public void setBaseDao(PositionDao positionDao) {
    super.setBaseDao(positionDao);
  }

  @Override
  public List<Map<String, Object>> findPositions (Department department){
    List<Filter> filters = new ArrayList<Filter>();
    Filter filter = new Filter("department",Operator.eq, department);
    filters.add(filter);
    List<Position> positionList = positionDao.findList (null, null, filters, null);
    String[] propertys =
      {"id", "name"};
    return FieldFilterUtils.filterCollectionMap(propertys, positionList);
  }

}
