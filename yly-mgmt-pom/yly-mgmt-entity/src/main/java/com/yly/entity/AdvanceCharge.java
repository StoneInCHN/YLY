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
import com.yly.entity.commonenum.CommonEnum.BudgetType;
import com.yly.entity.commonenum.CommonEnum.PaymentType;

/**
 * 预缴费管理
 * 
 * @author sujinxuan
 *
 */
@Entity
@Table(name = "yly_advance_charge")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_advance_charge_sequence")
public class AdvanceCharge extends BaseEntity {

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
   * 缴费金额
   */
  private BigDecimal advanceAmount;

  /**
   * 经办人
   */
  private String operator;

  /**
   * 收支类型
   */
  private BudgetType budgetType;

  /**
   * 备注
   */
  private String remark;

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

  @Index(name = "advance_charge_tenantid")
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

  @Column(nullable = false, precision = 12, scale = 2)
  public BigDecimal getAdvanceAmount() {
    return advanceAmount;
  }

  public void setAdvanceAmount(BigDecimal advanceAmount) {
    this.advanceAmount = advanceAmount;
  }

  public BudgetType getBudgetType() {
    return budgetType;
  }

  public void setBudgetType(BudgetType budgetType) {
    this.budgetType = budgetType;
  }

}
