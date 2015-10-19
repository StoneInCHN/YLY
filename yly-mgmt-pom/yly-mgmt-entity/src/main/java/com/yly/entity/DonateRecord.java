package com.yly.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Index;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yly.entity.base.BaseEntity;
import com.yly.entity.commonenum.CommonEnum.Gender;


/**
 * 捐赠记录
 * 
 * @author sujinxuan
 *
 */
@Entity
@Table(name = "yly_donate_record")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_donate_record_sequence")
public class DonateRecord extends BaseEntity {


  private static final long serialVersionUID = -8389533913705258178L;
  
  /**
   * 租户ID
   */
  private Long tenantID;

  /**
   * 捐赠描述
   */
  private String donateDescription;

  /**
   * 捐赠时间
   */
  private Date donateTime;

  /**
   * 捐赠人姓名
   */
  private String donatorName;

  /**
   * 捐赠人电话
   */
  private String donatorPhone;

  /**
   * 捐赠人性别
   */
  private Gender donatorGender;

  /**
   * 捐赠人地址
   */
  private String donatorAddress;

  /**
   * 备注
   */
  private String remark;
  
  /**
   * 捐赠记录明细
   */
  private Set<DonateDetail> donateDetails = new HashSet<DonateDetail>();
  
  
  @OneToMany(mappedBy = "donateRecord")
  public Set<DonateDetail> getDonateDetails() {
    return donateDetails;
  }

  public void setDonateDetails(Set<DonateDetail> donateDetails) {
    this.donateDetails = donateDetails;
  }

  public String getDonateDescription() {
    return donateDescription;
  }

  public void setDonateDescription(String donateDescription) {
    this.donateDescription = donateDescription;
  }

  @JsonProperty
  @Column(length = 15)
  public String getDonatorName() {
    return donatorName;
  }

  public void setDonatorName(String donatorName) {
    this.donatorName = donatorName;
  }

  @Index(name = "donate_record_tenantid")
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }

  @JsonProperty
  @Temporal(TemporalType.DATE)
  public Date getDonateTime() {
    return donateTime;
  }

  public void setDonateTime(Date donateTime) {
    this.donateTime = donateTime;
  }

  @JsonProperty
  @Column(length = 20)
  public String getDonatorPhone() {
    return donatorPhone;
  }

  public void setDonatorPhone(String donatorPhone) {
    this.donatorPhone = donatorPhone;
  }

  @JsonProperty
  public Gender getDonatorGender() {
    return donatorGender;
  }

  public void setDonatorGender(Gender donatorGender) {
    this.donatorGender = donatorGender;
  }

  @Column(length = 50)
  public String getDonatorAddress() {
    return donatorAddress;
  }

  public void setDonatorAddress(String donatorAddress) {
    this.donatorAddress = donatorAddress;
  }

  @Column(length = 50)
  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

}
