package com.yly.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yly.entity.base.BaseEntity;


/**
 * 
 * 护理级别统计
 * @author sujinxuan
 *
 */
@Entity
@Table(name = "yly_report_nurse_level_statistics")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_report_nurse_level_statistics_sequence")
public class ReportNurseLevelStatistics extends BaseEntity{
   
  private static final long serialVersionUID = 697841321332586266L;
  
  /**
   * 护理级别
   */
  private SystemConfig nursingLevel;
  /**
   * 该护理级别的老人人数
   */
  private Integer elderlyCount;
  /**
   * 租户ID
   */
  private Long tenantID;
  
  /**
   * 统计时间（按月统计）
   */
  private Date statisticsDate;
  
  @JsonProperty
  public Date getStatisticsDate() {
    return statisticsDate;
  }

  public void setStatisticsDate(Date statisticsDate) {
    this.statisticsDate = statisticsDate;
  }
  
  @ManyToOne
  @JsonProperty
  public SystemConfig getNursingLevel() {
    return nursingLevel;
  }

  public void setNursingLevel(SystemConfig nursingLevel) {
    this.nursingLevel = nursingLevel;
  }

  @JsonProperty
  public Integer getElderlyCount() {
    return elderlyCount;
  }

  public void setElderlyCount(Integer elderlyCount) {
    this.elderlyCount = elderlyCount;
  }

  @Index(name="report_nurse_level_statistics_tenantid")
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }

  
}
