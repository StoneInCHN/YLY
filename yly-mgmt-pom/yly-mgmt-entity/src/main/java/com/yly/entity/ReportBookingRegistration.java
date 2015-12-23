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
  private int bookingDateStatitics;
  
  @Index (name = "report_booking_registration_tenantid")
  public Long getTenantID ()
  {
    return tenantID;
  }

  public void setTenantID (Long tenantID)
  {
    this.tenantID = tenantID;
  }

  public int getBookingCount ()
  {
    return bookingCount;
  }

  public void setBookingCount (int bookingCount)
  {
    this.bookingCount = bookingCount;
  }

  public int getBookingDateStatitics ()
  {
    return bookingDateStatitics;
  }

  public void setBookingDateStatitics (int bookingDateStatitics)
  {
    this.bookingDateStatitics = bookingDateStatitics;
  }
  
}
