package com.yly.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Resolution;
import org.hibernate.search.annotations.Store;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@Indexed(index = "volunteer")
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

  /** 身份证号码 */
  private String idcard;


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
  
  /** 活动时间 */
  private Date activityTime;

  /** 备注 */
  private String remark;

  @JsonProperty
  @Field(store = Store.NO, index = org.hibernate.search.annotations.Index.UN_TOKENIZED, analyzer = @Analyzer(impl = IKAnalyzer.class))
  public VolunteerType getVolunteerType() {
    return volunteerType;
  }

  public void setVolunteerType(VolunteerType volunteerType) {
    this.volunteerType = volunteerType;
  }

  @JsonProperty
  @Column(length = 30)
  @Field(store = Store.NO, index = org.hibernate.search.annotations.Index.TOKENIZED, analyzer = @Analyzer(impl = IKAnalyzer.class))
  public String getVolunteerName() {
    return volunteerName;
  }

  public void setVolunteerName(String volunteerName) {
    this.volunteerName = volunteerName;
  }

  @JsonProperty
  @Column(length = 50)
  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  @JsonProperty
  @Column(length = 30)
  public String getIdcard() {
    return idcard;
  }

  public void setIdcard(String idcard) {
    this.idcard = idcard;
  }

  @JsonProperty
  @Column(length = 200)
  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  @JsonProperty
  @Column(length = 50)
  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  @JsonProperty
  @Column(length = 30)
  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  @JsonProperty
  @Column(length = 30)
  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  @JsonProperty
  @Column(length = 60)
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Index(name = "volunteer_tenantid")
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }

  @JsonProperty
  @Field(index = org.hibernate.search.annotations.Index.UN_TOKENIZED, store = Store.NO)
  @DateBridge(resolution = Resolution.DAY)
  public Date getActivityTime() {
    return activityTime;
  }

  public void setActivityTime(Date activityTime) {
    this.activityTime = activityTime;
  }
  
  

}
