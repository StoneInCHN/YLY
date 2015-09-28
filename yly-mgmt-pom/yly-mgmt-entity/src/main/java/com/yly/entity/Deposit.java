package com.yly.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.yly.entity.base.BaseEntity;
import com.yly.entity.commonenum.CommonEnum.PaymentStatus;

/**
 * 押金
 * 
 * @author sujinxuan
 *
 */
@Entity
@Table(name = "yly_deposit")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_deposit_sequence")
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
  private String payType;
  
  /**
   * 发票号
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
  private PaymentStatus depositStatus;
  
  /**
   * 押金金额
   */
  private BigDecimal depositAmount;
  
  /**
   * 备注
   */
  private String remark;
  
  @Column(nullable = false, precision = 12, scale = 2)
  public BigDecimal getDepositAmount() {
    return depositAmount;
  }

  public void setDepositAmount(BigDecimal depositAmount) {
    this.depositAmount = depositAmount;
  }

  @Column(length=50)
  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  @Column(length=15)
  public String getPayType() {
    return payType;
  }

  public void setPayType(String payType) {
    this.payType = payType;
  }

  @Column(length=50)
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

  @Column(length=15)
  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }

  public PaymentStatus getDepositStatus() {
    return depositStatus;
  }

  public void setDepositStatus(PaymentStatus depositStatus) {
    this.depositStatus = depositStatus;
  }

  @Index(name="deposit_tenantid")
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }

  @OneToOne
  public ElderlyInfo getElderlyInfo() {
    return elderlyInfo;
  }

  public void setElderlyInfo(ElderlyInfo elderlyInfo) {
    this.elderlyInfo = elderlyInfo;
  }
  
  
}
