package com.yly.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yly.entity.base.BaseEntity;
import com.yly.entity.commonenum.CommonEnum.PaymentType;

/**
 * 账单支付方式记录
 * 
 * @author sujinxuan
 *
 */
@Entity
@Table(name = "yly_payment_record")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_payment_record_sequence")
public class PaymentRecord extends BaseEntity {

  
  private static final long serialVersionUID = -9092276707242009884L;
  
  /**
   * 支付方式
   */
  private PaymentType paymentType;

  /**
   * 付款金额
   */
  private BigDecimal payAmount;
  
  /**
   * 备注
   */
  private String remark;
  
  /**
   * 账单
   */
  private Billing billing;
  
  
  @ManyToOne
  public Billing getBilling() {
    return billing;
  }

  public void setBilling(Billing billing) {
    this.billing = billing;
  }

  @Column(nullable = false, precision = 12, scale = 2)
  public BigDecimal getPayAmount() {
    return payAmount;
  }

  public void setPayAmount(BigDecimal payAmount) {
    this.payAmount = payAmount;
  }

  public PaymentType getPaymentType() {
    return paymentType;
  }

  public void setPaymentType(PaymentType paymentType) {
    this.paymentType = paymentType;
  }

  @Column(length = 50)
  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }
  
}
