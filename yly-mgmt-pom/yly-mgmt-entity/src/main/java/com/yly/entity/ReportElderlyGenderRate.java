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
   * 女
   */
  private int female;

  /**
   * 男
   */
  private int male;

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
  public int getFemale ()
  {
    return female;
  }

  public void setFemale (int female)
  {
    this.female = female;
  }

  @JsonProperty
  public int getMale ()
  {
    return male;
  }

  public void setMale (int male)
  {
    this.male = male;
  }

}
