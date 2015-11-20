package com.yly.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.yly.entity.base.BaseEntity;

@Entity
@Table(name = "yly_nurse_duty_type")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_nurse_duty_type_sequence")
public class NurseDutyType extends BaseEntity {

  /**
   * 
   */
  private static final long serialVersionUID = 790617090017918707L;
  
  
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
   * 班次名称
   */
  private String dutyName;
  
  /**
   * 备注
   */
  private String remark;

  @Index(name="nurse_duty_type_tenantid")
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }

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
  public String getDutyName() {
    return dutyName;
  }

  public void setDutyName(String dutyName) {
    this.dutyName = dutyName;
  }

  @Column(length=200)
  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

}
