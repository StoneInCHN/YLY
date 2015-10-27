package com.yly.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yly.dao.DepartmentDao;
import com.yly.entity.Department;
import com.yly.framework.filter.Filter;
import com.yly.framework.service.impl.BaseServiceImpl;
import com.yly.service.DepartmentService;
import com.yly.utils.FieldFilterUtils;

/**
 * 部门
 * @author huyong
 *
 */
@Service("departmentServiceImpl")
public class DepartmentServiceImpl extends BaseServiceImpl<Department, Long> implements DepartmentService {

  @Resource(name = "departmentDaoImpl")
  private DepartmentDao departmentDao;
  
  @Resource
  public void setBaseDao(DepartmentDao departmentDao) {
    super.setBaseDao(departmentDao);
  }

  @Override
  public List<Map<String, Object>> findAllDepartments ()
  {
    List<Filter> filters = new ArrayList<Filter>();
    
    List<Department> departmentList = departmentDao.findList (null, null, filters, null);
    String[] propertys =
      {"id", "name"};
    return FieldFilterUtils.filterCollectionMap(propertys, departmentList);
  }

}
