package com.yly.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.yly.entity.base.BaseEntity;

/**
 * 租户配置元信息,用于记录租户对现有配置元的扩展
 * 
 * @author shijun
 *
 */
@Entity
@Table(name = "yly_tenant_config_info")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_tenant_config_info_sequence")
public class TenantConfigInfo extends BaseEntity {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * 租户ID
   */
  private Long tenantID;

  /**
   * 租户自定义名字
   */
  private String name;

  /**
   * 新增字段名称或者其他描述的值,并非具体的值
   */
  private String value;
  
  /**
   * 版本
   */
  private VersionConfig versionConfig;
  
  private Set<TenantMetaProperty> tenantMetaProperty = new HashSet<TenantMetaProperty>();

  @Index(name="tenant_config_info_tenantid")
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }

  @Column(length = 10)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Column(length = 15)
  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  @ManyToOne
  public VersionConfig getVersionConfig ()
  {
    return versionConfig;
  }

  public void setVersionConfig (VersionConfig versionConfig)
  {
    this.versionConfig = versionConfig;
  }

  @OneToMany(mappedBy = "tenantConfigInfo", fetch = FetchType.LAZY)
  public Set<TenantMetaProperty> getTenantMetaProperty() {
    return tenantMetaProperty;
  }

  public void setTenantMetaProperty(Set<TenantMetaProperty> tenantMetaProperty) {
    this.tenantMetaProperty = tenantMetaProperty;
  }
}
