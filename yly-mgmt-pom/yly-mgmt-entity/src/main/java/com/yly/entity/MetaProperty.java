package com.yly.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yly.entity.base.BaseEntity;
import com.yly.entity.commonenum.CommonEnum.MetaDataType;

/**
 * 配置元属性
 * 
 * @author shijun
 *
 */
@Entity
@Table(name = "yly_meta_property")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_meta_property_sequence")
public class MetaProperty extends BaseEntity {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * 配置元属性名称
   */
  private String name;

  /**
   * 属性key
   */
  private String metaKey;

  /**
   * 属性内容
   */
  private String content;

  /**
   * 属性数据类型
   */
  private MetaDataType metaDataType;

  /**
   * 属性顺序
   */
  private Integer metaOrder;

  /**
   * 配置元
   */
  private ConfigMeta configMeta;

  @Column(length = 10)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Column(length = 30)
  public String getMetaKey ()
  {
    return metaKey;
  }

  public void setMetaKey (String metaKey)
  {
    this.metaKey = metaKey;
  }

  @Column(length = 50)
  public String getContent() {
    return content;
  }

  

  public void setContent(String content) {
    this.content = content;
  }

  public MetaDataType getMetaDataType() {
    return metaDataType;
  }

  public void setMetaDataType(MetaDataType metaDataType) {
    this.metaDataType = metaDataType;
  }

  public Integer getMetaOrder ()
  {
    return metaOrder;
  }

  public void setMetaOrder (Integer metaOrder)
  {
    this.metaOrder = metaOrder;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  public ConfigMeta getConfigMeta() {
    return configMeta;
  }

  public void setConfigMeta(ConfigMeta configMeta) {
    this.configMeta = configMeta;
  }
}
