package com.yly.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yly.entity.base.BaseEntity;

/**
 * 租户动态扩充字段值的存储
 * 
 * @author shijun
 *
 */
@Entity
@Table(name = "yly_tenant_extend_column")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_tenant_extend_column_sequence")
public class TenantExtendColumn extends BaseEntity {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * 原业务表ID
   */
  private Long dataID;

  /**
   * 租户配置属性
   */
  private TenantMetaProperty tenantMetaProperty;

  /**
   * 值
   */
  private String extendValue;

  public Long getDataID() {
    return dataID;
  }

  public void setDataID(Long dataID) {
    this.dataID = dataID;
  }
  
  @OneToOne
  public TenantMetaProperty getTenantMetaProperty() {
    return tenantMetaProperty;
  }

  public void setTenantMetaProperty(TenantMetaProperty tenantMetaProperty) {
    this.tenantMetaProperty = tenantMetaProperty;
  }

  @Column(length = 15)
  public String getExtendValue() {
    return extendValue;
  }

  public void setExtendValue(String extendValue) {
    this.extendValue = extendValue;
  }
}
