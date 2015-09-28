package com.yly.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.yly.entity.base.BaseEntity;


/**
 * 
 * 员工人数统计
 * @author sujinxuan
 *
 */
@Entity
@Table(name = "yly_user_statistics")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_nurse_level_statistics_sequence")
public class UserStatistics extends BaseEntity{
   
  private static final long serialVersionUID = 697841321332586266L;
  /**
   * 租户ID
   */
  private Long tenantID;
  /**
   * 员工总人数
   */
  private Integer sumStaff;
  /**
   * 护理人员
   */
  private Integer nurseStaff;
  /**
   * 行政人员
   */
  private Integer adminStaff;
  
  /**
   * 统计时间（按月统计）
   */
  private Date statisticsDate;
  
  
  public Integer getSumStaff() {
    return sumStaff;
  }

  public void setSumStaff(Integer sumStaff) {
    this.sumStaff = sumStaff;
  }

  public Integer getNurseStaff() {
    return nurseStaff;
  }

  public void setNurseStaff(Integer nurseStaff) {
    this.nurseStaff = nurseStaff;
  }

  public Integer getAdminStaff() {
    return adminStaff;
  }

  public void setAdminStaff(Integer adminStaff) {
    this.adminStaff = adminStaff;
  }

  public Date getStatisticsDate() {
    return statisticsDate;
  }

  public void setStatisticsDate(Date statisticsDate) {
    this.statisticsDate = statisticsDate;
  }
  
  @Index(name="user_statistics_tenantid")
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }

  
}
