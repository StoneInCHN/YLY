package com.yly.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.yly.entity.base.BaseEntity;

/**
 * 护理收费标准配置
 * 
 * @author sujinxuan
 *
 */
@Entity
@Table(name = "yly_nurse_charge_config")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_nurse_charge_config_sequence")
public class NurseChargeConfig extends BaseEntity{
  
  private static final long serialVersionUID = 3063476633816990904L;

  /**
   * 租户ID
   */
  private Long tenantID;
   
  /**
   * 项目等级
   */
  private SystemConfig chargeItem;
  
  /**
   * 金额/天
   */
  private BigDecimal amountPerDay;
  
  /**
   * 金额/月
   */
  private BigDecimal amountPerMonth;
  
  @OneToOne
  public SystemConfig getChargeItem() {
    return chargeItem;
  }

  public void setChargeItem(SystemConfig chargeItem) {
    this.chargeItem = chargeItem;
  }

  @Index(name="nurse_charge_config_tenantid")
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }

  @Column(nullable = false, precision = 12, scale = 2)
  public BigDecimal getAmountPerDay() {
    return amountPerDay;
  }

  public void setAmountPerDay(BigDecimal amountPerDay) {
    this.amountPerDay = amountPerDay;
  }

  @Column(nullable = false, precision = 12, scale = 2)
  public BigDecimal getAmountPerMonth() {
    return amountPerMonth;
  }

  public void setAmountPerMonth(BigDecimal amountPerMonth) {
    this.amountPerMonth = amountPerMonth;
  }

}
