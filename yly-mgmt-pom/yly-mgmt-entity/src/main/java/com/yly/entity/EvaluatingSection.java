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
  private Set<EvaluatingItems> evaluatingItems = new HashSet<EvaluatingItems>();

  @Index(name="evaluating_section_tenantid")
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }

  @Column(length = 50)
  public String getSectionName() {
    return sectionName;
  }

  public void setSectionName(String sectionName) {
    this.sectionName = sectionName;
  }

  @OneToMany(mappedBy = "evaluatingSection", fetch = FetchType.LAZY)
  public Set<EvaluatingItems> getEvaluatingItems() {
    return evaluatingItems;
  }

  public void setEvaluatingItems(Set<EvaluatingItems> evaluatingItems) {
    this.evaluatingItems = evaluatingItems;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  public EvaluatingForm getEvaluatingForm() {
    return evaluatingForm;
  }

  public void setEvaluatingForm(EvaluatingForm evaluatingForm) {
    this.evaluatingForm = evaluatingForm;
  }
}
