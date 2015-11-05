package com.yly.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Index;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Store;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.fasterxml.jackson.annotation.JsonProperty;
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

  /** 树路径分隔符 */
  public static final String TREE_PATH_SEPARATOR = ",";

  /**
   * 租户ID
   */
  private Long tenantID;

  /** 部门名 */
  private String name;
  /** 层次 */
  private Integer grade;
  /** 上级部门 */
  private Department parent;

  /** 树路径 */
  private String treePath;

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
  @JsonProperty
  @Field(store = Store.NO, index = org.hibernate.search.annotations.Index.UN_TOKENIZED,
      analyzer = @Analyzer(impl = IKAnalyzer.class))
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  @JsonProperty
  @Column(length = 10)
  public Integer getGrade() {
    return grade;
  }

  public void setGrade(Integer grade) {
    this.grade = grade;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  public Department getParent() {
    return parent;
  }

  public void setParent(Department parent) {
    this.parent = parent;
  }

  @JsonProperty
  @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  public Set<Department> getChildren() {
    return children;
  }

  public void setChildren(Set<Department> children) {
    this.children = children;
  }

  /**
   * 获取树路径
   * 
   * @return 树路径
   */
  @Column(nullable = false, updatable = false)
  public String getTreePath() {
    return treePath;
  }

  /**
   * 设置树路径
   * 
   * @param treePath 树路径
   */
  public void setTreePath(String treePath) {
    this.treePath = treePath;
  }

  /**
   * 持久化前处理
   */
  @PrePersist
  public void prePersist() {
    Department parent = getParent();
    if (parent != null) {
      setTreePath(parent.getTreePath() + parent.getId() + TREE_PATH_SEPARATOR);
    } else {
      setTreePath(TREE_PATH_SEPARATOR);
    }
  }

  /**
   * 获取树路径
   * 
   * @return 树路径
   */
  @Transient
  public List<Long> getTreePaths() {
    List<Long> treePaths = new ArrayList<Long>();
    String[] ids = StringUtils.split(getTreePath(), TREE_PATH_SEPARATOR);
    if (ids != null) {
      for (String id : ids) {
        treePaths.add(Long.valueOf(id));
      }
    }
    return treePaths;
  }

}
