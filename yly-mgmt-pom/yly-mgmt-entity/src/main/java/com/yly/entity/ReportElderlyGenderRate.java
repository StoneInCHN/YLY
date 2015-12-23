package com.yly.entity;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yly.entity.base.BaseEntity;

/**
 * 
 * 
 * @author yohu
 *
 */
@Entity
@Table (name = "yly_report_elderly_gender_rate")
@SequenceGenerator (name = "sequenceGenerator", sequenceName = "yly_report_elderly_gender_rate_sequence")
public class ReportElderlyGenderRate extends BaseEntity
{

  private static final long serialVersionUID = 4063994934255971594L;

  /**
   * 租户ID
   */
  private Long tenantID;
  /**
   * 在院
   */
  private int inNursingHome;
  /**
   * 出院
   */
  private int outnursingHome;
  /**
   * 办理入院
   */
  private int inProcessCheckin;
  /**
   * 办理出院
   */
  private int inprocessCheckout;
  /**
   * 死亡
   */
  private int dead;

  @Index (name = "report_elderly_gender_rate_tenantid")
  public Long getTenantID ()
  {
    return tenantID;
  }

  public void setTenantID (Long tenantID)
  {
    this.tenantID = tenantID;
  }

  @JsonProperty
  public int getInNursingHome ()
  {
    return inNursingHome;
  }

  public void setInNursingHome (int inNursingHome)
  {
    this.inNursingHome = inNursingHome;
  }

  @JsonProperty
  public int getOutnursingHome ()
  {
    return outnursingHome;
  }

  public void setOutnursingHome (int outnursingHome)
  {
    this.outnursingHome = outnursingHome;
  }

  @JsonProperty
  public int getInProcessCheckin ()
  {
    return inProcessCheckin;
  }

  public void setInProcessCheckin (int inProcessCheckin)
  {
    this.inProcessCheckin = inProcessCheckin;
  }

  @JsonProperty
  public int getInprocessCheckout ()
  {
    return inprocessCheckout;
  }

  public void setInprocessCheckout (int inprocessCheckout)
  {
    this.inprocessCheckout = inprocessCheckout;
  }

  @JsonProperty
  public int getDead ()
  {
    return dead;
  }

  public void setDead (int dead)
  {
    this.dead = dead;
  }

}
