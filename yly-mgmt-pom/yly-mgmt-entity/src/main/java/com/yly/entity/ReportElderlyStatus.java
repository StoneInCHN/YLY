package com.yly.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
@Table (name = "yly_report_elderly_status")
@SequenceGenerator (name = "sequenceGenerator", sequenceName = "yly_report_elderly_status_sequence")
public class ReportElderlyStatus extends BaseEntity
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
  private int outNursingHome;
  /**
   * 办理入院
   */
  private int inProcessCheckin;
  /**
   * 办理出院
   */
  private int inProcessCheckout;
  /**
   * 死亡
   */
  private int dead;

  private Date statisticsDate;
  
  
  @Index (name = "report_elderly_status_tenantid")
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
  public int getInProcessCheckin ()
  {
    return inProcessCheckin;
  }

  public void setInProcessCheckin (int inProcessCheckin)
  {
    this.inProcessCheckin = inProcessCheckin;
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

  @JsonProperty
  public int getOutNursingHome ()
  {
    return outNursingHome;
  }

  public void setOutNursingHome (int outNursingHome)
  {
    this.outNursingHome = outNursingHome;
  }

  @JsonProperty
  public int getInProcessCheckout ()
  {
    return inProcessCheckout;
  }

  public void setInProcessCheckout (int inProcessCheckout)
  {
    this.inProcessCheckout = inProcessCheckout;
  }

  @JsonProperty
  @Temporal(TemporalType.DATE)
  public Date getStatisticsDate ()
  {
    return statisticsDate;
  }

  public void setStatisticsDate (Date statisticsDate)
  {
    this.statisticsDate = statisticsDate;
  }

  
}
