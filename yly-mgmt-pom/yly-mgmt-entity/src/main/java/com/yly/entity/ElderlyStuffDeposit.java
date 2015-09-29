package com.yly.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yly.entity.base.BaseEntity;
import com.yly.entity.commonenum.CommonEnum.StuffDepositStatus;

/**
 * 老人物品寄存
 * 
 * @author shijun
 *
 */

@Entity
@Table(name = "yly_elderly_stuff_deposit")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_elderly_stuff_deposit_sequence")
public class ElderlyStuffDeposit extends BaseEntity {

  /**
   * 
   */
  private static final long serialVersionUID = -5884350766024263072L;
  
  /**
   * 寄存物品名称
   */
  private String name;
  
  /**
   * 数量
   */
  private Short count;
  
  /**
   * 编号
   */
  private String stuffNumber;
  
  /**
   * 备注
   */
  private String remark;
  
  /**
   * 物品寄存状态
   */
  private StuffDepositStatus stuffDepositStatus;
  
  /**
   * 寄存时间
   */
  private Date putinDate;
  
  /**
   * 取出时间
   */
  private Date takeAlwayDate;
  
  /**
   * 操作员/接收人
   */
  private String operator;
  
  /**
   * 物品所属老人
   */
  private ElderlyInfo elderlyInfo;

  @Column(length = 30)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Short getCount() {
    return count;
  }

  public void setCount(Short count) {
    this.count = count;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public StuffDepositStatus getStuffDepositStatus() {
    return stuffDepositStatus;
  }

  public void setStuffDepositStatus(StuffDepositStatus stuffDepositStatus) {
    this.stuffDepositStatus = stuffDepositStatus;
  }

  public Date getPutinDate() {
    return putinDate;
  }

  public void setPutinDate(Date putinDate) {
    this.putinDate = putinDate;
  }

  public Date getTakeAlwayDate() {
    return takeAlwayDate;
  }

  public void setTakeAlwayDate(Date takeAlwayDate) {
    this.takeAlwayDate = takeAlwayDate;
  }

  @Column(length = 15)
  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }

  public ElderlyInfo getElderlyInfo() {
    return elderlyInfo;
  }

  public void setElderlyInfo(ElderlyInfo elderlyInfo) {
    this.elderlyInfo = elderlyInfo;
  }

  @Column(length = 15)
public String getStuffNumber() {
	return stuffNumber;
}

public void setStuffNumber(String stuffNumber) {
	this.stuffNumber = stuffNumber;
}
  
  
}
