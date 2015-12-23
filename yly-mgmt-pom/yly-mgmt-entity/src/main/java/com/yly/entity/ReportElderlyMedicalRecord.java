package com.yly.entity;

import java.util.Date;

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
@Table (name = "yly_report_elderly_medical_record")
@SequenceGenerator (name = "sequenceGenerator", sequenceName = "yly_report_elderly_medical_record_sequence")
public class ReportElderlyMedicalRecord extends BaseEntity
{

  private static final long serialVersionUID = 4063994934255971594L;

  /**
   * 租户ID
   */
  private Long tenantID;

  /**
   * 老人人数
   */
  private int elderlyCount; 

  /**
   * 统计周期
   */
  private Date medicalStatiticsCycle;
  @Index (name = "report_elderly_medical_record_tenantid")
  public Long getTenantID ()
  {
    return tenantID;
  }

  public void setTenantID (Long tenantID)
  {
    this.tenantID = tenantID;
  }

  public int getElderlyCount ()
  {
    return elderlyCount;
  }

  public void setElderlyCount (int elderlyCount)
  {
    this.elderlyCount = elderlyCount;
  }

  public Date getMedicalStatiticsCycle ()
  {
    return medicalStatiticsCycle;
  }

  public void setMedicalStatiticsCycle (Date medicalStatiticsCycle)
  {
    this.medicalStatiticsCycle = medicalStatiticsCycle;
  }

  
}
