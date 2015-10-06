package com.yly.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.yly.entity.base.BaseEntity;
import com.yly.entity.commonenum.CommonEnum.Relation;

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
  private String consignerName;

  /**
   * 电话号码
   */
  private String consignerPhoneNumber;

  /**
   * 单位地址
   */
  private String companyAddress;

  /**
   * 现居住详细地址
   */
  private String consignerResidentialAddress;

  /**
   * 是否和老人在同一城市
   */
  private Boolean isSameCity;

  /**
   * 和老人关系
   */
  private Relation relation;
  /**
   * 老人
   */
  private ElderlyInfo elderlyInfo;

  @Column(length = 15)
  public String getConsignerName() {
    return consignerName;
  }

  public void setConsignerName(String consignerName) {
    this.consignerName = consignerName;
  }
  
  @Column(length = 15)
  public String getConsignerPhoneNumber() {
    return consignerPhoneNumber;
  }

  public void setConsignerPhoneNumber(String consignerPhoneNumber) {
    this.consignerPhoneNumber = consignerPhoneNumber;
  }

  @Column(length = 150)
  public String getConsignerResidentialAddress() {
    return consignerResidentialAddress;
  }

  public void setConsignerResidentialAddress(String consignerResidentialAddress) {
    this.consignerResidentialAddress = consignerResidentialAddress;
  }

  public Relation getRelation() {
    return relation;
  }

  public void setRelation(Relation relation) {
    this.relation = relation;
  }

  public String getCompanyAddress() {
    return companyAddress;
  }

  public void setCompanyAddress(String companyAddress) {
    this.companyAddress = companyAddress;
  }


  public Boolean getIsSameCity() {
    return isSameCity;
  }

  public void setIsSameCity(Boolean isSameCity) {
    this.isSameCity = isSameCity;
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
