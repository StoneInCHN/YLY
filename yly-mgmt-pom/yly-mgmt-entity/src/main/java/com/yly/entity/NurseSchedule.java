package com.yly.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.fasterxml.jackson.annotation.JsonProperty;
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
  private NurseDutyType dutyType;
  
  /**
   * 值班人姓名（可以是多个，中间逗号隔开）
   */
  private String dutyStaff;
  
  private TenantUser tenantUser;
  
  /**
   * 表单实例
   */
 // private Set<TenantUser> tenantUsers = new HashSet<TenantUser>();
  
  @JsonProperty
  public Date getDutyStartTime() {
    return dutyStartTime;
  }

  public void setDutyStartTime(Date dutyStartTime) {
    this.dutyStartTime = dutyStartTime;
  }

  @JsonProperty
  public Date getDutyEndTime() {
    return dutyEndTime;
  }

  public void setDutyEndTime(Date dutyEndTime) {
    this.dutyEndTime = dutyEndTime;
  }
  
  @ManyToOne
  //@Column(length=10)
  @JsonProperty
  public NurseDutyType getDutyType() {
    return dutyType;
  }

  public void setDutyType(NurseDutyType dutyType) {
    this.dutyType = dutyType;
  }

  @Column(length=50)
  @JsonProperty
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

  @ManyToOne
  @JsonProperty
  public TenantUser getTenantUser() {
    return tenantUser;
  }

  public void setTenantUser(TenantUser tenantUser) {
    this.tenantUser = tenantUser;
  }
  
}
