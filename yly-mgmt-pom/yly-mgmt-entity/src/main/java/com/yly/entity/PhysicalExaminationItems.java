package com.yly.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yly.entity.base.BaseEntity;

/**
 * 体检项
 * 
 * @author shijun
 *
 */
@Entity
@Table(name = "yly_physical_examination_items")
@SequenceGenerator(name = "sequenceGenerator",
    sequenceName = "yly_physical_examination_items_sequence")
public class PhysicalExaminationItems extends BaseEntity {

  /**
   * 
   */
  private static final long serialVersionUID = 4681769790429459845L;

  private PhysicalExamination physicalExamination;
  
  private PhysicalExaminationItemConfig physicalExaminationItem;
  
  private String physicalExaminationItemValue;
 
  @ManyToOne(fetch = FetchType.LAZY)
  public PhysicalExamination getPhysicalExamination() {
    return physicalExamination;
  }

  public void setPhysicalExamination(PhysicalExamination physicalExamination) {
    this.physicalExamination = physicalExamination;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  public PhysicalExaminationItemConfig getPhysicalExaminationItem() {
    return physicalExaminationItem;
  }

  public void setPhysicalExaminationItem(PhysicalExaminationItemConfig physicalExaminationItem) {
    this.physicalExaminationItem = physicalExaminationItem;
  }

  @Column(length = 50)
  public String getPhysicalExaminationItemValue() {
    return physicalExaminationItemValue;
  }

  public void setPhysicalExaminationItemValue(String physicalExaminationItemValue) {
    this.physicalExaminationItemValue = physicalExaminationItemValue;
  }
}
