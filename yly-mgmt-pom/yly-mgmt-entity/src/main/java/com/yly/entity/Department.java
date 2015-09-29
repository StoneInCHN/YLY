package com.yly.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.yly.entity.base.BaseEntity;

/**
 * 部门
 * 
 * @author huyong
 *
 */
@Entity
@Table(name = "yly_department")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_department_sequence")
public class Department extends BaseEntity {

  private static final long serialVersionUID = 7282926448628904157L;

  /**
   * 租户ID
   */
  private Long tenantID;

  /** 部门名 */
  private String name;
  /** 层次 */
  private String grade;
  /** 上级部门 */
  private Department parent;
  /** 下属部门 */
  private Set<Department> children = new HashSet<Department>();

  /**
   * 租户用户基本信息
   */
  private Set<TenantUser> tenantUsers = new HashSet<TenantUser>();

  /**
   * 固定资产
   */
  private Set<FixedAssets> fixedAssets = new HashSet<FixedAssets>();
  /**
   * 职位
   */
  private Set<Position> positions = new HashSet<Position>();

  @OneToMany(mappedBy = "department")
  public Set<Position> getPositions() {
    return positions;
  }

  public void setPositions(Set<Position> positions) {
    this.positions = positions;
  }

  @OneToMany(mappedBy = "department")
  public Set<FixedAssets> getFixedAssets() {
    return fixedAssets;
  }

  public void setFixedAssets(Set<FixedAssets> fixedAssets) {
    this.fixedAssets = fixedAssets;
  }

  @Index(name = "department_tenantid")
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }

  @OneToMany(mappedBy = "department")
  public Set<TenantUser> getTenantUsers() {
    return tenantUsers;
  }

  public void setTenantUsers(Set<TenantUser> tenantUsers) {
    this.tenantUsers = tenantUsers;
  }

  @Column(length = 20, nullable = false)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getGrade() {
    return grade;
  }

  public void setGrade(String grade) {
    this.grade = grade;
  }

  @ManyToOne
  public Department getParent() {
    return parent;
  }

  public void setParent(Department parent) {
    this.parent = parent;
  }

  @OneToMany(mappedBy = "parent")
  public Set<Department> getChildren() {
    return children;
  }

  public void setChildren(Set<Department> children) {
    this.children = children;
  }

}
