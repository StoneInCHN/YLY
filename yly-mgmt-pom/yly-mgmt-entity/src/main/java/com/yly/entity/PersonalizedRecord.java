package com.yly.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.yly.entity.base.BaseEntity;
import com.yly.entity.commonenum.CommonEnum.PaymentStatus;

/**
 * 个性化服务服务详情记录
 * 
 * @author sujinxuan
 *
 */
@Entity
@Table(name = "yly_personalized_record")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_personalized_record_sequence")
public class PersonalizedRecord extends BaseEntity {

  private static final long serialVersionUID = 8840662978230104889L;

  /**
   * 租户ID
   */
  private Long tenantID;

  /**
   * 老人
   */
  private ElderlyInfo elderlyInfo;

  /**
   * 服务时间
   */
  private Date serviceTime;

  /**
   * 服务护工
   */
  private String serviceNurse;

  /**
   * 备注
   */
  private String remark;

  /**
   * 个性化服务项目
   */
  private PersonalizedNurse personalizedNurse;
  
  /**
   * 缴纳状态
   */
  private PaymentStatus chargeStatus;
  
  /**
   * 护理内容
   */
  private String nurseContent;
  
  /**
   * 个性化服务收费记录
   */
  private PersonalizedCharge personalizedCharge;
  
  @ManyToOne
  public PersonalizedCharge getPersonalizedCharge() {
    return personalizedCharge;
  }

  public void setPersonalizedCharge(PersonalizedCharge personalizedCharge) {
    this.personalizedCharge = personalizedCharge;
  }

  public PaymentStatus getChargeStatus() {
    return chargeStatus;
  }

  public void setChargeStatus(PaymentStatus chargeStatus) {
    this.chargeStatus = chargeStatus;
  }

  @Column(length=20)
  public String getNurseContent() {
    return nurseContent;
  }

  public void setNurseContent(String nurseContent) {
    this.nurseContent = nurseContent;
  }
  
  @ManyToOne
  public PersonalizedNurse getPersonalizedNurse() {
    return personalizedNurse;
  }

  public void setPersonalizedNurse(PersonalizedNurse personalizedNurse) {
    this.personalizedNurse = personalizedNurse;
  }

  @Column(length = 50)
  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  @Index(name = "personalized_record_tenantid")
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }

  @ManyToOne
  public ElderlyInfo getElderlyInfo() {
    return elderlyInfo;
  }

  public void setElderlyInfo(ElderlyInfo elderlyInfo) {
    this.elderlyInfo = elderlyInfo;
  }

  public Date getServiceTime() {
    return serviceTime;
  }

  public void setServiceTime(Date serviceTime) {
    this.serviceTime = serviceTime;
  }

  @Column(length = 15)
  public String getServiceNurse() {
    return serviceNurse;
  }

  public void setServiceNurse(String serviceNurse) {
    this.serviceNurse = serviceNurse;
  }
  
  
}
