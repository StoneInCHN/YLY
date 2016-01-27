package com.yly.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Resolution;
import org.hibernate.search.annotations.Store;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@Indexed(index="chargeRecord/personalizedRecord")
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
  private String operator;

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
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JsonProperty
  public PersonalizedCharge getPersonalizedCharge() {
    return personalizedCharge;
  }

  public void setPersonalizedCharge(PersonalizedCharge personalizedCharge) {
    this.personalizedCharge = personalizedCharge;
  }

  @JsonProperty
  public PaymentStatus getChargeStatus() {
    return chargeStatus;
  }

  public void setChargeStatus(PaymentStatus chargeStatus) {
    this.chargeStatus = chargeStatus;
  }

  @Column(length=20)
  @JsonProperty
  public String getNurseContent() {
    return nurseContent;
  }

  public void setNurseContent(String nurseContent) {
    this.nurseContent = nurseContent;
  }
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JsonProperty
  public PersonalizedNurse getPersonalizedNurse() {
    return personalizedNurse;
  }

  public void setPersonalizedNurse(PersonalizedNurse personalizedNurse) {
    this.personalizedNurse = personalizedNurse;
  }

  @Column(length = 50)
  @JsonProperty
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
  @IndexedEmbedded
  @JsonProperty
  public ElderlyInfo getElderlyInfo() {
    return elderlyInfo;
  }

  public void setElderlyInfo(ElderlyInfo elderlyInfo) {
    this.elderlyInfo = elderlyInfo;
  }
  
  @Field(index = org.hibernate.search.annotations.Index.UN_TOKENIZED, store = Store.NO)
  @DateBridge(resolution = Resolution.DAY)
  @JsonProperty
  public Date getServiceTime() {
    return serviceTime;
  }

  public void setServiceTime(Date serviceTime) {
    this.serviceTime = serviceTime;
  }

  @JsonProperty
  @Column(length=30)
  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }
  
  
}
