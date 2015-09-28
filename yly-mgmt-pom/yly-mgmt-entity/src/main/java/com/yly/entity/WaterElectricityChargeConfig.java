package com.yly.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.yly.entity.base.BaseEntity;

/**
 * 水电收费标准配置
 * 
 * @author sujinxuan
 *
 */
@Entity
@Table(name = "yly_water_electricity_charge_config")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_water_electricity_charge_config_sequence")
public class WaterElectricityChargeConfig extends BaseEntity{
  
  private static final long serialVersionUID = 3063476633816990904L;

  /**
   * 租户ID
   */
  private Long tenantID;
  
  /**
   * 水单价（元/吨）
   */
  private BigDecimal waterUnitPrice;
  
  /**
   * 电单价（元/度）
   */
  private BigDecimal electricityUnitPrice;
  
  @Index(name="water_electricity_charge_config_tenantid")
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }

  @Column(nullable = false, precision = 12, scale = 2)
  public BigDecimal getWaterUnitPrice() {
    return waterUnitPrice;
  }

  public void setWaterUnitPrice(BigDecimal waterUnitPrice) {
    this.waterUnitPrice = waterUnitPrice;
  }

  @Column(nullable = false, precision = 12, scale = 2)
  public BigDecimal getElectricityUnitPrice() {
    return electricityUnitPrice;
  }

  public void setElectricityUnitPrice(BigDecimal electricityUnitPrice) {
    this.electricityUnitPrice = electricityUnitPrice;
  }

}
