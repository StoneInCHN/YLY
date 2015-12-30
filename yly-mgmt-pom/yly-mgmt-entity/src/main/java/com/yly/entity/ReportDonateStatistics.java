package com.yly.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yly.entity.base.BaseEntity;
import com.yly.entity.commonenum.CommonEnum.DonateType;

/**
 * 
 * 
 * @author yohu
 *
 */
@Entity
@Table (name = "yly_report_donate_statistics")
@SequenceGenerator (name = "sequenceGenerator", sequenceName = "yly_report_donate_statistics_sequence")
public class ReportDonateStatistics extends BaseEntity
{

  private static final long serialVersionUID = 4063994934255971594L;

  /**
   * 租户ID
   */
  private Long tenantID;

  /**
   * 捐赠物品（钱或具体的物品）
   */
  private String donateName;
  /**
   * 统计捐赠数量，可能是物或钱
   */
  private Double donateCount;
  /**
   * 赠送类型
   */
  private DonateType donateType;
  /**
   * 统计周期
   */
  private Date donateStatisticsCycle;

  @Index (name = "report_donate_statistics_tenantid")
  public Long getTenantID ()
  {
    return tenantID;
  }

  public void setTenantID (Long tenantID)
  {
    this.tenantID = tenantID;
  }

  @JsonProperty
  public String getDonateName ()
  {
    return donateName;
  }

  public void setDonateName (String donateName)
  {
    this.donateName = donateName;
  }

  @JsonProperty
  public Double getDonateCount ()
  {
    return donateCount;
  }

  public void setDonateCount (Double donateCount)
  {
    this.donateCount = donateCount;
  }

  @JsonProperty
  public Date getDonateStatisticsCycle ()
  {
    return donateStatisticsCycle;
  }

  public void setDonateStatisticsCycle (Date donateStatisticsCycle)
  {
    this.donateStatisticsCycle = donateStatisticsCycle;
  }

  @JsonProperty
  public DonateType getDonateType ()
  {
    return donateType;
  }

  public void setDonateType (DonateType donateType)
  {
    this.donateType = donateType;
  }

}
