package com.yly.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Index;

import com.yly.entity.base.BaseEntity;
import com.yly.entity.commonenum.CommonEnum.PaymentStatus;
import com.yly.entity.commonenum.CommonEnum.PaymentType;

/**
 * 伙食费缴费费记录
 * 
 * @author sujinxuan
 *
 */
@Entity
@Table(name = "yly_water_electricity_charge")
@SequenceGenerator(name = "sequenceGenerator",
    sequenceName = "yly_water_electricity_charge_sequence")
public class WaterElectricityCharge extends BaseEntity {


  private static final long serialVersionUID = 261299867492190060L;

  /**
   * 租户ID
   */
  private Long tenantID;

  /**
   * 老人
   */
  private ElderlyInfo elderlyInfo;
  /**
   * 支付方式
   */
  private PaymentType paymentType;

  /**
   * 收据票号
   */
  private String invoiceNo;

  /**
   * 缴费时间
   */
  private Date payTime;

  /***
   * 收费开始时间
   */
  private Date periodStartDate;

  /**
   * 收费结束时间
   */
  private Date periodEndDate;

  /**
   * 周期内用水的吨数
   */
  private BigDecimal waterCount;

  /**
   * 周期内用电的度数
   */
  private BigDecimal electricityCount;

  /**
   * 用水费用
   */
  private BigDecimal waterAmount;

  /**
   * 用电费用
   */
  private BigDecimal electricityAmount;

  /**
   * 经办人
   */
  private String operator;
  /**
   * 缴纳状态
   */
  private PaymentStatus chargeStatus;
  /**
   * 备注
   */
  private String remark;

  /**
   * 账单号
   */
  private String billingNo;

  /**
   * 所属账单
   */
  private Billing billing;
  
  private BigDecimal totalAmount;
  
  @Transient
  public BigDecimal getTotalAmount() {
    totalAmount = waterAmount.add(electricityAmount);
    return totalAmount;
  }

  public void setTotalAmount(BigDecimal totalAmount) {
    this.totalAmount = totalAmount;
  }

  @OneToOne
  public Billing getBilling() {
    return billing;
  }

  public void setBilling(Billing billing) {
    this.billing = billing;
  }

  @Column(length = 30)
  public String getBillingNo() {
    return billingNo;
  }

  public void setBillingNo(String billingNo) {
    this.billingNo = billingNo;
  }

  @Column(length = 50)
  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public PaymentType getPaymentType() {
    return paymentType;
  }

  public void setPaymentType(PaymentType paymentType) {
    this.paymentType = paymentType;
  }

  @Column(length = 50)
  public String getInvoiceNo() {
    return invoiceNo;
  }

  public void setInvoiceNo(String invoiceNo) {
    this.invoiceNo = invoiceNo;
  }

  public Date getPayTime() {
    return payTime;
  }

  public void setPayTime(Date payTime) {
    this.payTime = payTime;
  }

  @Column(length = 15)
  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }

  public PaymentStatus getChargeStatus() {
    return chargeStatus;
  }

  public void setChargeStatus(PaymentStatus chargeStatus) {
    this.chargeStatus = chargeStatus;
  }

  @Index(name = "water_electricity_charge_tenantid")
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }

  @ManyToOne
  public ElderlyInfo getElderlyInfo() {
    return elderlyInfo;
  }

  public void setElderlyInfo(ElderlyInfo elderlyInfo) {
    this.elderlyInfo = elderlyInfo;
  }

  public Date getPeriodStartDate() {
    return periodStartDate;
  }

  public void setPeriodStartDate(Date periodStartDate) {
    this.periodStartDate = periodStartDate;
  }

  public Date getPeriodEndDate() {
    return periodEndDate;
  }

  public void setPeriodEndDate(Date periodEndDate) {
    this.periodEndDate = periodEndDate;
  }

  @Column(precision = 12, scale = 2)
  public BigDecimal getWaterCount() {
    return waterCount;
  }

  public void setWaterCount(BigDecimal waterCount) {
    this.waterCount = waterCount;
  }

  @Column(precision = 12, scale = 2)
  public BigDecimal getElectricityCount() {
    return electricityCount;
  }

  public void setElectricityCount(BigDecimal electricityCount) {
    this.electricityCount = electricityCount;
  }

  @Column(precision = 12, scale = 2)
  public BigDecimal getWaterAmount() {
    return waterAmount;
  }

  public void setWaterAmount(BigDecimal waterAmount) {
    this.waterAmount = waterAmount;
  }

  @Column(precision = 12, scale = 2)
  public BigDecimal getElectricityAmount() {
    return electricityAmount;
  }

  public void setElectricityAmount(BigDecimal electricityAmount) {
    this.electricityAmount = electricityAmount;
  }


}
