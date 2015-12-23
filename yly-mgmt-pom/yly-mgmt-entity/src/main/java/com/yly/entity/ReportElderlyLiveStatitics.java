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
@Table (name = "yly_report_elderly_live_statitics_result")
@SequenceGenerator (name = "sequenceGenerator", sequenceName = "yly_report_elderly_live_statitics_sequence")
public class ReportElderlyLiveStatitics extends BaseEntity
{

  private static final long serialVersionUID = 4063994934255971594L;

  /**
   * 租户ID
   */
  private Long tenantID;

  private int totalZoomCount;

  private int inUsingZoomCount;

  private Double inUsingRate;

  @Index (name = "report_elderly_live_statitics_tenantid")
  public Long getTenantID ()
  {
    return tenantID;
  }

  public void setTenantID (Long tenantID)
  {
    this.tenantID = tenantID;
  }

  public int getTotalZoomCount ()
  {
    return totalZoomCount;
  }

  public void setTotalZoomCount (int totalZoomCount)
  {
    this.totalZoomCount = totalZoomCount;
  }

  public int getInUsingZoomCount ()
  {
    return inUsingZoomCount;
  }

  public void setInUsingZoomCount (int inUsingZoomCount)
  {
    this.inUsingZoomCount = inUsingZoomCount;
  }

  public Double getInUsingRate ()
  {
    return inUsingRate;
  }

  public void setInUsingRate (Double inUsingRate)
  {
    this.inUsingRate = inUsingRate;
  }

}
