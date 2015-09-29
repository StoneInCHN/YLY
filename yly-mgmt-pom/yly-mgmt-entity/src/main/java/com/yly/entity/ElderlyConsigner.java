package com.yly.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.yly.entity.base.BaseEntity;

/**
 * 老人入院委托人
 * 
 * @author shijun
 *
 */
@Entity
@Table(name = "yly_elderly_consigner")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_elderly_consigner_sequence")
public class ElderlyConsigner extends BaseEntity {

  /**
   * 
   */
  private static final long serialVersionUID = -1193182900822549704L;

  /**
   * 租户ID
   */
  private Long tenantID;
  
  /**
   * 委托人姓名
   */
  private String name;

  /**
   * 电话号码
   */
  private String phoneNumber;

  /**
   * 单位地址
   */
  private String companyAddress;

  /**
   * 现局主席详细地址
   */
  private String residentialAddress;

  /**
   * 是否和老人在同一城市
   */
  private Boolean isSameCity;

  /**
   * 和老人关系
   */
  private String relation;
  /**
   * 老人
   */
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

  public String getCompanyAddress() {
    return companyAddress;
  }

  public void setCompanyAddress(String companyAddress) {
    this.companyAddress = companyAddress;
  }

  public String getResidentialAddress() {
    return residentialAddress;
  }

  public void setResidentialAddress(String residentialAddress) {
    this.residentialAddress = residentialAddress;
  }

  public Boolean getIsSameCity() {
    return isSameCity;
  }

  public void setIsSameCity(Boolean isSameCity) {
    this.isSameCity = isSameCity;
  }

  @Column(length = 15)
  public String getRelation() {
    return relation;
  }

  public void setRelation(String relation) {
    this.relation = relation;
  }

  @OneToOne(fetch = FetchType.LAZY)
  public ElderlyInfo getElderlyInfo() {
    return elderlyInfo;
  }

  public void setElderlyInfo(ElderlyInfo elderlyInfo) {
    this.elderlyInfo = elderlyInfo;
  }

  @Index(name="elderly_consigner_tenantid")
  public Long getTenantID() {
    return tenantID;
  }


  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }
}
