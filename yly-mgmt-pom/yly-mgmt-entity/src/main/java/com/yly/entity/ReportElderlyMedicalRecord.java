package com.yly.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yly.entity.base.BaseEntity;

/**
 * 按照日期统计周期，每个月生成一条统计记录
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

  @JsonProperty
  public int getElderlyCount ()
  {
    return elderlyCount;
  }

  public void setElderlyCount (int elderlyCount)
  {
    this.elderlyCount = elderlyCount;
  }

  @JsonProperty
  public Date getMedicalStatiticsCycle ()
  {
    return medicalStatiticsCycle;
  }

  public void setMedicalStatiticsCycle (Date medicalStatiticsCycle)
  {
    this.medicalStatiticsCycle = medicalStatiticsCycle;
  }

  
}
