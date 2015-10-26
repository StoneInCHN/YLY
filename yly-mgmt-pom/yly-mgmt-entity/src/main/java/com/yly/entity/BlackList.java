package com.yly.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yly.entity.base.BaseEntity;

/**
 * 黑名单
 * 
 * @author sujinxuan
 *
 */
@Entity
@Table(name = "yly_blacklist")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_blacklist_sequence")
@Indexed(index="blackList")
public class BlackList extends BaseEntity {

  
  private static final long serialVersionUID = 3525183712762376969L;

  /**
   * 老人信息
   */
  private ElderlyInfo elderlyInfo;
  
  /**
   * 加入黑名单原因
   */
  private String cause;
  
  /**
   * 备注
   */
  private String remark;
  
  
  /**
   * 租户ID
   */
  private Long tenantID;
  
  @JsonProperty
  @ManyToOne(fetch = FetchType.LAZY)
  @IndexedEmbedded
  public ElderlyInfo getElderlyInfo() {
    return elderlyInfo;
  }

  public void setElderlyInfo(ElderlyInfo elderlyInfo) {
    this.elderlyInfo = elderlyInfo;
  }

  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }

  @JsonProperty
  @Column(length = 200)
  public String getCause() {
    return cause;
  }

  public void setCause(String cause) {
    this.cause = cause;
  }

  @Column(length = 50)
  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

}
