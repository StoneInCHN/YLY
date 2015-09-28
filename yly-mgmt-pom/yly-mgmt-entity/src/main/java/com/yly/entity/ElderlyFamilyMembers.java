package com.yly.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.yly.entity.base.BaseEntity;

@Entity
@Table(name = "yly_elderly_family_members")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_elderly_family_members_sequence")
public class ElderlyFamilyMembers extends BaseEntity {

  /**
   * 
   */
  private static final long serialVersionUID = 7445603213049873537L;

  /**
   * 租户ID
   */
  private Long tenantID;
  
  /**
   * 家庭成员名称
   */
  private String name;

  /**
   * 电话号码
   */
  private String phoneNumber;

  /**
   * 关系
   */
  private String relation;

  /**
   * 居住地址
   */
  private String residentialAddress;
  
  private ElderlyInfo elderlyInfo;

  @Column(length = 15)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Column(length = 15)
  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  @Column(length = 15)
  public String getRelation() {
    return relation;
  }

  public void setRelation(String relation) {
    this.relation = relation;
  }

  public String getResidentialAddress() {
    return residentialAddress;
  }

  public void setResidentialAddress(String residentialAddress) {
    this.residentialAddress = residentialAddress;
  }

  @Index(name="elderly_family_members_tenantid")
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  public ElderlyInfo getElderlyInfo() {
    return elderlyInfo;
  }

  public void setElderlyInfo(ElderlyInfo elderlyInfo) {
    this.elderlyInfo = elderlyInfo;
  }
  
  
}
