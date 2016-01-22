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
 *         老人换床(房)记录
 */

@Entity
@Table(name = "yly_bed_change_record")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_bed_change_record_sequence")
public class BedChangeRecord extends BaseEntity {

  /**
   * 
   */
  private static final long serialVersionUID = -6055255863529939746L;



  /**
   * 变更前床位
   */
  private Bed oldBed;

  /**
   * 变更后床位
   */
  private Bed newBed;


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
  public Bed getOldBed() {
    return oldBed;
  }

  public void setOldBed(Bed oldBed) {
    this.oldBed = oldBed;
  }

  @ManyToOne
  public Bed getNewBed() {
    return newBed;
  }

  public void setNewBed(Bed newBed) {
    this.newBed = newBed;
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
