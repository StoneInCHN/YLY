package com.yly.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.yly.dao.DepartmentDao;
import com.yly.entity.Department;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * 部门
 * 
 * @author huyong
 *
 */
@Repository("departmentDaoImpl")
public class DepartmentDaoImpl extends BaseDaoImpl<Department, Long> implements DepartmentDao {


  public List<Department> findRoots(Long tenantID,Integer count) {
    String jpql =
        "select department from Department department where department.parent is null and department.tenantID = :tenantID order by department.id asc";
    TypedQuery<Department> query =
        entityManager.createQuery(jpql, Department.class).setParameter("tenantID", tenantID).setFlushMode(FlushModeType.COMMIT);
    if (count != null) {
      query.setMaxResults(count);
    }
    return query.getResultList();
  }

  public List<Department> findParents(Department department, Integer count) {
    if (department == null || department.getParent() == null) {
      return Collections.<Department>emptyList();
    }
    String jpql =
        "select department from Department department where department.id in (:ids) order by department.grade asc";
    TypedQuery<Department> query =
        entityManager.createQuery(jpql, Department.class).setFlushMode(FlushModeType.COMMIT)
            .setParameter("ids", department.getTreePaths());
    if (count != null) {
      query.setMaxResults(count);
    }
    return query.getResultList();
  }

  public List<Department> findChildren(Department department, Integer count) {
    TypedQuery<Department> query;
    if (department != null) {
      String jpql =
          "select department from Department department where department.treePath like :treePath order by department.id asc";
      query =
          entityManager
              .createQuery(jpql, Department.class)
              .setFlushMode(FlushModeType.COMMIT)
              .setParameter(
                  "treePath",
                  "%" + Department.TREE_PATH_SEPARATOR + department.getId()
                      + Department.TREE_PATH_SEPARATOR + "%");
    } else {
      String jpql = "select department from Department department order by department.id asc";
      query = entityManager.createQuery(jpql, Department.class).setFlushMode(FlushModeType.COMMIT);
    }
    if (count != null) {
      query.setMaxResults(count);
    }
    return sort(query.getResultList(), department);
  }

  /**
   * 设置treePath、grade并保存
   * 
   */
  @Override
  public void persist(Department department) {
    Assert.notNull(department);
    setValue(department);
    super.persist(department);
  }

  /**
   * 设置treePath、grade并更新
   * 
   */
  @Override
  public Department merge(Department department) {
    Assert.notNull(department);
    setValue(department);
    for (Department category : findChildren(department, null)) {
      setValue(category);
    }
    return super.merge(department);
  }


  private List<Department> sort(List<Department> departments, Department parent) {
    List<Department> result = new ArrayList<Department>();
    if (departments != null) {
      for (Department department : departments) {
        if ((department.getParent() != null && department.getParent().equals(parent))
            || (department.getParent() == null && parent == null)) {
          result.add(department);
          result.addAll(sort(departments, department));
        }
      }
    }
    return result;
  }

  /**
   * 设置值
   * 
   */
  private void setValue(Department department) {
    if (department == null) {
      return;
    }
    Department parent = department.getParent();
    if (parent != null) {
      department
          .setTreePath(parent.getTreePath() + parent.getId() + Department.TREE_PATH_SEPARATOR);
    } else {
      department.setTreePath(Department.TREE_PATH_SEPARATOR);
    }
    department.setGrade(department.getTreePaths().size());
  }
}
