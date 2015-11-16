package com.yly.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yly.entity.base.BaseEntity;

@Entity
@Table(name = "yly_evaluating_items_options")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_evaluating_items_options_sequence")
public class EvaluatingItemsOptions extends BaseEntity{

  /**
   * 
   */
  private static final long serialVersionUID = 8548341303258480850L;
  
  /**
   * 每个评估项里面的具体问题
   */
  private EvaluatingItems evaluatingItems;
  
  /**
   * 评估项里面每个问题的选项
   */
  private EvaluatingItemOptions evaluatingItemOptions;
  
  //private Set<EvaluatingItemsAnswer> evaluatingItemsAnswers = new HashSet<EvaluatingItemsAnswer>();
  private List<EvaluatingItemsAnswer> evaluatingItemsAnswers = new ArrayList<EvaluatingItemsAnswer>();
  /**
   * 每个选项的分值
   */
  private Integer optionScore;

  @ManyToOne(fetch = FetchType.LAZY)
  public EvaluatingItems getEvaluatingItems() {
    return evaluatingItems;
  }

  public void setEvaluatingItems(EvaluatingItems evaluatingItems) {
    this.evaluatingItems = evaluatingItems;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  public EvaluatingItemOptions getEvaluatingItemOptions() {
    return evaluatingItemOptions;
  }

  public void setEvaluatingItemOptions(EvaluatingItemOptions evaluatingItemOptions) {
    this.evaluatingItemOptions = evaluatingItemOptions;
  }

  @OneToMany(mappedBy = "evaluatingItemsOptions",fetch = FetchType.LAZY)
  public List<EvaluatingItemsAnswer> getEvaluatingItemsAnswers() {
    return evaluatingItemsAnswers;
  }

  public void setEvaluatingItemsAnswers(List<EvaluatingItemsAnswer> evaluatingItemsAnswers) {
    this.evaluatingItemsAnswers = evaluatingItemsAnswers;
  }

public Integer getOptionScore() {
	return optionScore;
}

public void setOptionScore(Integer optionScore) {
	this.optionScore = optionScore;
}
  
}
