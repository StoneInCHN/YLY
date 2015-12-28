package com.yly.entity;

import java.math.BigDecimal;
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
@Table (name = "yly_report_water_electricity_record")
@SequenceGenerator (name = "sequenceGenerator", sequenceName = "yly_report_water_electricity_record_sequence")
public class ReportWaterElectricityRecord extends BaseEntity
{

  private static final long serialVersionUID = 4063994934255971594L;

  /**
   * 租户ID
   */
  private Long tenantID;
 
  /**
   * 周期内用电的总度数
   */
  private BigDecimal electricityCount;
  
  /**
   * 周期内用水的总吨数
   */
  private BigDecimal waterCount;
  
  /**
   * 水电费统计周期
   */
  private Date waterElectricityStatiticsCycle;
  
  @Index (name = "report_water_electricity_record_tenantid")
  public Long getTenantID ()
  {
    return tenantID;
  }

  public void setTenantID (Long tenantID)
  {
    this.tenantID = tenantID;
  }

  @JsonProperty
  public BigDecimal getElectricityCount ()
  {
    return electricityCount;
  }

  public void setElectricityCount (BigDecimal electricityCount)
  {
    this.electricityCount = electricityCount;
  }

  @JsonProperty
  public BigDecimal getWaterCount ()
  {
    return waterCount;
  }

  public void setWaterCount (BigDecimal waterCount)
  {
    this.waterCount = waterCount;
  }

  @JsonProperty
  public Date getWaterElectricityStatiticsCycle ()
  {
    return waterElectricityStatiticsCycle;
  }

  public void setWaterElectricityStatiticsCycle (
      Date waterElectricityStatiticsCycle)
  {
    this.waterElectricityStatiticsCycle = waterElectricityStatiticsCycle;
  }

}
