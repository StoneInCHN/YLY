package com.yly.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yly.entity.base.BaseEntity;

/**
 * 评估项里面每个问题的选项
 * 
 * @author shijun
 *
 */

@Entity
@Table(name = "yly_evaluating_item_options")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_evaluating_item_options_sequence")
public class EvaluatingItemOptions extends BaseEntity {

  /**
   * 
   */
  private static final long serialVersionUID = 830713650704044781L;
  
  /**
   * 租户ID
   */
  private Long tenantID;

  /**
   * 选项名称
   */
  private String optionName;
  
  private List<EvaluatingItemsOptions> evaluatingItemsOptions = new ArrayList<EvaluatingItemsOptions>();

  @Index(name="evaluating_item_options_tenantid")
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }
  @JsonProperty
  public String getOptionName() {
    return optionName;
  }

  public void setOptionName(String optionName) {
    this.optionName = optionName;
  }
  
  @OneToMany(mappedBy = "evaluatingItemOptions",fetch = FetchType.LAZY)
  public List<EvaluatingItemsOptions> getEvaluatingItemsOptions() {
    return evaluatingItemsOptions;
  }

  public void setEvaluatingItemsOptions(List<EvaluatingItemsOptions> evaluatingItemsOptions) {
    this.evaluatingItemsOptions = evaluatingItemsOptions;
  }

  
 
}
