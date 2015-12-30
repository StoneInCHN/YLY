package com.yly.entity;

import java.util.Date;

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
@Table (name = "yly_report_booking_registration_record")
@SequenceGenerator (name = "sequenceGenerator", sequenceName = "yly_report_booking_registration_sequence")
public class ReportBookingRegistration extends BaseEntity
{

  private static final long serialVersionUID = 4063994934255971594L;

  /**
   * 租户ID
   */
  private Long tenantID;
 
  /**
   * 预约人数
   */
  private int bookingCount;
  
  /**
   * 统计周期
   */
  private Date bookingDateStatitics;
  
  @Index (name = "report_booking_registration_tenantid")
  public Long getTenantID ()
  {
    return tenantID;
  }

  public void setTenantID (Long tenantID)
  {
    this.tenantID = tenantID;
  }

  @JsonProperty
  public int getBookingCount ()
  {
    return bookingCount;
  }

  public void setBookingCount (int bookingCount)
  {
    this.bookingCount = bookingCount;
  }

  @JsonProperty
  public Date getBookingDateStatitics ()
  {
    return bookingDateStatitics;
  }

  public void setBookingDateStatitics (Date bookingDateStatitics)
  {
    this.bookingDateStatitics = bookingDateStatitics;
  }
  
}
