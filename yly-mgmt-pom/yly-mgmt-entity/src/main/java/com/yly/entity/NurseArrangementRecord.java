package com.yly.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;
import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Resolution;
import org.hibernate.search.annotations.Store;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yly.entity.base.BaseEntity;

/**
 * 护理员安排记录
 * @author luzhang
 *
 */
@Entity
@Table(name = "yly_nurse_arrangement_record")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_nurse_arrangement_record_sequence")
@Indexed(index = "nurseArrangement/nurseArrangementRecord")
public class NurseArrangementRecord extends BaseEntity{

  private static final long serialVersionUID = -2032017101345631052L;

  /**
   * 租户ID
   */
  private Long tenantID;
  
  /**
   * 护理执行时间
   */
  private Date nurseServiceTime;
  /**
   * 护理员姓名
   */
  private String nurseOperator;
  /**
   * 所属的护理安排
   */
  private NurseArrangement nurseArrangement;
  
  /**
   * 备注
   */
  private String remark;
  
  @Index(name="nurse_arrangement_record_tenantid")
  public Long getTenantID() {
    return tenantID;
  }
  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }
  @Field(index = org.hibernate.search.annotations.Index.UN_TOKENIZED, store = Store.NO)
  @DateBridge(resolution = Resolution.DAY)
  public Date getNurseServiceTime() {
    return nurseServiceTime;
  }
  public void setNurseServiceTime(Date nurseServiceTime) {
    this.nurseServiceTime = nurseServiceTime;
  }
  @JsonProperty
  @Column(length=50)
  public String getNurseOperator() {
    return nurseOperator;
  }
  public void setNurseOperator(String nurseOperator) {
    this.nurseOperator = nurseOperator;
  }
  @ManyToOne(fetch = FetchType.LAZY)
  public NurseArrangement getNurseArrangement() {
    return nurseArrangement;
  }
  public void setNurseArrangement(NurseArrangement nurseArrangement) {
    this.nurseArrangement = nurseArrangement;
  }
  @JsonProperty
  @Column(length=500)
  public String getRemark() {
    return remark;
  }
  public void setRemark(String remark) {
    this.remark = remark;
  }
  
  
}
