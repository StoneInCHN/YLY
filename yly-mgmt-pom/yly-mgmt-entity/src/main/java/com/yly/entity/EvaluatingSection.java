package com.yly.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yly.entity.base.BaseEntity;

/**
 * 评估的每个项,比如 认知能力,行动能力等
 * 
 * @author shijun
 *
 */
@Entity
@Table(name = "yly_evaluating_section")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_evaluating_section_sequence")
public class EvaluatingSection extends BaseEntity {

  /**
   * 
   */
  private static final long serialVersionUID = -4745256940280674440L;

  /**
   * 租户ID
   */
  private Long tenantID;

  /**
   * 每个评估项目名称
   */
  private String sectionName;
  
  private EvaluatingForm evaluatingForm;

  /**
   * 每个评估项目对应的问题集
   */
  private List<EvaluatingItems> evaluatingItems = new ArrayList<EvaluatingItems>();
  
  /**
   * 该评估模块的评分规则
   */
//  private String evaluatingRule;
  
  /**
   * 该评估模块的评分描述
   */
  private String sectionDescription;
  
  /**
   * 标记是否系统定义的评估模块，比如 日常生活活动，精神状态，感知觉与沟通，社会参与 这个四个就是系统定义的评估模块
   * true:系统定义     false：自定义
   */
  private Boolean systemSection;
  
//  @JsonProperty
//  @Column(length = 100)
//  public String getEvaluatingRule() {
//    return evaluatingRule;
//  }
//
//  public void setEvaluatingRule(String evaluatingRule) {
//    this.evaluatingRule = evaluatingRule;
//  }
  
  @JsonProperty
  @Column(length = 500)
  public String getSectionDescription() {
    return sectionDescription;
  }

  public void setSectionDescription(String sectionDescription) {
    this.sectionDescription = sectionDescription;
  }

  @JsonProperty
  public Boolean getSystemSection() {
    return systemSection;
  }

  public void setSystemSection(Boolean systemSection) {
    this.systemSection = systemSection;
  }

  @Index(name="evaluating_section_tenantid")
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }
  @JsonProperty
  @Column(length = 50)
  public String getSectionName() {
    return sectionName;
  }

  public void setSectionName(String sectionName) {
    this.sectionName = sectionName;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  public EvaluatingForm getEvaluatingForm() {
    return evaluatingForm;
  }
  @NotEmpty
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "yly_evaluating_section_item")
  @JsonProperty
  public List<EvaluatingItems> getEvaluatingItems() {
    return evaluatingItems;
  }

  public void setEvaluatingItems(List<EvaluatingItems> evaluatingItems) {
    this.evaluatingItems = evaluatingItems;
  }

  public void setEvaluatingForm(EvaluatingForm evaluatingForm) {
    this.evaluatingForm = evaluatingForm;
  }
}
