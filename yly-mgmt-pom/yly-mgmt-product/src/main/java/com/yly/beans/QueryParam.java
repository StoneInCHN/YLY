package com.yly.beans;

import java.io.Serializable;
import java.util.Date;

import com.yly.entity.commonenum.CommonEnum.BillingType;
import com.yly.entity.commonenum.CommonEnum.BudgetType;
import com.yly.entity.commonenum.CommonEnum.PaymentStatus;

public class QueryParam implements Serializable{
	
	private static final long serialVersionUID = 5233600129916917541L;
	/**
	 * 是否按租户查询
	 */
	private Boolean isTenant;
    /**
     * 开始时间
     */
	private Date beginDate;
	/**
	 * 结束时间
	 */
	private Date endDate;
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
    private Boolean isPeriod;
    /**
     * 缴费账单类型
     */
    private BillingType billingType;
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
