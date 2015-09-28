package com.yly.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;
import org.hibernate.validator.constraints.NotEmpty;

import com.yly.entity.base.BaseEntity;
import com.yly.entity.commonenum.CommonEnum.AccountStatus;

/**
 * 租户用户登录账户
 * 
 * @author shijun
 *
 */
@Entity
@Table(name = "yly_tenant_account")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_tenant_account_sequence")
public class TenantAccount extends BaseEntity {

  private static final long serialVersionUID = -665961639617388534L;

  /**
   * 租户ID
   */
  private Long tenantID;

  /**
   * 用户名
   */
  private String userName;

  /**
   * 密码
   */
  private String password;
  
  /** 角色 */
  private Set<Role> roles = new HashSet<Role>();
  
  /**
   * 账号状态
   */
  private AccountStatus accoutStatus;
  
  /** 姓名 */
  private String realName;
  
  /** 最后登录日期 */
  private Date loginDate;

  /** 最后登录IP */
  private String loginIp;
  
  /**员工编号*/
  private String staffID;
  
  /**
   * 是否为内置账户
   */
  private Boolean isSystem;
  
  /**
   *  租户用户
   */
  private TenantUser tenantUser;
  
  @ManyToOne
  public TenantUser getTenantUser() {
    return tenantUser;
  }

  public void setTenantUser(TenantUser tenantUser) {
    this.tenantUser = tenantUser;
  }

  @Column(length=30)
  public String getStaffID() {
    return staffID;
  }

  public void setStaffID(String staffID) {
    this.staffID = staffID;
  }

  public AccountStatus getAccoutStatus() {
    return accoutStatus;
  }

  public void setAccoutStatus(AccountStatus accoutStatus) {
    this.accoutStatus = accoutStatus;
  }

  @Column(length=20)
  public String getRealName() {
    return realName;
  }

  public void setRealName(String realName) {
    this.realName = realName;
  }

  public Date getLoginDate() {
    return loginDate;
  }

  public void setLoginDate(Date loginDate) {
    this.loginDate = loginDate;
  }

  @Column(length=20)
  public String getLoginIp() {
    return loginIp;
  }

  public void setLoginIp(String loginIp) {
    this.loginIp = loginIp;
  }

  public Boolean getIsSystem() {
    return isSystem;
  }

  public void setIsSystem(Boolean isSystem) {
    this.isSystem = isSystem;
  }

  /**
   * 获取角色
   * 
   * @return 角色
   */
  @NotEmpty
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "yly_tenant_account_role")
  public Set<Role> getRoles() {
      return roles;
  }

  /**
   * 设置角色
   * 
   * @param roles
   *            角色
   */
  public void setRoles(Set<Role> roles) {
      this.roles = roles;
  }

  @Index(name="tenant_account_tenantid")
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }

  @Column(length = 20)
  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  @Column(length = 128)
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
