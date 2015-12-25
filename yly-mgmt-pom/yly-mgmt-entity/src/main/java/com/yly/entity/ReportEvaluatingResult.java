package com.yly.entity;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yly.entity.base.BaseEntity;
import com.yly.entity.commonenum.CommonEnum.EvaluatingFormType;

/**
 * 
 * 
 * @author yohu
 *
 */
@Entity
@Table (name = "yly_report_evaluating_result")
@SequenceGenerator (name = "sequenceGenerator", sequenceName = "yly_report_evaluating_result_sequence")
public class ReportEvaluatingResult extends BaseEntity
{

  private static final long serialVersionUID = 4063994934255971594L;

  /**
   * 租户ID
   */
  private Long tenantID;
 
  /**
   * 评估结果名
   */
  private String evaluatingResultName;
  
  /**
   * 人数
   */
  private int elderlyCount;
  
  /**
   * 类型分全国卷和自定义卷
   */
  private EvaluatingFormType evaluatingType;
  
  @Index (name = "report_evaluating_tenantid")
  public Long getTenantID ()
  {
    return tenantID;
  }

  public void setTenantID (Long tenantID)
  {
    this.tenantID = tenantID;
  }

  @JsonProperty
  public int getElderlyCount ()
  {
    return elderlyCount;
  }

  public void setElderlyCount (int elderlyCount)
  {
    this.elderlyCount = elderlyCount;
  }

  @JsonProperty
  public String getEvaluatingResultName ()
  {
    return evaluatingResultName;
  }

  public void setEvaluatingResultName (String evaluatingResultName)
  {
    this.evaluatingResultName = evaluatingResultName;
  }

  @JsonProperty
  public EvaluatingFormType getEvaluatingType ()
  {
    return evaluatingType;
  }

  public void setEvaluatingType (EvaluatingFormType evaluatingType)
  {
    this.evaluatingType = evaluatingType;
  }

 
 
  
}
