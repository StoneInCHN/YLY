package com.yly.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.yly.entity.base.BaseEntity;

/**
 * 个性化服务收费标准配置
 * 
 * @author sujinxuan
 *
 */
@Entity
@Table(name = "yly_personalized_charge_config")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_personalized_charge_config_sequence")
public class PersonalizedChargeConfig extends BaseEntity{
  
  private static final long serialVersionUID = 3063476633816990904L;

  /**
   * 租户ID
   */
  private Long tenantID;
   
  /**
   * 收费项目
   */
  private String changeItem;
  
  /**
   * 金额/次
   */
  private BigDecimal amountPerTime;

  @Index(name="personalized_charge_config_tenantid")
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }

  @Column(length = 15)
  public String getChangeItem() {
    return changeItem;
  }

  public void setChangeItem(String changeItem) {
    this.changeItem = changeItem;
  }

  @Column(nullable = false, precision = 12, scale = 2)
  public BigDecimal getAmountPerTime() {
    return amountPerTime;
  }

  public void setAmountPerTime(BigDecimal amountPerTime) {
    this.amountPerTime = amountPerTime;
  }


}
