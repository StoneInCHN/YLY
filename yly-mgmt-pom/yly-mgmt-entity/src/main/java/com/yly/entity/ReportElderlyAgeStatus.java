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
@Table (name = "yly_report_elderly_age_status")
@SequenceGenerator (name = "sequenceGenerator", sequenceName = "yly_report_elderly_age_status_sequence")
public class ReportElderlyAgeStatus extends BaseEntity
{

  private static final long serialVersionUID = 4063994934255971594L;

  /**
   * 租户ID
   */
  private Long tenantID;

  /**
   * 60岁以下老人人数
   */
  private int under60;
  /**
   * 61到65岁老人人数
   */
  private int range61To65;
  /**
   * 66到70岁老人人数
   */
  private int range66To70;
  /**
   * 71到75岁老人人数
   */
  private int range71To75;
  /**
   * 76岁以上老人人数
   */
  private int above76;

  @Index (name = "report_elderly_age_status_tenantid")
  public Long getTenantID ()
  {
    return tenantID;
  }

  public void setTenantID (Long tenantID)
  {
    this.tenantID = tenantID;
  }

  @JsonProperty
  public int getUnder60 ()
  {
    return under60;
  }

  public void setUnder60 (int under60)
  {
    this.under60 = under60;
  }

  @JsonProperty
  public int getRange61To65 ()
  {
    return range61To65;
  }

  public void setRange61To65 (int range61To65)
  {
    this.range61To65 = range61To65;
  }

  @JsonProperty
  public int getRange66To70 ()
  {
    return range66To70;
  }

  public void setRange66To70 (int range66To70)
  {
    this.range66To70 = range66To70;
  }

  @JsonProperty
  public int getRange71To75 ()
  {
    return range71To75;
  }

  public void setRange71To75 (int range71To75)
  {
    this.range71To75 = range71To75;
  }

  @JsonProperty
  public int getAbove76 ()
  {
    return above76;
  }

  public void setAbove76 (int above76)
  {
    this.above76 = above76;
  }

}
