package com.yly.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yly.entity.base.BaseEntity;
import com.yly.entity.commonenum.CommonEnum.DoseFrequency;

/**
 * 处方药品对应表
 * 
 * @author shijun
 *
 */
@Entity
@Table(name = "yly_prescription_drugs_items")
@SequenceGenerator(name = "sequenceGenerator",
    sequenceName = "yly_prescription_drugs_items_sequence")
public class PrescriptionDrugsItems extends BaseEntity {

  /**
   * 
   */
  private static final long serialVersionUID = 7298294818920168701L;

  /**
   * 处方
   */
  private Prescription prescription;

  /**
   * 药品
   */
  private DrugsInfo drugsInfo;
  
  /**
   * 单次用量
   */
  private Float singleDose;
  
  /**
   * 频度
   */
  private DoseFrequency doseFrequency;
  
  /**
   * 药品用法
   */
  private SystemConfig drugUseMethod;
  
  /**
   * 用药天数
   */
  private Integer medicationDays;
  
  /**
   * 药总数
   */
  private Float medicineTotal;
  

  @ManyToOne(fetch = FetchType.LAZY)
  public Prescription getPrescription() {
    return prescription;
  }

  public void setPrescription(Prescription prescription) {
    this.prescription = prescription;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  public DrugsInfo getDrugsInfo() {
    return drugsInfo;
  }

  public void setDrugsInfo(DrugsInfo drugsInfo) {
    this.drugsInfo = drugsInfo;
  }

  public Float getSingleDose() {
    return singleDose;
  }

  public void setSingleDose(Float singleDose) {
    this.singleDose = singleDose;
  }

  public DoseFrequency getDoseFrequency() {
    return doseFrequency;
  }

  public void setDoseFrequency(DoseFrequency doseFrequency) {
    this.doseFrequency = doseFrequency;
  }

  @ManyToOne
  public SystemConfig getDrugUseMethod() {
    return drugUseMethod;
  }

  public void setDrugUseMethod(SystemConfig drugUseMethod) {
    this.drugUseMethod = drugUseMethod;
  }

  public Integer getMedicationDays() {
    return medicationDays;
  }

  public void setMedicationDays(Integer medicationDays) {
    this.medicationDays = medicationDays;
  }

  public Float getMedicineTotal() {
    return medicineTotal;
  }

  public void setMedicineTotal(Float medicineTotal) {
    this.medicineTotal = medicineTotal;
  }
}
