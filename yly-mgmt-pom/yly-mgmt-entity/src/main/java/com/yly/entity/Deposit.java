package com.yly.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yly.entity.base.BaseEntity;
import com.yly.entity.commonenum.CommonEnum.PaymentStatus;
import com.yly.entity.commonenum.CommonEnum.PaymentType;
import com.yly.lucene.DateBridgeImpl;

/**
 * 押金
 * 
 * @author sujinxuan
 *
 */
@Entity
@Table(name = "yly_deposit")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_deposit_sequence")
@Indexed(index="chargeManage/deposit")
public class Deposit extends BaseEntity{
  
  private static final long serialVersionUID = -9092276707242009884L;
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

  /**
   * 经办人
   */
  private String operator;
  /**
   * 押金缴纳状态
   */
  private PaymentStatus chargeStatus;
  
  /**
   * 押金金额
   */
  private BigDecimal depositAmount;
  
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
  
  /**
   * 所属补充账单
   */
  private BillingSupplyment billingSupply;
  
  
  @OneToOne
  public BillingSupplyment getBillingSupply() {
    return billingSupply;
  }

  public void setBillingSupply(BillingSupplyment billingSupply) {
    this.billingSupply = billingSupply;
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
  @JsonProperty
  @Column(nullable = false, precision = 12, scale = 2)
  public BigDecimal getDepositAmount() {
    return depositAmount;
  }

  public void setDepositAmount(BigDecimal depositAmount) {
    this.depositAmount = depositAmount;
  }
  @JsonProperty
  @Column(length=2000)
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

  @Column(length=30)
  public String getInvoiceNo() {
    return invoiceNo;
  }

  public void setInvoiceNo(String invoiceNo) {
    this.invoiceNo = invoiceNo;
  }
  @JsonProperty
  @Field(index = org.hibernate.search.annotations.Index.UN_TOKENIZED, store = Store.NO)
  @FieldBridge(impl = DateBridgeImpl.class)
  public Date getPayTime() {
    return payTime;
  }

  public void setPayTime(Date payTime) {
    this.payTime = payTime;
  }

  @Column(length=15)
  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }
  @JsonProperty
  @Field(store = Store.NO, index = org.hibernate.search.annotations.Index.UN_TOKENIZED, analyzer = @Analyzer(impl = IKAnalyzer.class))
  public PaymentStatus getChargeStatus() {
    return chargeStatus;
  }

  public void setChargeStatus(PaymentStatus chargeStatus) {
    this.chargeStatus = chargeStatus;
  }

  @Index(name="deposit_tenantid")
  @Field(store = Store.NO, index = org.hibernate.search.annotations.Index.UN_TOKENIZED, analyzer = @Analyzer(impl = IKAnalyzer.class))
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }

  @OneToOne
  @IndexedEmbedded
  public ElderlyInfo getElderlyInfo() {
    return elderlyInfo;
  }

  public void setElderlyInfo(ElderlyInfo elderlyInfo) {
    this.elderlyInfo = elderlyInfo;
  }
  
  
}
