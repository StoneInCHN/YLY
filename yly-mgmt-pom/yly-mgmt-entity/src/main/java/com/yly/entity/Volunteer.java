package com.yly.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.yly.entity.base.BaseEntity;
import com.yly.entity.commonenum.CommonEnum.VolunteerType;

/**
 * 租户自愿者
 * 
 * @author sujinxuan
 *
 */
@Entity
@Table(name = "yly_volunteer")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_volunteer_sequence")
public class Volunteer extends BaseEntity {

  private static final long serialVersionUID = 4063994934255971594L;
  
  /**
   * 租户ID
   */
  private Long tenantID;
  
  /**
   * 志愿者类型
   */
  private VolunteerType volunteerType;

  /**
   * 志愿者姓名或组织结构名称
   */
  private String volunteerName;
  
  /**身份证号码*/
  private String IDCard;
  
  
  /** E-mail */
  private String email;

  /** 地址 */
  private String address;

  /** 邮编 */
  private String zipCode;

  /** 电话 */
  private String telephone;

  /** 手机 */
  private String mobile;
  
  /**
   * 备注
   */
  private String remark;
  
  
  public VolunteerType getVolunteerType() {
    return volunteerType;
  }

  public void setVolunteerType(VolunteerType volunteerType) {
    this.volunteerType = volunteerType;
  }

  @Column(length = 30)
  public String getVolunteerName() {
    return volunteerName;
  }

  public void setVolunteerName(String volunteerName) {
    this.volunteerName = volunteerName;
  }

  @Column(length = 50)
  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }
  
  @Column(length=30)
  public String getIDCard() {
    return IDCard;
  }

  public void setIDCard(String iDCard) {
    IDCard = iDCard;
  }

  @Column(length=200)
  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  @Column(length=50)
  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  @Column(length=30)
  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  @Column(length=30)
  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  @Column(length=60)
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Index(name="volunteer_tenantid")
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }

}
