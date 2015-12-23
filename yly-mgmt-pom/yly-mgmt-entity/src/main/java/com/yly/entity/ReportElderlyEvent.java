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
   * 积极事件
   */
  private int positiveEvent;
  
  /**
   * 消极事件
   */
  private int negativeEvent;
  
  /**
   * 一般事件
   */
  private int normalEvent;
  
  /**
   * 统计周期
   */
  private String eventStatiticsCycle;
  
  @Index (name = "report_elderly_event_tenantid")
  public Long getTenantID ()
  {
    return tenantID;
  }

  public void setTenantID (Long tenantID)
  {
    this.tenantID = tenantID;
  }

  public int getPositiveEvent ()
  {
    return positiveEvent;
  }

  public void setPositiveEvent (int positiveEvent)
  {
    this.positiveEvent = positiveEvent;
  }

  public int getNegativeEvent ()
  {
    return negativeEvent;
  }

  public void setNegativeEvent (int negativeEvent)
  {
    this.negativeEvent = negativeEvent;
  }

  public int getNormalEvent ()
  {
    return normalEvent;
  }

  public void setNormalEvent (int normalEvent)
  {
    this.normalEvent = normalEvent;
  }

  public String getEventStatiticsCycle ()
  {
    return eventStatiticsCycle;
  }

  public void setEventStatiticsCycle (String eventStatiticsCycle)
  {
    this.eventStatiticsCycle = eventStatiticsCycle;
  }

  
}
