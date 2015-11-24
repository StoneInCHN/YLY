package com.yly.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
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
 * 病历
 * 
 * @author shijun
 *
 */

@Entity
@Table(name = "yly_medical_record")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_medical_record_sequence")
@Indexed(index="medicalRecord")
public class MedicalRecord extends BaseEntity {

  /**
   * 
   */
  private static final long serialVersionUID = 4020526053535962966L;
  
  /**
   * 租户ID
   */
  private Long tenantID;

  /**
   * 主诉
   */
  private String chiefComplaint;

  /**
   * 病史
   */
  private String medicalHistory;

  /**
   * 过敏史
   */
  private String allergicHistory;

  /**
   * 门诊诊断
   */
  private String clinicDiagnosis;

  /**
   * 治疗意见
   */
  private String treatmentAdvice;

  /**
   * 门诊处理
   */
  private String outpatientTreatment;

  /**
   * 门诊医嘱
   */
  private String outpatientMedicalAdvice;
  
  private ElderlyInfo elderlyInfo;

  public String getChiefComplaint() {
    return chiefComplaint;
  }

  public void setChiefComplaint(String chiefComplaint) {
    this.chiefComplaint = chiefComplaint;
  }

  public String getMedicalHistory() {
    return medicalHistory;
  }

  public void setMedicalHistory(String medicalHistory) {
    this.medicalHistory = medicalHistory;
  }

  public String getAllergicHistory() {
    return allergicHistory;
  }

  public void setAllergicHistory(String allergicHistory) {
    this.allergicHistory = allergicHistory;
  }

  public String getClinicDiagnosis() {
    return clinicDiagnosis;
  }

  public void setClinicDiagnosis(String clinicDiagnosis) {
    this.clinicDiagnosis = clinicDiagnosis;
  }

  public String getTreatmentAdvice() {
    return treatmentAdvice;
  }

  public void setTreatmentAdvice(String treatmentAdvice) {
    this.treatmentAdvice = treatmentAdvice;
  }

  public String getOutpatientTreatment() {
    return outpatientTreatment;
  }

  public void setOutpatientTreatment(String outpatientTreatment) {
    this.outpatientTreatment = outpatientTreatment;
  }

  public String getOutpatientMedicalAdvice() {
    return outpatientMedicalAdvice;
  }

  public void setOutpatientMedicalAdvice(String outpatientMedicalAdvice) {
    this.outpatientMedicalAdvice = outpatientMedicalAdvice;
  }

  @Index(name = "medical_record_tenantid")
  @Field(index = org.hibernate.search.annotations.Index.UN_TOKENIZED, store = Store.NO)
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }

  @JsonProperty
  @ManyToOne(fetch = FetchType.LAZY)
  @Index(name = "medical_record_elderly")
  @IndexedEmbedded
  public ElderlyInfo getElderlyInfo() {
    return elderlyInfo;
  }

  public void setElderlyInfo(ElderlyInfo elderlyInfo) {
    this.elderlyInfo = elderlyInfo;
  }
}
