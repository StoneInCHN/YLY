package com.yly.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yly.entity.base.BaseEntity;

/**
 * 元数据配置类型
 * 
 * @author shijun
 *
 */
@Entity
@Table(name = "yly_config_type")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_config_type_sequence")
public class ConfigType extends BaseEntity {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * 配置项名称
   */
  private String name;

  /**
   * 对应处理器
   */
  private String processor;

  /**
   * 备注
   */
  private String remark;

  /**
   * 配置元集合
   */
  private Set<ConfigMeta> configMeta = new HashSet<ConfigMeta>();

  @Column(length = 20)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getProcessor() {
    return processor;
  }

  public void setProcessor(String processor) {
    this.processor = processor;
  }

  @Column(length = 20)
  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }
  
  @OneToMany(mappedBy = "configType", fetch = FetchType.LAZY)
  public Set<ConfigMeta> getConfigMeta() {
    return configMeta;
  }

  public void setConfigMeta(Set<ConfigMeta> configMeta) {
    this.configMeta = configMeta;
  }
}
