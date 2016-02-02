package com.yly.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.yly.entity.base.BaseEntity;
import com.yly.entity.commonenum.CommonEnum.NurseChangeStatus;
import com.yly.lucene.DateBridgeImpl;

/**
 * 
 * @author sujinxuan
 *
 *         护理级别变更记录
 */

@Entity
@Table(name = "yly_nurse_level_change_record")
@SequenceGenerator(name = "sequenceGenerator",
    sequenceName = "yly_nurse_level_change_record_sequence")
@Indexed(index = "nurseLevelChangeRecord")
public class NurseLevelChangeRecord extends BaseEntity {

  /**
   * 
   */
  private static final long serialVersionUID = -6055255863529939746L;

  /**
   * 租户ID
   */
  private Long tenantID;
  

  /**
   * 变更前护理级别
   */
  private SystemConfig oldNurseLevel;

  /**
   * 变更后护理级别
   */
  private SystemConfig newNurseLevel;


  /**
   * 变更日期
   */
  private Date changeDate;
  
  /**
   * 护理级别变更记录的状态
   */
  private NurseChangeStatus nurseChangeStatus;
 
  /**
   * 备注
   */
  private String remark;

  /**
   * 老人
   */
  private ElderlyInfo elderlyInfo;
  
  @Index(name = "nurse_level_change_record_tenantid")
  @Field(store = Store.NO, index = org.hibernate.search.annotations.Index.UN_TOKENIZED,
      analyzer = @Analyzer(impl = IKAnalyzer.class))
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }
  
  @Index(name = "nurse_level_change_record_nursechangestatus")
  @JsonProperty
  @Field(index = org.hibernate.search.annotations.Index.UN_TOKENIZED, store = Store.NO)
  public NurseChangeStatus getNurseChangeStatus() {
    return nurseChangeStatus;
  }

  public void setNurseChangeStatus(NurseChangeStatus nurseChangeStatus) {
    this.nurseChangeStatus = nurseChangeStatus;
  }
  
  @ManyToOne
  @JsonProperty
  public SystemConfig getOldNurseLevel() {
    return oldNurseLevel;
  }

  public void setOldNurseLevel(SystemConfig oldNurseLevel) {
    this.oldNurseLevel = oldNurseLevel;
  }

  @ManyToOne
  @JsonProperty
  public SystemConfig getNewNurseLevel() {
    return newNurseLevel;
  }

  public void setNewNurseLevel(SystemConfig newNurseLevel) {
    this.newNurseLevel = newNurseLevel;
  }

  @Temporal(TemporalType.DATE)
  @JsonProperty
  @Field(index = org.hibernate.search.annotations.Index.UN_TOKENIZED, store = Store.NO)
  @FieldBridge(impl = DateBridgeImpl.class)
  public Date getChangeDate() {
    return changeDate;
  }

  public void setChangeDate(Date changeDate) {
    this.changeDate = changeDate;
  }

  @Column(length = 100)
  @JsonProperty
  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  @JsonProperty
  @ManyToOne(fetch = FetchType.LAZY)
  @IndexedEmbedded
  public ElderlyInfo getElderlyInfo() {
    return elderlyInfo;
  }

  public void setElderlyInfo(ElderlyInfo elderlyInfo) {
    this.elderlyInfo = elderlyInfo;
  }



}
