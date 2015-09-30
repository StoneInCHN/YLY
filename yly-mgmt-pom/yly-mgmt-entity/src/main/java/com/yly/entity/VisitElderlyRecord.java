package com.yly.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yly.entity.base.BaseEntity;
import com.yly.entity.commonenum.CommonEnum.Relation;

/**
 * 探望老人
 * 
 * @author shijun
 *
 */

@Entity
@Table(name = "yly_visit_elderly_record")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_visit_elderly_record_sequence")
public class VisitElderlyRecord extends BaseEntity {

  /**
   * 
   */
  private static final long serialVersionUID = -5942468444145494314L;

  /**
   * 租户ID
   */
  private Long tenantID;

  /**
   * 被探望老人
   */
  private ElderlyInfo elderlyInfo;

  /**
   * 来访人
   */
  private String visitor;

  /**
   * 来访时间
   */
  private Date visitDate;

  /**
   * 预计离开时间
   */
  private Date dueLeaveDate;

  /**
   * 来访人数
   */
  private Integer visitPersonnelNumber;

  /**
   * 身份证号码
   */
  private String IDCard;

  /**
   * 电话号码
   */
  private String phoneNumber;

  /**
   * 来访原因
   */
  private String reasonForVisit;

  /**
   * 咨询人和老人关系
   */
  private Relation relation;

  /**
   * 备注
   */
  private String remark;

  @JsonProperty
  @Column(length = 15)
  public String getVisitor() {
    return visitor;
  }

  public void setVisitor(String visitor) {
    this.visitor = visitor;
  }

  @JsonProperty
  public Date getVisitDate() {
    return visitDate;
  }

  public void setVisitDate(Date visitDate) {
    this.visitDate = visitDate;
  }

  @JsonProperty
  public Date getDueLeaveDate() {
    return dueLeaveDate;
  }

  public void setDueLeaveDate(Date dueLeaveDate) {
    this.dueLeaveDate = dueLeaveDate;
  }

  @JsonProperty
  public Integer getVisitPersonnelNumber() {
    return visitPersonnelNumber;
  }

  public void setVisitPersonnelNumber(Integer visitPersonnelNumber) {
    this.visitPersonnelNumber = visitPersonnelNumber;
  }

  @JsonProperty
  @Column(length = 25)
  public String getIDCard() {
    return IDCard;
  }

  public void setIDCard(String iDCard) {
    IDCard = iDCard;
  }

  @JsonProperty
  @Column(length = 15)
  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  @JsonProperty
  @Column(length = 50)
  public String getReasonForVisit() {
    return reasonForVisit;
  }

  public void setReasonForVisit(String reasonForVisit) {
    this.reasonForVisit = reasonForVisit;
  }

  @JsonProperty
  @Column(length = 150)
  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  @JsonProperty
  @ManyToOne(fetch = FetchType.LAZY)
  public ElderlyInfo getElderlyInfo() {
    return elderlyInfo;
  }

  public void setElderlyInfo(ElderlyInfo elderlyInfo) {
    this.elderlyInfo = elderlyInfo;
  }

  @JsonProperty
  public Relation getRelation() {
    return relation;
  }

  public void setRelation(Relation relation) {
    this.relation = relation;
  }

  @Index(name = "visit_elderly_record_tenantid")
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }

}
