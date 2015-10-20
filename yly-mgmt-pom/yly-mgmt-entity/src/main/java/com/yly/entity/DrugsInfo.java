package com.yly.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yly.entity.base.BaseEntity;
import com.yly.entity.commonenum.CommonEnum.DosageUnit;
import com.yly.entity.commonenum.CommonEnum.DoseFrequency;
import com.yly.entity.commonenum.CommonEnum.DrugStatus;

/**
 * 药品信息
 * 
 * @author shijun
 *
 */
@Entity
@Table(name = "yly_drugs_info")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_drugs_info_sequence")
@Indexed(index="medical/drugsInfo")
public class DrugsInfo extends BaseEntity {

  /**
   * 
   */
  private static final long serialVersionUID = -7623136186617988017L;

  /**
   * 租户ID
   */
  private Long tenantID;

  /**
   * 药品名称
   */
  private String name;

  /**
   * 别名
   */
  private String alias;

  /**
   * 拼音简码
   */
  private String phoneticCode;

  /**
   * 药品分类
   */
  private SystemConfig drugCategory;

  /**
   * 药品常用单位
   */
  private SystemConfig conventionalUnit;

  /**
   * 药品最小单位
   */
  private SystemConfig minUnit;

  /**
   * 换算单位
   */
  private Float conversionUnit;

  /**
   * 剂量单位
   */
  private DosageUnit dosageUnit;

  /**
   * 药品用法
   */
  private SystemConfig drugUseMethod;

  /**
   * 剂量
   */
  private Float dosage;

  /**
   * 药品规格
   */
  private String drugSpecifications;

  /**
   * 频度
   */
  private DoseFrequency doseFrequency;

  /**
   * 单次用量
   */
  private Float singleDose;

  /**
   * 生产厂家
   */
  private String manufacturer;

  /**
   * 药品状态
   */
  private DrugStatus drugStatus;

  /**
   * 备注
   */
  private String remark;

  /**
   * 处方价格
   */
  private BigDecimal prescriptionPrice;

  /**
   * 采购价格
   */
  private BigDecimal purchasePrice;
  
  private Set<PrescriptionDrugsItems> prescriptionDrugsItems = new HashSet<PrescriptionDrugsItems>();
  
  @Column(length = 60)
  @JsonProperty
  @Field(store = Store.NO, index = org.hibernate.search.annotations.Index.TOKENIZED, analyzer = @Analyzer(impl = IKAnalyzer.class))
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Column(length = 60)
  @JsonProperty
  @Field(store = Store.NO, index = org.hibernate.search.annotations.Index.TOKENIZED, analyzer = @Analyzer(impl = IKAnalyzer.class))
  public String getAlias() {
    return alias;
  }

  public void setAlias(String alias) {
    this.alias = alias;
  }

  @Column(length = 20)
  @JsonProperty
  public String getPhoneticCode() {
    return phoneticCode;
  }

  public void setPhoneticCode(String phoneticCode) {
    this.phoneticCode = phoneticCode;
  }

  @JsonProperty
  @ManyToOne
  public SystemConfig getDrugCategory() {
    return drugCategory;
  }

  public void setDrugCategory(SystemConfig drugCategory) {
    this.drugCategory = drugCategory;
  }

  @ManyToOne
  public SystemConfig getConventionalUnit() {
    return conventionalUnit;
  }

  public void setConventionalUnit(SystemConfig conventionalUnit) {
    this.conventionalUnit = conventionalUnit;
  }

  @ManyToOne
  public SystemConfig getMinUnit() {
    return minUnit;
  }

  public void setMinUnit(SystemConfig minUnit) {
    this.minUnit = minUnit;
  }

  public Float getConversionUnit() {
    return conversionUnit;
  }

  public void setConversionUnit(Float conversionUnit) {
    this.conversionUnit = conversionUnit;
  }

  public DosageUnit getDosageUnit() {
    return dosageUnit;
  }

  public void setDosageUnit(DosageUnit dosageUnit) {
    this.dosageUnit = dosageUnit;
  }

  @ManyToOne
  public SystemConfig getDrugUseMethod() {
    return drugUseMethod;
  }

  public void setDrugUseMethod(SystemConfig drugUseMethod) {
    this.drugUseMethod = drugUseMethod;
  }

  public Float getDosage() {
    return dosage;
  }

  public void setDosage(Float dosage) {
    this.dosage = dosage;
  }

  @Column(length = 30)
  public String getDrugSpecifications() {
    return drugSpecifications;
  }

  public void setDrugSpecifications(String drugSpecifications) {
    this.drugSpecifications = drugSpecifications;
  }

  public DoseFrequency getDoseFrequency() {
    return doseFrequency;
  }

  public void setDoseFrequency(DoseFrequency doseFrequency) {
    this.doseFrequency = doseFrequency;
  }

  public Float getSingleDose() {
    return singleDose;
  }

  public void setSingleDose(Float singleDose) {
    this.singleDose = singleDose;
  }

  @Column(length = 150)
  public String getManufacturer() {
    return manufacturer;
  }

  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  public DrugStatus getDrugStatus() {
    return drugStatus;
  }

  public void setDrugStatus(DrugStatus drugStatus) {
    this.drugStatus = drugStatus;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  @Index(name = "drugs_info_tenantid")
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }

//  @Field(index = org.hibernate.search.annotations.Index.NO)
  public BigDecimal getPrescriptionPrice() {
    return prescriptionPrice;
  }

  public void setPrescriptionPrice(BigDecimal prescriptionPrice) {
    this.prescriptionPrice = prescriptionPrice;
  }

  public BigDecimal getPurchasePrice() {
    return purchasePrice;
  }

  public void setPurchasePrice(BigDecimal purchasePrice) {
    this.purchasePrice = purchasePrice;
  }

  @OneToMany(mappedBy = "drugsInfo" , fetch = FetchType.LAZY)
  public Set<PrescriptionDrugsItems> getPrescriptionDrugsItems() {
    return prescriptionDrugsItems;
  }

  public void setPrescriptionDrugsItems(Set<PrescriptionDrugsItems> prescriptionDrugsItems) {
    this.prescriptionDrugsItems = prescriptionDrugsItems;
  }
}
