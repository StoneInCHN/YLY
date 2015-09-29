package com.yly.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.yly.entity.base.BaseEntity;

/**
 * 体检实例
 * 
 * @author shijun
 *
 */

@Entity
@Table(name = "yly_physical_examination")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_physical_examination_sequence")
public class PhysicalExamination extends BaseEntity {

  /**
   * 
   */
  private static final long serialVersionUID = 334363512760506116L;

  /**
   * 租户ID
   */
  private Long tenantID;

  /**
   * 老人信息(体检人)
   */
  private ElderlyInfo elderlyInfo;

  /**
   * 体检时间
   */
  private Date physicalExaminationDate;

  /**
   * 录入人
   */
  private TenantUser operator;
  
  private Set<PhysicalExaminationItems> physicalExaminationItems = new HashSet<PhysicalExaminationItems>();

  @Index(name = "physical_examination_tenantid")
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }

  @Index(name = "physical_examination_elderlyinfo")
  public ElderlyInfo getElderlyInfo() {
    return elderlyInfo;
  }

  public void setElderlyInfo(ElderlyInfo elderlyInfo) {
    this.elderlyInfo = elderlyInfo;
  }

  public Date getPhysicalExaminationDate() {
    return physicalExaminationDate;
  }

  public void setPhysicalExaminationDate(Date physicalExaminationDate) {
    this.physicalExaminationDate = physicalExaminationDate;
  }

  public TenantUser getOperator() {
    return operator;
  }

  public void setOperator(TenantUser operator) {
    this.operator = operator;
  }

  @OneToMany(mappedBy = "physicalExamination" , fetch = FetchType.LAZY)
  public Set<PhysicalExaminationItems> getPhysicalExaminationItems() {
    return physicalExaminationItems;
  }

  public void setPhysicalExaminationItems(Set<PhysicalExaminationItems> physicalExaminationItems) {
    this.physicalExaminationItems = physicalExaminationItems;
  }
  
}
