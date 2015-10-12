package com.yly.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yly.entity.base.BaseEntity;
import com.yly.entity.commonenum.CommonEnum.IdentifierType;

/**
 * 编号
 * 
 * @author shijun
 *
 */
@Entity
@Table(name = "yly_identifier")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_identifier_sequence")
public class Identifier extends BaseEntity {

  private static final long serialVersionUID = -2330598144835706164L;

  /** 类型 */
  private IdentifierType idType;

  /** 末值 */
  private Long lastValue;

  
  
  @Column(nullable = false, updatable = false, unique = true)
  public IdentifierType getIdType() {
    return idType;
  }

  public void setIdType(IdentifierType idType) {
    this.idType = idType;
  }

  /**
   * 获取末值
   * 
   * @return 末值
   */
  @Column(nullable = false)
  public Long getLastValue() {
      return lastValue;
  }

  /**
   * 设置末值
   * 
   * @param lastValue
   *            末值
   */
  public void setLastValue(Long lastValue) {
      this.lastValue = lastValue;
  }

}
