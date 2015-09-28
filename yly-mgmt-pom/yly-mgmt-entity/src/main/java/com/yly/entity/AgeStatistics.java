package com.yly.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.yly.entity.base.BaseEntity;

/**
 * 老人年龄统计
 * 
 * @author sujinxuan
 *
 */
@Entity
@Table(name = "yly_age_statistics")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_age_statistics_sequence")
public class AgeStatistics extends BaseEntity{
  
  private static final long serialVersionUID = 934130269765552612L;

  /**
   * 租户ID
   */
  private Long tenantID;
  /**
   * 男性人数
   */
  private Integer males;
  /**
   * 女性人数
   */
  private Integer females;
  /**
   * 60岁以下(不包含60)人数
   */
  private Integer beforeSixty;
  /**
   * 60-70岁人数(不包含70)
   */
  private Integer seventy;
  /**
   * 70-80岁人数(不包含80)
   */
  private Integer engity;
  /**
   * 80-90岁人数(不包含90)
   */
  private Integer ninety;
  /**
   * 90岁以上(包含90)
   */
  private Integer overNinety;
  /**
   * 在院老人总人数
   */
  private Integer elderlyCount;
  /**
   * 在院老人平均年龄
   */
  private Integer averageAge;
  /**
   * 统计时间（按月统计）
   */
  private Date statisticsDate;
  
  
  public Date getStatisticsDate() {
    return statisticsDate;
  }

  public void setStatisticsDate(Date statisticsDate) {
    this.statisticsDate = statisticsDate;
  }

  public Integer getMales() {
    return males;
  }

  public void setMales(Integer males) {
    this.males = males;
  }

  public Integer getFemales() {
    return females;
  }

  public void setFemales(Integer females) {
    this.females = females;
  }

  public Integer getBeforeSixty() {
    return beforeSixty;
  }

  public void setBeforeSixty(Integer beforeSixty) {
    this.beforeSixty = beforeSixty;
  }

  public Integer getSeventy() {
    return seventy;
  }

  public void setSeventy(Integer seventy) {
    this.seventy = seventy;
  }

  public Integer getEngity() {
    return engity;
  }

  public void setEngity(Integer engity) {
    this.engity = engity;
  }

  public Integer getNinety() {
    return ninety;
  }

  public void setNinety(Integer ninety) {
    this.ninety = ninety;
  }

  public Integer getOverNinety() {
    return overNinety;
  }

  public void setOverNinety(Integer overNinety) {
    this.overNinety = overNinety;
  }

  public Integer getElderlyCount() {
    return elderlyCount;
  }

  public void setElderlyCount(Integer elderlyCount) {
    this.elderlyCount = elderlyCount;
  }

  public Integer getAverageAge() {
    return averageAge;
  }

  public void setAverageAge(Integer averageAge) {
    this.averageAge = averageAge;
  }

  @Index(name="age_statistics_tenantid")
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }
 
}
