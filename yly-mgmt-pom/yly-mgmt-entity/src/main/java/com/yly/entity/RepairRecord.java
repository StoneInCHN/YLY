package com.yly.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;
import org.hibernate.search.annotations.Indexed;

import com.yly.entity.base.BaseEntity;

/**
 * 维修记录
 * 
 * @author sujinxuan
 *
 */
@Entity
@Table(name = "yly_repair_record")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_repair_record_sequence")
@Indexed(index="repairRecord")
public class RepairRecord extends BaseEntity {

  private static final long serialVersionUID = 8257363153365363828L;
  
  /**
   * 维修项目
   */
  private String repairContent;
  
  /**
   * 报修人
   */
  private String reportOperator;
  
  /**
   * 维修地点
   */
  private String repairPlace;
  
  /**
   * 维修人员
   */
  private String repairOperator;

  /**
   * 联系电话
   */
  private String contactPhone;
  
  /**
   * 备注
   */
  private String remark;
  /**
   * 租户ID
   */
  private Long tenantID;
  
  
  @Column(length = 50)
  public String getRepairContent() {
    return repairContent;
  }

  public void setRepairContent(String repairContent) {
    this.repairContent = repairContent;
  }

  @Column(length = 50)
  public String getRepairPlace() {
    return repairPlace;
  }

  public void setRepairPlace(String repairPlace) {
    this.repairPlace = repairPlace;
  }

  @Column(length = 15)
  public String getRepairOperator() {
    return repairOperator;
  }

  public void setRepairOperator(String repairOperator) {
    this.repairOperator = repairOperator;
  }
  
  @Column(length = 15)
  public String getReportOperator() {
    return reportOperator;
  }

  public void setReportOperator(String reportOperator) {
    this.reportOperator = reportOperator;
  }

  @Index(name="repair_record_tenantid")
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }
  
  @Column(length = 50)
  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  @Column(length = 30)
  public String getContactPhone() {
    return contactPhone;
  }

  public void setContactPhone(String contactPhone) {
    this.contactPhone = contactPhone;
  }
  
}
