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
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.yly.entity.base.BaseEntity;
import com.yly.entity.commonenum.CommonEnum.BillingType;
import com.yly.entity.commonenum.CommonEnum.PaymentStatus;
import com.yly.entity.commonenum.CommonEnum.PaymentType;
import com.yly.lucene.DateBridgeImpl;

/**
 * 日常缴费账单补充
 * 
 * @author sujinxuan
 *
 */
@Entity
@Table(name = "yly_billing_supplyment")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_billing_supplyment_sequence")
public class BillingSupplyment extends BaseEntity {

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
   * 账单号
   */
  private String billingNo;

  /**
   * 缴费时间
   */
  private Date payTime;
  /**
   * 收款人
   */
  private String payStaff;

  /***
   * 收费开始时间
   */
  private Date periodStartDate;

  /**
   * 收费结束时间
   */
  private Date periodEndDate;

  /**
   * 床位费金额
   */
  private BigDecimal bedAmount = new BigDecimal(0);
  /**
   * 水费金额
   */
  private BigDecimal waterAmount = new BigDecimal(0);
  /**
   * 电费费金额
   */
  private BigDecimal electricityAmount = new BigDecimal(0);
  /**
   * 个性化服务费金额
   */
  private BigDecimal personalizedAmount = new BigDecimal(0);

  /**
   * 护理费金额
   */
  private BigDecimal nurseAmount = new BigDecimal(0);

  /**
   * 押金金额
   */
  private BigDecimal depositAmount = new BigDecimal(0);

  /**
   * 伙食费金额
   */
  private BigDecimal mealAmount = new BigDecimal(0);
  
  /**
   * 总金额
   */
  private BigDecimal totalAmount = new BigDecimal(0);

  /**
   * 押金详情
   */
  private Deposit deposit;
  /**
   * 床位护理费详情
   */
  private BedNurseCharge bedNurseCharge;
  /**
   * 伙食费详情
   */
  private MealCharge mealCharge;
  /**
   * 水电费详情
   */
  private WaterElectricityCharge waterElectricityCharge;
  /**
   * 个性化服务费详情
   */
  private PersonalizedCharge personalizedCharge;
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
   * 支付记录
   */
  private Set<PaymentRecord> paymentRecords = new HashSet<PaymentRecord>();

  /**
   * 账单类型（入住缴费，退住结算，日常缴费的补充账单）
   */
  private BillingType billType;
  
  /**
   * 原始账单
   */
  private Billing billing;
  
  @OneToOne
  public Billing getBilling() {
    return billing;
  }

  public void setBilling(Billing billing) {
    this.billing = billing;
  }

  @Column(precision = 12, scale = 2)
  public BigDecimal getMealAmount() {
    return mealAmount;
  }

  public void setMealAmount(BigDecimal mealAmount) {
    this.mealAmount = mealAmount;
  }

  @Field(store = Store.NO, index = org.hibernate.search.annotations.Index.UN_TOKENIZED,
      analyzer = @Analyzer(impl = IKAnalyzer.class))
  public BillingType getBillType() {
    return billType;
  }

  public void setBillType(BillingType billType) {
    this.billType = billType;
  }

  @OneToMany(mappedBy = "billing",cascade=CascadeType.ALL)
  public Set<PaymentRecord> getPaymentRecords() {
    return paymentRecords;
  }

  public void setPaymentRecords(Set<PaymentRecord> paymentRecords) {
    this.paymentRecords = paymentRecords;
  }

  public PaymentType getPaymentType() {
    return paymentType;
  }

  public void setPaymentType(PaymentType paymentType) {
    this.paymentType = paymentType;
  }

  @OneToOne(mappedBy = "billingSupply",cascade=CascadeType.ALL)
  public BedNurseCharge getBedNurseCharge() {
    return bedNurseCharge;
  }

  public void setBedNurseCharge(BedNurseCharge bedNurseCharge) {
    this.bedNurseCharge = bedNurseCharge;
  }

  @OneToOne(mappedBy = "billingSupply",cascade=CascadeType.ALL)
  public MealCharge getMealCharge() {
    return mealCharge;
  }

  public void setMealCharge(MealCharge mealCharge) {
    this.mealCharge = mealCharge;
  }

  @OneToOne(mappedBy = "billingSupply")
  public WaterElectricityCharge getWaterElectricityCharge() {
    return waterElectricityCharge;
  }

  public void setWaterElectricityCharge(WaterElectricityCharge waterElectricityCharge) {
    this.waterElectricityCharge = waterElectricityCharge;
  }

  @OneToOne(mappedBy = "billingSupply")
  public PersonalizedCharge getPersonalizedCharge() {
    return personalizedCharge;
  }

  public void setPersonalizedCharge(PersonalizedCharge personalizedCharge) {
    this.personalizedCharge = personalizedCharge;
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

  @Column(length = 30)
  public String getInvoiceNo() {
    return invoiceNo;
  }

  public void setInvoiceNo(String invoiceNo) {
    this.invoiceNo = invoiceNo;
  }

  @Field(index = org.hibernate.search.annotations.Index.UN_TOKENIZED, store = Store.NO)
  @FieldBridge(impl = DateBridgeImpl.class)
  public Date getPayTime() {
    return payTime;
  }

  public void setPayTime(Date payTime) {
    this.payTime = payTime;
  }

  @Column(length = 15)
  public String getPayStaff() {
    return payStaff;
  }

  public void setPayStaff(String payStaff) {
    this.payStaff = payStaff;
  }

  @Column(length = 15)
  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }

  @Field(store = Store.NO, index = org.hibernate.search.annotations.Index.UN_TOKENIZED, analyzer = @Analyzer(impl = IKAnalyzer.class))
  public PaymentStatus getChargeStatus() {
    return chargeStatus;
  }

  public void setChargeStatus(PaymentStatus chargeStatus) {
    this.chargeStatus = chargeStatus;
  }

  @Index(name = "billing_supplyment_tenantid")
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

  @Field(index = org.hibernate.search.annotations.Index.UN_TOKENIZED, store = Store.NO)
  @FieldBridge(impl = DateBridgeImpl.class)
  public Date getPeriodStartDate() {
    return periodStartDate;
  }

  public void setPeriodStartDate(Date periodStartDate) {
    this.periodStartDate = periodStartDate;
  }

  @Field(index = org.hibernate.search.annotations.Index.UN_TOKENIZED, store = Store.NO)
  @FieldBridge(impl = DateBridgeImpl.class)
  public Date getPeriodEndDate() {
    return periodEndDate;
  }

  public void setPeriodEndDate(Date periodEndDate) {
    this.periodEndDate = periodEndDate;
  }

  @Column(precision = 12, scale = 2)
  public BigDecimal getBedAmount() {
    return bedAmount;
  }

  public void setBedAmount(BigDecimal bedAmount) {
    this.bedAmount = bedAmount;
  }

  @Column(precision = 12, scale = 2)
  public BigDecimal getNurseAmount() {
    return nurseAmount;
  }

  public void setNurseAmount(BigDecimal nurseAmount) {
    this.nurseAmount = nurseAmount;
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

  @Column(precision = 12, scale = 2)
  public BigDecimal getPersonalizedAmount() {
    return personalizedAmount;
  }

  public void setPersonalizedAmount(BigDecimal personalizedAmount) {
    this.personalizedAmount = personalizedAmount;
  }

  @Column(precision = 12, scale = 2)
  public BigDecimal getDepositAmount() {
    return depositAmount;
  }

  public void setDepositAmount(BigDecimal depositAmount) {
    this.depositAmount = depositAmount;
  }

  @OneToOne(mappedBy = "billingSupply", cascade = CascadeType.ALL)
  public Deposit getDeposit() {
    return deposit;
  }

  public void setDeposit(Deposit deposit) {
    this.deposit = deposit;
  }

  @Column(precision = 12, scale = 2)
  public BigDecimal getTotalAmount() {
    return totalAmount;
  }

  public void setTotalAmount(BigDecimal totalAmount) {
    this.totalAmount = totalAmount;
  }

}
