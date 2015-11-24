package com.yly.entity;

import java.util.ArrayList;
import java.util.List;

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
import com.yly.entity.commonenum.CommonEnum.DoseFrequency;
import com.yly.entity.commonenum.CommonEnum.PrescriptionType;

/**
 * 处方
 * 
 * @author shijun
 *
 */
@Entity
@Table(name = "yly_prescription")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_prescription_sequence")
@Indexed(index="prescription")
public class Prescription extends BaseEntity {

  /**
   * 
   */
  private static final long serialVersionUID = 4820821997689833943L;

  /**
   * 租户ID
   */
  private Long tenantID;

  /**
   * 老人信息
   */
  private ElderlyInfo elderlyInfo;
  
  /**
   * 处方类型
   */
  private PrescriptionType prescriptionType;
  
  /**
   * 每日剂数(该属性主要是中药处方使用)
   */
  private Integer dailyDose;
  
  /**
   * 用药天数(该属性主要是中药处方使用)
   */
  private Integer medicationDays;
  
  /**
   * 频度(该属性主要是中药处方使用)
   */
  private DoseFrequency doseFrequency;
  
  /**
   * 药品用法(该属性主要是中药处方使用)
   */
  private SystemConfig drugUseMethod;
  
  private List<PrescriptionDrugsItems> prescriptionDrugsItems = new ArrayList<PrescriptionDrugsItems>();

  @Index(name = "prescription_tenantid")
  @Field(index = org.hibernate.search.annotations.Index.UN_TOKENIZED, store = Store.NO)
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
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

  @OneToMany(mappedBy = "prescription" , fetch = FetchType.LAZY,cascade=javax.persistence.CascadeType.ALL)
  
  public List<PrescriptionDrugsItems> getPrescriptionDrugsItems ()
  {
    return prescriptionDrugsItems;
  }

  public void setPrescriptionDrugsItems (
      List<PrescriptionDrugsItems> prescriptionDrugsItems)
  {
    this.prescriptionDrugsItems = prescriptionDrugsItems;
  }
  

  @JsonProperty
  public PrescriptionType getPrescriptionType() {
    return prescriptionType;
  }
 

  public void setPrescriptionType(PrescriptionType prescriptionType) {
    this.prescriptionType = prescriptionType;
  }

  public Integer getDailyDose() {
    return dailyDose;
  }

  public void setDailyDose(Integer dailyDose) {
    this.dailyDose = dailyDose;
  }

  public Integer getMedicationDays() {
    return medicationDays;
  }

  public void setMedicationDays(Integer medicationDays) {
    this.medicationDays = medicationDays;
  }

  public DoseFrequency getDoseFrequency() {
    return doseFrequency;
  }

  public void setDoseFrequency(DoseFrequency doseFrequency) {
    this.doseFrequency = doseFrequency;
  }

  @ManyToOne
  @JsonProperty
  public SystemConfig getDrugUseMethod() {
    return drugUseMethod;
  }

  public void setDrugUseMethod(SystemConfig drugUseMethod) {
    this.drugUseMethod = drugUseMethod;
  }
  
  
}
