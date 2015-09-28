package com.yly.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.yly.entity.base.BaseEntity;

/**
 * 每个项的评估选择结果
 * 
 * @author shijun
 *
 */

@Entity
@Table(name = "yly_evaluating_items_answer")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_evaluating_items_answer_sequence")
public class EvaluatingItemsAnswer extends BaseEntity {

  /**
   * 
   */
  private static final long serialVersionUID = 2373255645598876001L;

  /**
   * 租户ID
   */
  private Long tenantID;
  
  /**
   * 回答的文字内容
   */
  private String answerContent;
  
  /**
   * 回答的是与不是
   */
  private Boolean answerYesNo;
  
  private EvaluatingItemsOptions evaluatingItemsOptions;
  
  private ElderlyEvaluatingRecord elderlyEvaluatingRecord;

  @Column(length = 100)
  public String getAnswerContent() {
    return answerContent;
  }

  public void setAnswerContent(String answerContent) {
    this.answerContent = answerContent;
  }

  public Boolean getAnswerYesNo() {
    return answerYesNo;
  }

  public void setAnswerYesNo(Boolean answerYesNo) {
    this.answerYesNo = answerYesNo;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  public EvaluatingItemsOptions getEvaluatingItemsOptions() {
    return evaluatingItemsOptions;
  }

  public void setEvaluatingItemsOptions(EvaluatingItemsOptions evaluatingItemsOptions) {
    this.evaluatingItemsOptions = evaluatingItemsOptions;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  public ElderlyEvaluatingRecord getElderlyEvaluatingRecord() {
    return elderlyEvaluatingRecord;
  }

  public void setElderlyEvaluatingRecord(ElderlyEvaluatingRecord elderlyEvaluatingRecord) {
    this.elderlyEvaluatingRecord = elderlyEvaluatingRecord;
  }

  @Index(name="evaluating_item_answer_tenantid")
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }
}
