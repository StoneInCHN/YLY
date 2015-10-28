package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.DepartmentDao;
import com.yly.dao.DepositDao;
import com.yly.entity.Department;
import com.yly.entity.Deposit;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * 部门
 * @author huyong
 *
 */
@Repository("departmentDaoImpl")
public class DepartmentDaoImpl extends BaseDaoImpl<Department, Long> implements DepartmentDao {

}
