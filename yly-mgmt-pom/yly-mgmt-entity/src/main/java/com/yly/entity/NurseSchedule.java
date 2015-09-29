package com.yly.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.yly.entity.base.BaseEntity;

/**
 * 护理员排班
 * 
 * @author sujinxuan
 *
 */
@Entity
@Table(name = "yly_nurse_schedule")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_nurse_schedule_sequence")
public class NurseSchedule extends BaseEntity{
  
  private static final long serialVersionUID = -4675199294331320559L;

  /**
   * 租户ID
   */
  private Long tenantID;
  
  /**
   * 排班开始时间
   */
  private Date dutyStartTime;
  
  /**
   * 排班结束时间
   */
  private Date dutyEndTime;
  
  /**
   * 值班类型
   */
  private String dutyType;
  
  /**
   * 值班人姓名（可以是多个，中间逗号隔开）
   */
  private String dutyStaff;
  
  
  public Date getDutyStartTime() {
    return dutyStartTime;
  }

  public void setDutyStartTime(Date dutyStartTime) {
    this.dutyStartTime = dutyStartTime;
  }

  public Date getDutyEndTime() {
    return dutyEndTime;
  }

  public void setDutyEndTime(Date dutyEndTime) {
    this.dutyEndTime = dutyEndTime;
  }

  @Column(length=10)
  public String getDutyType() {
    return dutyType;
  }

  public void setDutyType(String dutyType) {
    this.dutyType = dutyType;
  }

  @Column(length=50)
  public String getDutyStaff() {
    return dutyStaff;
  }

  public void setDutyStaff(String dutyStaff) {
    this.dutyStaff = dutyStaff;
  }

  @Index(name="nurse_schedule_tenantid")
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }

  
}
