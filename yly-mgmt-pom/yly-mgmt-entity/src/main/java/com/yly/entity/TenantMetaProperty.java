package com.yly.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yly.entity.base.BaseEntity;

@Entity
@Table(name = "yly_tenant_meta_property")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_tenant_meta_property_sequence")
public class TenantMetaProperty extends BaseEntity {


  private static final long serialVersionUID = 1L;

  /**
   * 租户配置
   */
  private TenantConfigInfo tenantConfigInfo;

  /**
   * 描述当前行存储的数据用途,可以是数据类型,也可是元数据里面的参数值
   */
  private String key;

  /**
   * key对应的值
   */
  private String value;

  /**
   * 扩充列
   */
  private TenantExtendColumn tenantExtendColumn;

  @ManyToOne(fetch = FetchType.LAZY)
  public TenantConfigInfo getTenantConfigInfo() {
    return tenantConfigInfo;
  }

  public void setTenantConfigInfo(TenantConfigInfo tenantConfigInfo) {
    this.tenantConfigInfo = tenantConfigInfo;
  }

  @Column(length = 30)
  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  @Column(length = 15)
  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  @OneToOne(mappedBy = "tenantMetaProperty")
  public TenantExtendColumn getTenantExtendColumn() {
    return tenantExtendColumn;
  }

  public void setTenantExtendColumn(TenantExtendColumn tenantExtendColumn) {
    this.tenantExtendColumn = tenantExtendColumn;
  }
}
