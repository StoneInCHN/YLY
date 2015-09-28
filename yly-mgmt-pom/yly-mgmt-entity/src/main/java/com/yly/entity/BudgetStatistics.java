package com.yly.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.yly.entity.base.BaseEntity;


/**
 * 
 *  收支统计
 * @author sujinxuan
 *
 */
@Entity
@Table(name = "yly_budget_statistics")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_budget_statistics_sequence")
public class BudgetStatistics extends BaseEntity{
   
  
  private static final long serialVersionUID = -6344873047838474649L;

  /**
   * 租户ID
   */
  private Long tenantID;
  
  /**
   * 收入
   */
  private BigDecimal incomes;
  
  /**
   * 支出
   */
  private BigDecimal outgoings;
  
  /**
   * 统计时间（按月统计）
   */
  private Date statisticsDate;
  
  @Column(nullable = false, precision = 12, scale = 2)
  public BigDecimal getIncomes() {
    return incomes;
  }

  public void setIncomes(BigDecimal incomes) {
    this.incomes = incomes;
  }

  @Column(nullable = false, precision = 12, scale = 2)
  public BigDecimal getOutgoings() {
    return outgoings;
  }

  public void setOutgoings(BigDecimal outgoings) {
    this.outgoings = outgoings;
  }

  public Date getStatisticsDate() {
    return statisticsDate;
  }

  public void setStatisticsDate(Date statisticsDate) {
    this.statisticsDate = statisticsDate;
  }
  
  @Index(name="budget_statistics_tenantid")
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }

  
}
