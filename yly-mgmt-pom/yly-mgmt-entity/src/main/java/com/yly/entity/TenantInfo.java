package com.yly.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yly.entity.base.BaseEntity;
import com.yly.entity.commonenum.CommonEnum.AccountStatus;

/**
 *  租户信息
 * 
 * @author sujinxuan
 *
 */
@Entity
@Table(name = "yly_tenant_info")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_tenant_info_sequence")
public class TenantInfo extends BaseEntity {

  
  private static final long serialVersionUID = 3525183712762376969L;

  /**
   * 租户组织机构代码
   */
  private String orgCode;
  
  /**
   * 租户名称
   */
  private String tenantName;
  
  /**
   * 联系电话
   */
  private String contactPhone;
  
  /**
   * 租户地址
   */
  private String address;
  
  /**
   * 联系人
   */
  private String contactPerson;
  
  /** 邮编 */
  private String zipCode;
  
  /** E-mail */
  private String email;
  /**
   * 备注
   */
  private String remark;
  
  /**
   * 租户账号状态
   */
  private AccountStatus accountStatus;

  /**
   * 版本
   */
  private VersionConfig versionConfig;
  
  @Column(length = 20)
  public String getOrgCode() {
    return orgCode;
  }

  public void setOrgCode(String orgCode) {
    this.orgCode = orgCode;
  }

  @Column(length = 50)
  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  @Column(length = 30)
  public String getContactPhone() {
    return contactPhone;
  }

  public void setContactPhone(String contactPhone) {
    this.contactPhone = contactPhone;
  }

  @Column(length = 80)
  public String getTenantName() {
    return tenantName;
  }

  public void setTenantName(String tenantName) {
    this.tenantName = tenantName;
  }

  @Column(length = 100)
  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  @Column(length = 15)
  public String getContactPerson() {
    return contactPerson;
  }

  public void setContactPerson(String contactPerson) {
    this.contactPerson = contactPerson;
  }

  @Column(length = 20)
  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  @Column(length = 60)
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public AccountStatus getAccountStatus() {
    return accountStatus;
  }

  public void setAccountStatus(AccountStatus accountStatus) {
    this.accountStatus = accountStatus;
  }
  
  @ManyToOne(cascade=CascadeType.ALL)
  public VersionConfig getVersionConfig ()
  {
    return versionConfig;
  }

  public void setVersionConfig (VersionConfig versionConfig)
  {
    this.versionConfig = versionConfig;
  }
}
