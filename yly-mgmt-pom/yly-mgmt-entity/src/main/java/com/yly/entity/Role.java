package com.yly.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yly.entity.base.BaseEntity;

/**
 * Entity - 角色
 * 
 */
@Entity
@Table(name = "yly_role")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_role_sequence")
public class Role extends BaseEntity {

  private static final long serialVersionUID = -6614052029623997372L;

  /** 名称 */
  private String name;

  /** 是否内置 */
  private Boolean isSystem;

  /** 描述 */
  private String description;

  /** 权限 */
  private Set<AuthorityResource> authorityResources = new HashSet<AuthorityResource>();

  /** 租户账号 */
  private Set<TenantAccount> tenantAccounts = new HashSet<TenantAccount>();

  /**
   * 租户ID
   */
  private Long tenantID;
  
  @Index(name="role_tenantid")
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }

  /**
   * 获取名称
   * 
   * @return 名称
   */
  @JsonProperty
  @NotEmpty
  @Length(max = 200)
  @Column(nullable = false)
  public String getName() {
    return name;
  }

  /**
   * 设置名称
   * 
   * @param name 名称
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * 获取是否内置
   * 
   * @return 是否内置
   */
  @Column(nullable = false, updatable = false)
  public Boolean getIsSystem() {
    return isSystem;
  }

  /**
   * 设置是否内置
   * 
   * @param isSystem 是否内置
   */
  public void setIsSystem(Boolean isSystem) {
    this.isSystem = isSystem;
  }

  /**
   * 获取描述
   * 
   * @return 描述
   */
  @JsonProperty
  @Length(max = 200)
  public String getDescription() {
    return description;
  }

  /**
   * 设置描述
   * 
   * @param description 描述
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * 获取权限
   * 
   * @return 权限
   */
  @JsonProperty
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinColumn(name = "yly_role_auth_resource")
  public Set<AuthorityResource> getAuthorityResources() {
    return authorityResources;
  }

  /**
   * 设置权限
   * 
   * @param authorityResources 权限
   */
  public void setAuthorityResources(Set<AuthorityResource> authorityResources) {
    this.authorityResources = authorityResources;
  }

  @ManyToMany(mappedBy = "roles")
  public Set<TenantAccount> getTenantAccounts() {
    return tenantAccounts;
  }

  public void setTenantAccounts(Set<TenantAccount> tenantAccounts) {
    this.tenantAccounts = tenantAccounts;
  }

}
