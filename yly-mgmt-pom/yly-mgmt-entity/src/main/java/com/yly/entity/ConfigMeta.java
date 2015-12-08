package com.yly.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yly.entity.base.BaseEntity;

/**
 * 配置元
 * 
 * @author shijun
 *
 */
@Entity
@Table(name = "yly_config_meta")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_config_meta_sequence")
public class ConfigMeta extends BaseEntity {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * 配置元名称
   */
  private String name;

  /**
   * 配置元 key
   */
  private String configKey;

  /**
   * 配置元内容
   */
  private String content;

  /**
   * 配置元类型
   */
  private ConfigType configType;

  /**
   * 配置元属性
   */
  private Set<MetaProperty> metaProperty = new HashSet<MetaProperty>();
  
  /**
   * 租户配置
   */
  private Set<TenantConfigInfo> tenantConfigInfo = new HashSet<TenantConfigInfo>();

  private Set<VersionConfig> versionConfig = new HashSet<VersionConfig> ();
  
  @Column(length = 10)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Column(length = 30)
  public String getConfigKey ()
  {
    return configKey;
  }

  public void setConfigKey (String configKey)
  {
    this.configKey = configKey;
  }

  public String getContent() {
    return content;
  }

  @Column(length = 50)
  public void setContent(String content) {
    this.content = content;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  public ConfigType getConfigType() {
    return configType;
  }

  public void setConfigType(ConfigType configType) {
    this.configType = configType;
  }

  @OneToMany(mappedBy = "configMeta", fetch = FetchType.LAZY)
  public Set<MetaProperty> getMetaProperty() {
    return metaProperty;
  }

  public void setMetaProperty(Set<MetaProperty> metaProperty) {
    this.metaProperty = metaProperty;
  }

  @OneToMany(mappedBy = "configMeta", fetch = FetchType.LAZY)
  public Set<TenantConfigInfo> getTenantConfigInfo() {
    return tenantConfigInfo;
  }

  public void setTenantConfigInfo(Set<TenantConfigInfo> tenantConfigInfo) {
    this.tenantConfigInfo = tenantConfigInfo;
  }
  
  @ManyToMany(mappedBy = "configMeta", fetch = FetchType.LAZY)
  public Set<VersionConfig> getVersionConfig ()
  {
    return versionConfig;
  }

  public void setVersionConfig (Set<VersionConfig> versionConfig)
  {
    this.versionConfig = versionConfig;
  }

}
