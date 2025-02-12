package com.yly.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yly.entity.base.BaseEntity;

/**
 * 每个评估项里面的具体问题
 * 
 * @author shijun
 *
 */
@Entity
@Table(name = "yly_evaluating_items")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_evaluating_items_sequence")
@Indexed(index = "evaluatingItems/evaluatingItems")
public class EvaluatingItems extends BaseEntity {

  /**
   * 
   */
  private static final long serialVersionUID = 5400567577806644622L;
  
  /**
   * 租户ID
   */
  private Long tenantID;
  
  /**
   * 每项的名称
   */
  private String itemName;

  /**
   * 是否必选
   */
  private Boolean answerRequired;

  /**
   * 是否允许多选
   */
  private Boolean allowMutipleAnswers;
  
  /**
   * 评估的每个项
   */
  private List<EvaluatingSection> evaluatingSections = new ArrayList<EvaluatingSection>();
  
  private List<EvaluatingItemsOptions> evaluatingItemsOptions = new ArrayList<EvaluatingItemsOptions>();
  
  @Index(name="evaluating_items_tenantid")
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }
  @JsonProperty
  @Column(length = 120)
  @Field(store = Store.NO, index = org.hibernate.search.annotations.Index.TOKENIZED, analyzer = @Analyzer(impl = IKAnalyzer.class))
  public String getItemName() {
    return itemName;
  }

  public void setItemName(String itemName) {
    this.itemName = itemName;
  }

  public Boolean getAnswerRequired() {
    return answerRequired;
  }

  public void setAnswerRequired(Boolean answerRequired) {
    this.answerRequired = answerRequired;
  }

  public Boolean getAllowMutipleAnswers() {
    return allowMutipleAnswers;
  }

  public void setAllowMutipleAnswers(Boolean allowMutipleAnswers) {
    this.allowMutipleAnswers = allowMutipleAnswers;
  }
  
  @ManyToMany(mappedBy = "evaluatingItems")
  public List<EvaluatingSection> getEvaluatingSections() {
    return evaluatingSections;
  }

  public void setEvaluatingSections(List<EvaluatingSection> evaluatingSections) {
    this.evaluatingSections = evaluatingSections;
  }
  
  @JsonProperty
  @OneToMany(mappedBy = "evaluatingItems",fetch = FetchType.LAZY)
  public List<EvaluatingItemsOptions> getEvaluatingItemsOptions() {
    return evaluatingItemsOptions;
  }



  public void setEvaluatingItemsOptions(List<EvaluatingItemsOptions> evaluatingItemsOptions) {
    this.evaluatingItemsOptions = evaluatingItemsOptions;
  }
 
}
