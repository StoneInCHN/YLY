package com.yly.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yly.entity.base.BaseEntity;

/**
 * 捐赠物品类型配置
 * 
 * @author sujinxuan
 *
 */
@Entity
@Table(name = "yly_donate_item_type")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_donate_item_type_sequence")
public class DonateItemType extends BaseEntity {


  private static final long serialVersionUID = -1684057707764082356L;

  /**
   * 租户ID
   */
  private Long tenantID;

  /**
   * 捐赠物品名称
   */
  private String itemName;
  /**
   * 排序
   */
  private Integer itemOrder;
  /**
   * 备注
   */
  private String remark;
 
  
  @Column(length = 20)
  @JsonProperty
  public String getItemName() {
    return itemName;
  }

  public void setItemName(String itemName) {
    this.itemName = itemName;
  }

  public Integer getItemOrder() {
    return itemOrder;
  }

  public void setItemOrder(Integer itemOrder) {
    this.itemOrder = itemOrder;
  }

  @Column(length = 30)
  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  @Index(name = "donate_item_type_tenantid")
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }

}
