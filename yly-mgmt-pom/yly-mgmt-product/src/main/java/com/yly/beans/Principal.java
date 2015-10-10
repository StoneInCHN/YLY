package com.yly.beans;

import java.io.Serializable;

import com.yly.entity.TenantInfo;

/**
 * 身份信息
 * 
 */
public class Principal implements Serializable {

  private static final long serialVersionUID = 5798882004228239559L;

  /** ID */
  private Long id;

  /** 用户名 */
  private String username;

  /** 租户ID */
  private TenantInfo tenantInfo;

  /**
   * @param id ID
   * @param username 用户名
   * @param tenantID 租户ID
   */
  public Principal(Long id, String username, TenantInfo tenantInfo) {
    this.id = id;
    this.username = username;
    this.tenantInfo = tenantInfo;
  }

  /**
   * 获取ID
   * 
   * @return ID
   */
  public Long getId() {
    return id;
  }

  /**
   * 设置ID
   * 
   * @param id ID
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * 获取用户名
   * 
   * @return 用户名
   */
  public String getUsername() {
    return username;
  }

  /**
   * 设置用户名
   * 
   * @param username 用户名
   */
  public void setUsername(String username) {
    this.username = username;
  }


  /**
   * 获取租户ID
   * 
   * @return 租户ID
   */
  public TenantInfo getTenantInfo() {
    return tenantInfo;
  }

  /**
   * 设置租户ID
   * 
   * @param tenantID 租户ID
   */
  public void setTenantInfo(TenantInfo tenantInfo) {
    this.tenantInfo = tenantInfo;
  }

  @Override
  public String toString() {
    return username;
  }

}
