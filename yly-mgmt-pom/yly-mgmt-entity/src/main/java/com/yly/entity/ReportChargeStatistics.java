package com.yly.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
@Table (name = "yly_report_charge_statistics")
@SequenceGenerator (name = "sequenceGenerator", sequenceName = "yly_report_charge_statistics_sequence")
public class ReportChargeStatistics extends BaseEntity
{

  private static final long serialVersionUID = 4063994934255971594L;

  /**
   * 租户ID
   */
  private Long tenantID;

  /**
   * 护理费
   */
  private BigDecimal nurseCharge;

  /**
   * 床位费
   */
  private BigDecimal bedCharge;

  /**
   * 伙食费
   */
  private BigDecimal mealCharge;

  /**
   * 个性化服务费
   */
  private BigDecimal persionalizedCharge;

  /**
   * 预存款
   */
  private BigDecimal advanceCharge;
  
  /**
   * 统计月份
   */
  private Date statisticsDate;

  @Index (name = "yly_report_charge_statistics_tenantid")
  public Long getTenantID ()
  {
    return tenantID;
  }

  public void setTenantID (Long tenantID)
  {
    this.tenantID = tenantID;
  }

  @JsonProperty
  public BigDecimal getNurseCharge ()
  {
    return nurseCharge;
  }

  public void setNurseCharge (BigDecimal nurseCharge)
  {
    this.nurseCharge = nurseCharge;
  }

  @JsonProperty
  public BigDecimal getBedCharge ()
  {
    return bedCharge;
  }

  public void setBedCharge (BigDecimal bedCharge)
  {
    this.bedCharge = bedCharge;
  }

  @JsonProperty
  public BigDecimal getMealCharge ()
  {
    return mealCharge;
  }

  public void setMealCharge (BigDecimal mealCharge)
  {
    this.mealCharge = mealCharge;
  }

  @JsonProperty
  public BigDecimal getPersionalizedCharge ()
  {
    return persionalizedCharge;
  }

  public void setPersionalizedCharge (BigDecimal persionalizedCharge)
  {
    this.persionalizedCharge = persionalizedCharge;
  }

  @JsonProperty
  @Temporal (TemporalType.DATE)
  public Date getStatisticsDate ()
  {
    return statisticsDate;
  }

  public void setStatisticsDate (Date statisticsDate)
  {
    this.statisticsDate = statisticsDate;
  }

  @JsonProperty
  public BigDecimal getAdvanceCharge ()
  {
    return advanceCharge;
  }

  public void setAdvanceCharge (BigDecimal advanceCharge)
  {
    this.advanceCharge = advanceCharge;
  }

  
}
