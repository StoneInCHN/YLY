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

/**
 * 水电抄表记录
 * 
 * @author sujinxuan
 *
 */
@Entity
@Table(name = "yly_water_electricity_record")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_water_electricity_record_sequence")
public class WaterElectricityRecord extends BaseEntity{
  

  private static final long serialVersionUID = 261299867492190060L;

  /**
   * 租户ID
   */
  private Long tenantID;
  
  /**
   * 抄表地点
   */
  private Room room;
  
  /***
   * 抄表开始时间
   */
  private Date recordStartDate;
  
  /**
   * 抄表结束时间
   */
  private Date recordEndDate;
  
  /**
   * 周期内用水的吨数
   */
  private BigDecimal waterCount;
  /**
   * 用水减免吨数
   */
  private BigDecimal waterDerate;
  /**
   * 实际用水吨数
   */
  private BigDecimal waterActual;
  
  /**
   * 周期内用电的度数
   */
  private BigDecimal electricityCount;
  /**
   * 用电减免度数
   */
  private BigDecimal electricityDerate;
  /**
   * 实际用电度数
   */
  private BigDecimal electricityActual;
  
  /**
   * 抄表人
   */
  private String operator;

  /**
   * 备注
   */
  private String remark;
  
  @ManyToOne
  public Room getRoom() {
    return room;
  }

  public void setRoom(Room room) {
    this.room = room;
  }

  @Column(length=50)
  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  @Column(length=15)
  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }

  @Index(name="water_electricity_record_tenantid")
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }

  public Date getRecordStartDate() {
    return recordStartDate;
  }

  public void setRecordStartDate(Date recordStartDate) {
    this.recordStartDate = recordStartDate;
  }

  public Date getRecordEndDate() {
    return recordEndDate;
  }

  public void setRecordEndDate(Date recordEndDate) {
    this.recordEndDate = recordEndDate;
  }

  @Column(nullable = false, precision = 12, scale = 2)
  public BigDecimal getWaterCount() {
    return waterCount;
  }
  
  public void setWaterCount(BigDecimal waterCount) {
    this.waterCount = waterCount;
  }

  @Column(nullable = false, precision = 12, scale = 2)
  public BigDecimal getElectricityCount() {
    return electricityCount;
  }

  public void setElectricityCount(BigDecimal electricityCount) {
    this.electricityCount = electricityCount;
  }

  @Column(nullable = false, precision = 12, scale = 2)
  public BigDecimal getWaterDerate() {
    return waterDerate;
  }

  public void setWaterDerate(BigDecimal waterDerate) {
    this.waterDerate = waterDerate;
  }

  @Column(nullable = false, precision = 12, scale = 2)
  public BigDecimal getWaterActual() {
    return waterActual;
  }

  public void setWaterActual(BigDecimal waterActual) {
    this.waterActual = waterActual;
  }

  @Column(nullable = false, precision = 12, scale = 2)
  public BigDecimal getElectricityDerate() {
    return electricityDerate;
  }

  public void setElectricityDerate(BigDecimal electricityDerate) {
    this.electricityDerate = electricityDerate;
  }

  @Column(nullable = false, precision = 12, scale = 2)
  public BigDecimal getElectricityActual() {
    return electricityActual;
  }

  public void setElectricityActual(BigDecimal electricityActual) {
    this.electricityActual = electricityActual;
  }

}
