package com.yly.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.yly.entity.base.BaseEntity;

/**
 * 个性化服务护理
 * 
 * @author sujinxuan
 *
 */
@Entity
@Table(name = "yly_personalized_nurse")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_personalized_nurse_sequence")
public class PersonalizedNurse extends BaseEntity {

  private static final long serialVersionUID = 5389704995256000137L;


  /**
   * 租户ID
   */
  private Long tenantID;

  /**
   * 老人
   */
  private ElderlyInfo elderlyInfo;

  /**
   * 登记时间
   */
  private Date applyTime;

  /**
   * 服务总次数
   */
  private Integer sumCount;

  /**
   * 已使用的次数
   */
  private Integer usedCount;

  /**
   * 护理内容
   */
  private String nurseContent;

  /**
   * 经办人
   */
  private String operator;

  /**
   * 备注
   */
  private String remark;

  /**
   * 服务单价
   */
  private BigDecimal servicePrice;
  /**
   * 服务记录
   */
  private Set<PersonalizedRecord> personalizedRecords = new HashSet<PersonalizedRecord>();
  
  
  @Column(nullable = false, precision = 12, scale = 2)
  public BigDecimal getServicePrice() {
    return servicePrice;
  }

  public void setServicePrice(BigDecimal servicePrice) {
    this.servicePrice = servicePrice;
  }

  @OneToMany(mappedBy="personalizedNurse")
  public Set<PersonalizedRecord> getPersonalizedRecords() {
    return personalizedRecords;
  }

  public void setPersonalizedRecords(Set<PersonalizedRecord> personalizedRecords) {
    this.personalizedRecords = personalizedRecords;
  }

  @Column(length = 50)
  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  @Column(length = 15)
  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }

  @Index(name = "personalized_nurse_tenantid")
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }

  @ManyToOne
  public ElderlyInfo getElderlyInfo() {
    return elderlyInfo;
  }

  public void setElderlyInfo(ElderlyInfo elderlyInfo) {
    this.elderlyInfo = elderlyInfo;
  }

  public Date getApplyTime() {
    return applyTime;
  }

  public void setApplyTime(Date applyTime) {
    this.applyTime = applyTime;
  }

  public Integer getSumCount() {
    return sumCount;
  }

  public void setSumCount(Integer sumCount) {
    this.sumCount = sumCount;
  }

  public Integer getUsedCount() {
    return usedCount;
  }

  public void setUsedCount(Integer usedCount) {
    this.usedCount = usedCount;
  }
  
  @Column(length=20)
  public String getNurseContent() {
    return nurseContent;
  }

  public void setNurseContent(String nurseContent) {
    this.nurseContent = nurseContent;
  }

}
