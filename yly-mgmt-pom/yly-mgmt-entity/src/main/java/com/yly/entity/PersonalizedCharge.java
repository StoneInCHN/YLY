package com.yly.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
 * 个性化服务费缴费费记录
 * 
 * @author sujinxuan
 *
 */
@Entity
@Table(name = "yly_personalized_charge")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_personalized_charge_sequence")
@Indexed(index = "chargeRecord/personalizedCharge")
public class PersonalizedCharge extends BaseEntity {

  private static final long serialVersionUID = 8840662978230104889L;

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
   * 服务费金额
   */
  private BigDecimal personalizedAmount;

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
   * 个性化服务记录
   */
  private Set<PersonalizedRecord> personalizedRecords = new HashSet<PersonalizedRecord>();

  /**
   * 所属账单
   */
  private Billing billing;

  /***
   * 收费开始时间
   */
  private Date periodStartDate;

  /**
   * 收费结束时间
   */
  private Date periodEndDate;

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

  @JsonProperty
  @Field(index = org.hibernate.search.annotations.Index.UN_TOKENIZED, store = Store.NO)
  @FieldBridge(impl = DateBridgeImpl.class)
  public Date getPeriodStartDate() {
    return periodStartDate;
  }

  public void setPeriodStartDate(Date periodStartDate) {
    this.periodStartDate = periodStartDate;
  }

  @JsonProperty
  @Field(index = org.hibernate.search.annotations.Index.UN_TOKENIZED, store = Store.NO)
  @FieldBridge(impl = DateBridgeImpl.class)
  public Date getPeriodEndDate() {
    return periodEndDate;
  }

  public void setPeriodEndDate(Date periodEndDate) {
    this.periodEndDate = periodEndDate;
  }

  public PaymentType getPaymentType() {
    return paymentType;
  }

  public void setPaymentType(PaymentType paymentType) {
    this.paymentType = paymentType;
  }

  @OneToOne
  public Billing getBilling() {
    return billing;
  }

  public void setBilling(Billing billing) {
    this.billing = billing;
  }

  @OneToMany(mappedBy = "personalizedCharge", cascade = CascadeType.MERGE)
  public Set<PersonalizedRecord> getPersonalizedRecords() {
    return personalizedRecords;
  }

  public void setPersonalizedRecords(Set<PersonalizedRecord> personalizedRecords) {
    this.personalizedRecords = personalizedRecords;
  }

  @Column(length = 30)
  public String getBillingNo() {
    return billingNo;
  }

  public void setBillingNo(String billingNo) {
    this.billingNo = billingNo;
  }

  @JsonProperty
  @Column(length = 2000)
  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  @Column(length = 30)
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

  @JsonProperty
  @Field(store = Store.NO, index = org.hibernate.search.annotations.Index.UN_TOKENIZED,
      analyzer = @Analyzer(impl = IKAnalyzer.class))
  public PaymentStatus getChargeStatus() {
    return chargeStatus;
  }

  public void setChargeStatus(PaymentStatus chargeStatus) {
    this.chargeStatus = chargeStatus;
  }

  @Index(name = "personalized_charge_tenantid")
  @Field(store = Store.NO, index = org.hibernate.search.annotations.Index.UN_TOKENIZED,
      analyzer = @Analyzer(impl = IKAnalyzer.class))
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

  @JsonProperty
  @Column(nullable = false, precision = 12, scale = 2)
  public BigDecimal getPersonalizedAmount() {
    return personalizedAmount;
  }

  public void setPersonalizedAmount(BigDecimal personalizedAmount) {
    this.personalizedAmount = personalizedAmount;
  }

}
