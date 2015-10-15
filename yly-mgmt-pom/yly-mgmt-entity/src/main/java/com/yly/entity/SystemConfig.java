package com.yly.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yly.entity.base.BaseEntity;
import com.yly.entity.commonenum.CommonEnum.ConfigKey;

/**
 * 数据字典配置
 * 
 * @author sujinxuan
 *
 */
@Entity
@Table(name = "yly_system_config")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_system_config_sequence")
public class SystemConfig extends BaseEntity {


  private static final long serialVersionUID = -1684057707764082356L;

  /**
   * 租户ID
   */
  private Long tenantID;

  /**
   * 配置项
   */
  private ConfigKey configKey;

  /**
   * 配置项值
   */
  private String configValue;

  /**
   * 排序
   */
  private Integer configOrder;
  
  /**
   * 是否启用
   */
  private Boolean isEnabled;
  

  public Boolean getIsEnabled() {
    return isEnabled;
  }

  public void setIsEnabled(Boolean isEnabled) {
    this.isEnabled = isEnabled;
  }

  public Integer getConfigOrder() {
    return configOrder;
  }

  public void setConfigOrder(Integer configOrder) {
    this.configOrder = configOrder;
  }

  public ConfigKey getConfigKey() {
    return configKey;
  }

  public void setConfigKey(ConfigKey configKey) {
    this.configKey = configKey;
  }

  @JsonProperty
  @Column(length = 20)
  public String getConfigValue() {
    return configValue;
  }

  public void setConfigValue(String configValue) {
    this.configValue = configValue;
  }

  @Index(name = "system_config_tenantid")
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }

}
