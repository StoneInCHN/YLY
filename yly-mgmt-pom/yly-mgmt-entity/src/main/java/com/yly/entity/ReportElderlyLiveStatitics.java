package com.yly.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
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
@Table (name = "yly_report_elderly_live_statitics_result")
@SequenceGenerator (name = "sequenceGenerator", sequenceName = "yly_report_elderly_live_statitics_sequence")
public class ReportElderlyLiveStatitics extends BaseEntity
{

  private static final long serialVersionUID = 4063994934255971594L;

  /**
   * 租户ID
   */
  private Long tenantID;

  /**
   * 房间类型
   */
  private SystemConfig roomType;

  /**
   * 床位数
   */
  private int totalCount;

  /**
   * 使用中
   */
  private int inUsingCount;

  @Index(name = "report_elderly_live_statitics_tenantid")
  public Long getTenantID ()
  {
    return tenantID;
  }

  public void setTenantID (Long tenantID)
  {
    this.tenantID = tenantID;
  }

  @JsonProperty
  @ManyToOne
  public SystemConfig getRoomType ()
  {
    return roomType;
  }

  public void setRoomType (SystemConfig roomType)
  {
    this.roomType = roomType;
  }

  @JsonProperty
  public int getTotalCount ()
  {
    return totalCount;
  }

  public void setTotalCount (int totalCount)
  {
    this.totalCount = totalCount;
  }

  @JsonProperty
  public int getInUsingCount ()
  {
    return inUsingCount;
  }

  public void setInUsingCount (int inUsingCount)
  {
    this.inUsingCount = inUsingCount;
  }
  


  
}
