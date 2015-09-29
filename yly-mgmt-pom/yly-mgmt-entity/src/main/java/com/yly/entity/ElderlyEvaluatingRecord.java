package com.yly.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.yly.entity.base.BaseEntity;
import com.yly.entity.commonenum.CommonEnum.EvaluatingReason;

/**
 * 老人评估记录
 * 
 * @author shijun
 *
 */
@Entity
@Table(name = "yly_elderly_evaluating_record")
@SequenceGenerator(name = "sequenceGenerator",
    sequenceName = "yly_elderly_evaluating_record_sequence")
public class ElderlyEvaluatingRecord extends BaseEntity {

  /**
   * 
   */
  private static final long serialVersionUID = 6536296046816051292L;

  /**
   * 租户ID
   */
  private Long tenantID;

  /**
   * 评估原因
   */
  private EvaluatingReason evaluatingReason;

  /**
   * 评估人操作人
   */
  private String operator;

  /** 员工编号 */
  private String staffID;

  /**
   * 被评估老人
   */
  private ElderlyInfo elderlyInfo;

  /**
   * 评估结果
   */
  private String evaluatingResult;
  
  private EvaluatingForm evaluatingForm;

  private Set<EvaluatingItemsAnswer> evaluatingItemsAnswers = new HashSet<EvaluatingItemsAnswer>();

  public EvaluatingReason getEvaluatingReason() {
    return evaluatingReason;
  }

  public void setEvaluatingReason(EvaluatingReason evaluatingReason) {
    this.evaluatingReason = evaluatingReason;
  }

  public String getEvaluatingResult() {
    return evaluatingResult;
  }

  public void setEvaluatingResult(String evaluatingResult) {
    this.evaluatingResult = evaluatingResult;
  }

  @OneToMany(mappedBy = "elderlyEvaluatingRecord", fetch = FetchType.LAZY)
  public Set<EvaluatingItemsAnswer> getEvaluatingItemsAnswers() {
    return evaluatingItemsAnswers;
  }

  public void setEvaluatingItemsAnswers(Set<EvaluatingItemsAnswer> evaluatingItemsAnswers) {
    this.evaluatingItemsAnswers = evaluatingItemsAnswers;
  }

  @Column(length = 15)
  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  public ElderlyInfo getElderlyInfo() {
    return elderlyInfo;
  }

  public void setElderlyInfo(ElderlyInfo elderlyInfo) {
    this.elderlyInfo = elderlyInfo;
  }

  @Index(name = "elderly_evaluating_record_tenantid")
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }

  @Column(length = 15)
  public String getStaffID() {
    return staffID;
  }

  public void setStaffID(String staffID) {
    this.staffID = staffID;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  public EvaluatingForm getEvaluatingForm() {
    return evaluatingForm;
  }

  public void setEvaluatingForm(EvaluatingForm evaluatingForm) {
    this.evaluatingForm = evaluatingForm;
  }

}
