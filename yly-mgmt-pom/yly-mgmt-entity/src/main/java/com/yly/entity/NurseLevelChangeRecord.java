package com.yly.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.yly.entity.base.BaseEntity;

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
public class NurseLevelChangeRecord extends BaseEntity {

  /**
   * 
   */
  private static final long serialVersionUID = -6055255863529939746L;



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
   * 备注
   */
  private String remark;


  private ElderlyInfo elderlyInfo;

  @ManyToOne
  public SystemConfig getOldNurseLevel() {
    return oldNurseLevel;
  }

  public void setOldNurseLevel(SystemConfig oldNurseLevel) {
    this.oldNurseLevel = oldNurseLevel;
  }

  @ManyToOne
  public SystemConfig getNewNurseLevel() {
    return newNurseLevel;
  }

  public void setNewNurseLevel(SystemConfig newNurseLevel) {
    this.newNurseLevel = newNurseLevel;
  }

  @Temporal(TemporalType.DATE)
  public Date getChangeDate() {
    return changeDate;
  }

  public void setChangeDate(Date changeDate) {
    this.changeDate = changeDate;
  }

  @Column(length = 50)
  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  @ManyToOne
  public ElderlyInfo getElderlyInfo() {
    return elderlyInfo;
  }

  public void setElderlyInfo(ElderlyInfo elderlyInfo) {
    this.elderlyInfo = elderlyInfo;
  }



}
