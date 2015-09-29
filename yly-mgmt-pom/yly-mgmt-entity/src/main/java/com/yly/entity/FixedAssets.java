package com.yly.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.yly.entity.base.BaseEntity;
import com.yly.entity.commonenum.CommonEnum.AssetsType;
import com.yly.entity.commonenum.CommonEnum.AssetsUsage;

/**
 * 固定资产
 * 
 * @author sujinxuan
 *
 */
@Entity
@Table(name = "yly_fixed_assets")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_fixed_assets_sequence")
public class FixedAssets extends BaseEntity {

  
  private static final long serialVersionUID = 3525183712762376969L;

  /**
   * 资产名称
   */
  private String assetName;
  
  /**
   * 存放地点(所属部门)
   */
  private Department department;
  
  /**
   * 资产编号
   */
  private String assetNo;
  
  /**
   * 资产数量
   */
  private Integer assetCount;
  
  /**
   * 资产计量单位
   */
  private String assetUnit;
  /**
   * 使用状态
   */
  private AssetsUsage assetUsage;
  
  /**
   * 规格型号
   */
  private String assetSpecification;
  
  /**
   * 产地
   */
  private String assetOrigin;
  /**
   * 制造商
   */
  private String assetManufacturer;
  /**
   * 供应商
   */
  private String assetProvider;
  /**
   * 备注
   */
  private String remark;
  
  /**
   * 租户ID
   */
  private Long tenantID;
  
  /**
   * 资产价值（RMB）
   */
  private BigDecimal assetValue;
  
  /**
   * 资产类型
   */
  private AssetsType assetsType;
  /**
   * 资产录入日期
   */
  private Date assetTime;
  
  
  public Integer getAssetCount() {
    return assetCount;
  }

  public void setAssetCount(Integer assetCount) {
    this.assetCount = assetCount;
  }

  @Column(length=10)
  public String getAssetUnit() {
    return assetUnit;
  }

  public void setAssetUnit(String assetUnit) {
    this.assetUnit = assetUnit;
  }

  public AssetsUsage getAssetUsage() {
    return assetUsage;
  }

  public void setAssetUsage(AssetsUsage assetUsage) {
    this.assetUsage = assetUsage;
  }

  @Column(length=30)
  public String getAssetSpecification() {
    return assetSpecification;
  }

  public void setAssetSpecification(String assetSpecification) {
    this.assetSpecification = assetSpecification;
  }

  @Column(length=20)
  public String getAssetOrigin() {
    return assetOrigin;
  }

  public void setAssetOrigin(String assetOrigin) {
    this.assetOrigin = assetOrigin;
  }

  @Column(length=50)
  public String getAssetManufacturer() {
    return assetManufacturer;
  }

  public void setAssetManufacturer(String assetManufacturer) {
    this.assetManufacturer = assetManufacturer;
  }

  @Column(length=50)
  public String getAssetProvider() {
    return assetProvider;
  }

  public void setAssetProvider(String assetProvider) {
    this.assetProvider = assetProvider;
  }

  public AssetsType getAssetsType() {
    return assetsType;
  }

  public void setAssetsType(AssetsType assetsType) {
    this.assetsType = assetsType;
  }

  public Date getAssetTime() {
    return assetTime;
  }

  public void setAssetTime(Date assetTime) {
    this.assetTime = assetTime;
  }

  @Column(precision = 12, scale = 2)
  public BigDecimal getAssetValue() {
    return assetValue;
  }

  public void setAssetValue(BigDecimal assetValue) {
    this.assetValue = assetValue;
  }

  @Column(length=60)
  public String getAssetName() {
    return assetName;
  }

  public void setAssetName(String assetName) {
    this.assetName = assetName;
  }

  @ManyToOne
  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  @Column(length=40)
  public String getAssetNo() {
    return assetNo;
  }

  public void setAssetNo(String assetNo) {
    this.assetNo = assetNo;
  }

  @Index(name="fixed_assets_tenantid")
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }
  
  @Column(length = 50)
  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

}
