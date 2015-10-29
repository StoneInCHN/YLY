package com.yly.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yly.entity.base.BaseEntity;
import com.yly.entity.commonenum.CommonEnum.DonateType;


/**
 * 捐赠记录明细
 * @author sujinxuan
 *
 */
@Entity
@Table(name = "yly_donate_detail")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_donate_detail_sequence")
public class DonateDetail extends BaseEntity{
  
  
  private static final long serialVersionUID = -8389533913705258178L;
 
  /**
   * 捐赠类型（钱或物）
   */
  private DonateType donateType;

  /**
   * 捐赠数额
   */
  private BigDecimal donateAmount;
  
  /**
   * 捐赠物品类型
   */
  
  private DonateItemType donateItemType;
  /**
   * 物品计量单位
   */
  private String units;
  
  /**
   * 备注
   */
  private String remark;
  
  /**
   * 租户ID
   */
  private Long tenantID;
  
  /**
   * 捐赠记录
   */
  private DonateRecord donateRecord;
  
  @ManyToOne
  public DonateRecord getDonateRecord() {
    return donateRecord;
  }

  public void setDonateRecord(DonateRecord donateRecord) {
    this.donateRecord = donateRecord;
  }

  @JsonProperty
  @OneToOne
  public DonateItemType getDonateItemType() {
    return donateItemType;
  }

  public void setDonateItemType(DonateItemType donateItemType) {
    this.donateItemType = donateItemType;
  }

  @Column(length = 5)
  @JsonProperty
  public String getUnits() {
    return units;
  }

  public void setUnits(String units) {
    this.units = units;
  }

  @Index(name = "donate_detail_tenantid")
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }
  
  @JsonProperty
	public DonateType getDonateType() {
		return donateType;
	}

	public void setDonateType(DonateType donateType) {
		this.donateType = donateType;
	}

  @Column(precision = 12, scale = 2)
  @JsonProperty
  public BigDecimal getDonateAmount() {
    return donateAmount;
  }

  public void setDonateAmount(BigDecimal donateAmount) {
    this.donateAmount = donateAmount;
  }

  @Column(length=30)
  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }
  
}

