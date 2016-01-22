package com.yly.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yly.entity.base.BaseEntity;

/**
 * 护理计划
 * 
 * @author sujinxuan
 *
 */
@Entity
@Table(name = "yly_nurse_plan")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_nurse_plan")
public class NursePlan extends BaseEntity{
  
  private static final long serialVersionUID = 4949302048608695341L;

  /**
   * 租户ID
   */
  private Long tenantID;
  
  
  /***
   * 计划开始时间
   */
  private Date planStartDate;
  
  /**
   * 计划结束时间
   */
  private Date planEndDate;
  
  /**
   * 备注
   */
  private String remark;
  
  /**
   * 计划内容
   */
  private String planContent;

  /**
   * 计划名称
   */
  private String planName;

  
  @Column(length=200)
  @JsonProperty
  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  @Index(name="nurse_plan_tenantid")
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }

  @JsonProperty
  public Date getPlanStartDate() {
    return planStartDate;
  }

  public void setPlanStartDate(Date planStartDate) {
    this.planStartDate = planStartDate;
  }

  @JsonProperty
  public Date getPlanEndDate() {
    return planEndDate;
  }

  public void setPlanEndDate(Date planEndDate) {
    this.planEndDate = planEndDate;
  }

  @Column(length=200)
  @JsonProperty
  public String getPlanContent() {
    return planContent;
  }

  public void setPlanContent(String planContent) {
    this.planContent = planContent;
  }

  @Column(length=30)
  @JsonProperty
  public String getPlanName() {
    return planName;
  }

  public void setPlanName(String planName) {
    this.planName = planName;
  }

  
}
