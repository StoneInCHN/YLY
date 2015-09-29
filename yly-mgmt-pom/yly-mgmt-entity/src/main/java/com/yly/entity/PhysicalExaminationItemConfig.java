package com.yly.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.yly.entity.base.BaseEntity;
import com.yly.entity.commonenum.CommonEnum.ConfigKey;

/**
 * 体检项字典配置
 * 
 * @author sujinxuan
 *
 */
@Entity
@Table(name = "yly_physical_examinationitem_config")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_physical_examinationitem_config_sequence")
public class PhysicalExaminationItemConfig extends BaseEntity {

  private static final long serialVersionUID = 3063476633816990904L;

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

  @Column(length = 20)
  public String getConfigValue() {
    return configValue;
  }

  public void setConfigValue(String configValue) {
    this.configValue = configValue;
  }

  @Index(name = "physical_examination_item_config_tenantid")
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }

}
