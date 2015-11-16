package com.yly.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@Indexed(index = "elderlyEvaluatingRecord/elderlyEvaluatingRecord")
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

  private List<EvaluatingItemsAnswer> evaluatingItemsAnswers = new ArrayList<EvaluatingItemsAnswer>();
  
  /**
   * 每个模块总分和等级
   * 保存为字符串 如  --> "日常生活活动:总分,等级;精神状态:总分,等级;感知觉与沟通:总分,等级;社会参与:总分,等级;"
   */
  private String sectionsResult;  
  
  @JsonProperty
  public EvaluatingReason getEvaluatingReason() {
    return evaluatingReason;
  }

  public void setEvaluatingReason(EvaluatingReason evaluatingReason) {
    this.evaluatingReason = evaluatingReason;
  }
  @JsonProperty  
  @Column(length = 30)
  public String getEvaluatingResult() {
    return evaluatingResult;
  }

  public void setEvaluatingResult(String evaluatingResult) {
    this.evaluatingResult = evaluatingResult;
  }  
  
  @JsonProperty  
  @Column(length = 300)
  public String getSectionsResult() {
    return sectionsResult;
  }

  public void setSectionsResult(String sectionsResult) {
    this.sectionsResult = sectionsResult;
  }

  @JsonProperty
  @Column(length = 15)
  public String getOperator() {
    return operator;
  }
  @JsonProperty
  @OneToMany(mappedBy = "elderlyEvaluatingRecord", fetch = FetchType.LAZY)
  public List<EvaluatingItemsAnswer> getEvaluatingItemsAnswers() {
    return evaluatingItemsAnswers;
  }

  public void setEvaluatingItemsAnswers(List<EvaluatingItemsAnswer> evaluatingItemsAnswers) {
    this.evaluatingItemsAnswers = evaluatingItemsAnswers;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }
  @JsonProperty
  @ManyToOne(fetch = FetchType.LAZY)
  @IndexedEmbedded
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
  @JsonProperty
  @Column(length = 15)
  public String getStaffID() {
    return staffID;
  }

  public void setStaffID(String staffID) {
    this.staffID = staffID;
  }
  @JsonProperty
  @ManyToOne(fetch = FetchType.LAZY)
  public EvaluatingForm getEvaluatingForm() {
    return evaluatingForm;
  }

  public void setEvaluatingForm(EvaluatingForm evaluatingForm) {
    this.evaluatingForm = evaluatingForm;
  }

}
