package com.yly.json.request;

import java.io.Serializable;
import java.util.Date;

import com.yly.entity.BillingAdjustment;
import com.yly.entity.commonenum.CommonEnum.BillingType;
import com.yly.entity.commonenum.CommonEnum.BudgetType;
import com.yly.entity.commonenum.CommonEnum.PaymentStatus;

/**
 * 缴费记录查询条件
 * @author sujinxuan
 *
 */
public class ChargeSearchRequest implements Serializable{
	
	private static final long serialVersionUID = 5233600129916917541L;
	/**
	 * 是否按租户查询
	 */
	private Boolean isTenant=true;
    /**
     * 开始时间
     */
	private Date beginDate;
	/**
	 * 结束时间
	 */
	private Date endDate;
	/**
	 * 老人id
	 */
	private Long elderlyId;
	
    /**
	 * 老人姓名
	 */
    private String realName;
    /**
     * 老人编号
     */
    private String identifier;
    /**
     * 支付状态
     */
    private PaymentStatus status;
    
    /**
     * 收支类型
     */
    private BudgetType budgetType;
    /**
     * 是否查询时间段
     */
    private Boolean isPeriod=true;
    /**
     * 是否按查询创建时间
     */
    private Boolean isCreateTime=false;
    /**
     * 缴费账单类型
     */
    private BillingType billingType;
    /**
     * 是否立即办理出院
     */
    private Boolean checkoutNow;
    /**
     * 办理出院时间
     */
    private Date checkoutDate;
    /**
     * 是否使用预存款
     */
    private Boolean isAdvanceCharge;
    /**
     * 调账
     */
    private BillingAdjustment billingAdjustment;
    
	public BillingAdjustment getBillingAdjustment() {
      return billingAdjustment;
    }
	
    public void setBillingAdjustment(BillingAdjustment billingAdjustment) {
      this.billingAdjustment = billingAdjustment;
    }
  public Boolean getIsAdvanceCharge() {
      return isAdvanceCharge;
    }
    public void setIsAdvanceCharge(Boolean isAdvanceCharge) {
      this.isAdvanceCharge = isAdvanceCharge;
    }
    public Boolean getCheckoutNow() {
      return checkoutNow;
    }
    public void setCheckoutNow(Boolean checkoutNow) {
      this.checkoutNow = checkoutNow;
    }
    public Date getCheckoutDate() {
      return checkoutDate;
    }
    public void setCheckoutDate(Date checkoutDate) {
      this.checkoutDate = checkoutDate;
    }
    public Boolean getIsCreateTime() {
      return isCreateTime;
    }
    public void setIsCreateTime(Boolean isCreateTime) {
      this.isCreateTime = isCreateTime;
    }
    public Boolean getIsTenant() {
		return isTenant;
	}
	public void setIsTenant(Boolean isTenant) {
		this.isTenant = isTenant;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
    public Long getElderlyId() {
      return elderlyId;
    }
    public void setElderlyId(Long elderlyId) {
      this.elderlyId = elderlyId;
    }	
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public PaymentStatus getStatus() {
		return status;
	}
	public void setStatus(PaymentStatus status) {
		this.status = status;
	}
	public BudgetType getBudgetType() {
		return budgetType;
	}
	public void setBudgetType(BudgetType budgetType) {
		this.budgetType = budgetType;
	}
	public Boolean getIsPeriod() {
		return isPeriod;
	}
	public void setIsPeriod(Boolean isPeriod) {
		this.isPeriod = isPeriod;
	}
	public BillingType getBillingType() {
		return billingType;
	}
	public void setBillingType(BillingType billingType) {
		this.billingType = billingType;
	}
    
    

}
