package com.yly.entity;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.yly.entity.base.BaseEntity;

/**
 * 
 * 
 * @author yohu
 *
 */
@Entity
@Table (name = "yly_report_elderly_evluating_result")
@SequenceGenerator (name = "sequenceGenerator", sequenceName = "yly_report_elderly_evluating_result_sequence")
public class ReportEvluatingResult extends BaseEntity
{

  private static final long serialVersionUID = 4063994934255971594L;

  /**
   * 租户ID
   */
  private Long tenantID;
 
  /**
   * 评估结果名
   */
  private String evluatingResultName;
  
  /**
   * 人数
   */
  private int elderlyCount;
  
  /**
   * 类型分全国卷和自定义卷
   */
  private int evluatingType;
  
  @Index (name = "report_elderly_evluating_tenantid")
  public Long getTenantID ()
  {
    return tenantID;
  }

  public void setTenantID (Long tenantID)
  {
    this.tenantID = tenantID;
  }

  public String getEvluatingResultName ()
  {
    return evluatingResultName;
  }

  public void setEvluatingResultName (String evluatingResultName)
  {
    this.evluatingResultName = evluatingResultName;
  }

  public int getElderlyCount ()
  {
    return elderlyCount;
  }

  public void setElderlyCount (int elderlyCount)
  {
    this.elderlyCount = elderlyCount;
  }

  public int getEvluatingType ()
  {
    return evluatingType;
  }

  public void setEvluatingType (int evluatingType)
  {
    this.evluatingType = evluatingType;
  }

  
}
