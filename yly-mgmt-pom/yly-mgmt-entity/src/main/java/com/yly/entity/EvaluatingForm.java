package com.yly.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yly.entity.base.BaseEntity;
import com.yly.entity.commonenum.CommonEnum.EvaluatingFormStatus;

/**
 * 评估表
 * 
 * @author huyong
 *
 */

@Entity
@Table(name = "yly_evaluating_form")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_evaluating_form_sequence")
public class EvaluatingForm extends BaseEntity {

  /**
   * 
   */
  private static final long serialVersionUID = -5007214246176973609L;

  /**
   * 租户ID
   */
  private Long tenantID;

  /**
   * 表格名称
   */
  private String formName;

  /**
   * 表格状态 , 表格一旦创建之后并且投入使用之后不能修改表格,如果想使用新的表格请将现有的表格禁用
   */
  private EvaluatingFormStatus evaluatingFormStatus;
  
  private Set<EvaluatingSection> evaluatingSections = new HashSet<EvaluatingSection>();
  
  private Set<ElderlyEvaluatingRecord> elderlyEvaluatingRecords =  new HashSet<ElderlyEvaluatingRecord>();

  @Index(name="evaluating_form_tenantid")
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }
  @JsonProperty  
  @Column(length = 30)
  public String getFormName() {
    return formName;
  }

  public void setFormName(String formName) {
    this.formName = formName;
  }
  @JsonProperty
  public EvaluatingFormStatus getEvaluatingFormStatus() {
    return evaluatingFormStatus;
  }

  public void setEvaluatingFormStatus(EvaluatingFormStatus evaluatingFormStatus) {
    this.evaluatingFormStatus = evaluatingFormStatus;
  }

  @OneToMany(mappedBy = "evaluatingForm" , fetch = FetchType.LAZY)
  @JsonProperty
  public Set<EvaluatingSection> getEvaluatingSections() {
    return evaluatingSections;
  }

  public void setEvaluatingSections(Set<EvaluatingSection> evaluatingSections) {
    this.evaluatingSections = evaluatingSections;
  }

  @OneToMany(mappedBy = "evaluatingForm" , fetch = FetchType.LAZY)
  public Set<ElderlyEvaluatingRecord> getElderlyEvaluatingRecords() {
    return elderlyEvaluatingRecords;
  }

  public void setElderlyEvaluatingRecords(Set<ElderlyEvaluatingRecord> elderlyEvaluatingRecords) {
    this.elderlyEvaluatingRecords = elderlyEvaluatingRecords;
  }
  
  
}
