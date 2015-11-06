package com.yly.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yly.entity.base.BaseEntity;

/**
 * 职位
 * 
 * @author huyong
 *
 */
@Entity
@Table(name = "yly_position")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_position")
public class Position extends BaseEntity {

  private static final long serialVersionUID = -344417707958673848L;

  /**
   * 租户ID
   */
  private Long tenantID;
  /** 职位名 */
  private String name;

  /** 隶属部门 */
  private Department department;

  /**
   * 租户用户基本信息
   */
  private Set<TenantUser> tenantUsers = new HashSet<TenantUser>();

  @OneToMany(mappedBy = "position")
  public Set<TenantUser> getTenantUsers() {
    return tenantUsers;
  }

  public void setTenantUsers(Set<TenantUser> tenantUsers) {
    this.tenantUsers = tenantUsers;
  }

  @Index(name = "position_tenantid")
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }


  @Column(length = 20, nullable = false)
  @JsonProperty
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JsonProperty
  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

}
