package com.yly.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Resolution;
import org.hibernate.search.annotations.Store;
import org.wltea.analyzer.lucene.IKAnalyzer;

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
@Table(name = "yly_meal_charge")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_meal_charge_sequence")
@Indexed(index="chargeRecord/mealCharge")
public class MealCharge extends BaseEntity {


  private static final long serialVersionUID = -770567905717752478L;

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
   * 伙食费金额
   */
  private BigDecimal mealAmount;

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

  @Field(store = Store.YES, index = org.hibernate.search.annotations.Index.UN_TOKENIZED, analyzer = @Analyzer(impl = IKAnalyzer.class))
  public PaymentStatus getChargeStatus() {
    return chargeStatus;
  }

  public void setChargeStatus(PaymentStatus chargeStatus) {
    this.chargeStatus = chargeStatus;
  }

  @Index(name = "meal_charge_tenantid")
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }

  @ManyToOne
  @IndexedEmbedded
  public ElderlyInfo getElderlyInfo() {
    return elderlyInfo;
  }

  public void setElderlyInfo(ElderlyInfo elderlyInfo) {
    this.elderlyInfo = elderlyInfo;
  }

  @Field(index = org.hibernate.search.annotations.Index.UN_TOKENIZED, store = Store.NO)
  @DateBridge(resolution = Resolution.DAY)
  public Date getPeriodStartDate() {
    return periodStartDate;
  }

  public void setPeriodStartDate(Date periodStartDate) {
    this.periodStartDate = periodStartDate;
  }

  @Field(index = org.hibernate.search.annotations.Index.UN_TOKENIZED, store = Store.NO)
  @DateBridge(resolution = Resolution.DAY)
  public Date getPeriodEndDate() {
    return periodEndDate;
  }

  public void setPeriodEndDate(Date periodEndDate) {
    this.periodEndDate = periodEndDate;
  }

  @Column(nullable = false, precision = 12, scale = 2)
  public BigDecimal getMealAmount() {
    return mealAmount;
  }

  public void setMealAmount(BigDecimal mealAmount) {
    this.mealAmount = mealAmount;
  }

}
