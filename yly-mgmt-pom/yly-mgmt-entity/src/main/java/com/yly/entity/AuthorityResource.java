package com.yly.entity;

import java.security.acl.Permission;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yly.entity.base.BaseEntity;

@Entity
@Table(name = "yly_auth_resource")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_auth_resource_sequence")
public class AuthorityResource extends BaseEntity implements Permission {

  /**
   * 
   */
  private static final long serialVersionUID = -7067398393555025067L;
  /**
   * 权限名
   */
  private String authCnName;

  /**
   * 权限英文名
   */
  private String authEnName;

  /**
   * 父节点编号
   */
  private Long parentId;

  /**
   * 权限对应的URL
   */
  private String authURL;

  /**
   * 角色
   */
  private Set<Role> roles = new HashSet<Role>();


  @JsonProperty
  @Column(length = 20, unique = true, nullable = false)
  public String getAuthCnName() {
    return authCnName;
  }

  public void setAuthCnName(String authCnName) {
    this.authCnName = authCnName;
  }

  @JsonProperty
  @Column(length = 30, unique = true, nullable = false)
  public String getAuthEnName() {
    return authEnName;
  }

  public void setAuthEnName(String authEnName) {
    this.authEnName = authEnName;
  }

  public Long getParentId() {
    return parentId;
  }

  public void setParentId(Long parentId) {
    this.parentId = parentId;
  }

  @Column(length = 100)
  public String getAuthURL() {
    return authURL;
  }

  public void setAuthURL(String authURL) {
    this.authURL = authURL;
  }

  @ManyToMany(mappedBy = "authorityResources")
  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
  }

}
