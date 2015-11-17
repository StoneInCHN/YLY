package com.yly.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@Indexed(index="physicalExamination")
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
  
  private List<PhysicalExaminationItems> physicalExaminationItems = new ArrayList<PhysicalExaminationItems>();

  @Index(name = "physical_examination_tenantid")
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }

  @Index(name = "physical_examination_elderlyinfo")
  @ManyToOne(fetch=FetchType.LAZY)
  @JsonProperty
  @IndexedEmbedded
  public ElderlyInfo getElderlyInfo() {
    return elderlyInfo;
  }

  public void setElderlyInfo(ElderlyInfo elderlyInfo) {
    this.elderlyInfo = elderlyInfo;
  }
  
  @Field(index = org.hibernate.search.annotations.Index.UN_TOKENIZED, store = Store.NO)
  public Date getPhysicalExaminationDate() {
    return physicalExaminationDate;
  }

  public void setPhysicalExaminationDate(Date physicalExaminationDate) {
    this.physicalExaminationDate = physicalExaminationDate;
  }

  @ManyToOne(fetch=FetchType.LAZY)
  @JsonProperty
  @IndexedEmbedded
  public TenantUser getOperator() {
    return operator;
  }

  public void setOperator(TenantUser operator) {
    this.operator = operator;
  }

  @OneToMany(mappedBy = "physicalExamination" , fetch = FetchType.LAZY,cascade=CascadeType.ALL)
  public List<PhysicalExaminationItems> getPhysicalExaminationItems() {
    return physicalExaminationItems;
  }

  public void setPhysicalExaminationItems(List<PhysicalExaminationItems> physicalExaminationItems) {
    this.physicalExaminationItems = physicalExaminationItems;
  }
  
}
