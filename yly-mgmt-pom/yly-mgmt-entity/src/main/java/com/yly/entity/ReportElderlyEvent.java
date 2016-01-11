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
@Table (name = "yly_report_elderly_event")
@SequenceGenerator (name = "sequenceGenerator", sequenceName = "yly_report_elderly_event_sequence")
public class ReportElderlyEvent extends BaseEntity
{

  private static final long serialVersionUID = 4063994934255971594L;

  /**
   * 租户ID
   */
  private Long tenantID;
 
  /**
   * 积极事件人数
   */
  private int positiveEvent;
  
  /**
   * 消极事件人数
   */
  private int negativeEvent;
  
  /**
   * 一般事件人数
   */
  private int normalEvent;
  
  /**
   * 统计周期
   */
  private Date eventStatiticsCycle;
  
  @Index (name = "report_elderly_event_tenantid")
  public Long getTenantID ()
  {
    return tenantID;
  }

  public void setTenantID (Long tenantID)
  {
    this.tenantID = tenantID;
  }

  @JsonProperty
  public int getPositiveEvent ()
  {
    return positiveEvent;
  }

  public void setPositiveEvent (int positiveEvent)
  {
    this.positiveEvent = positiveEvent;
  }

  @JsonProperty
  public int getNegativeEvent ()
  {
    return negativeEvent;
  }

  public void setNegativeEvent (int negativeEvent)
  {
    this.negativeEvent = negativeEvent;
  }

  @JsonProperty
  public int getNormalEvent ()
  {
    return normalEvent;
  }

  public void setNormalEvent (int normalEvent)
  {
    this.normalEvent = normalEvent;
  }

  @JsonProperty
  @Temporal(TemporalType.DATE)
  public Date getEventStatiticsCycle ()
  {
    return eventStatiticsCycle;
  }

  public void setEventStatiticsCycle (Date eventStatiticsCycle)
  {
    this.eventStatiticsCycle = eventStatiticsCycle;
  }

  
}
