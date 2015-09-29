package com.yly.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yly.entity.base.BaseEntity;

/**
 * 账单调账记录
 * 
 * @author sujinxuan
 *
 */
@Entity
@Table(name = "yly_billing_adjustment")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_billing_adjustment_sequence")
public class BillingAdjustment extends BaseEntity {

  
  private static final long serialVersionUID = -9092276707242009884L;
  
  /**
   * 调账金额
   */
  private BigDecimal adjustmentAmount;
  
  /**
   * 调账原因
   */
  private String adjustmentCause;
  
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
  public BigDecimal getAdjustmentAmount() {
    return adjustmentAmount;
  }

  public void setAdjustmentAmount(BigDecimal adjustmentAmount) {
    this.adjustmentAmount = adjustmentAmount;
  }

  public String getAdjustmentCause() {
    return adjustmentCause;
  }

  public void setAdjustmentCause(String adjustmentCause) {
    this.adjustmentCause = adjustmentCause;
  }
  
}
