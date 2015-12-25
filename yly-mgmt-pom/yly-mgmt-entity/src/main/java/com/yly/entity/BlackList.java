package com.yly.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Resolution;
import org.hibernate.search.annotations.Store;
import org.wltea.analyzer.lucene.IKAnalyzer;

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
@Indexed(index = "blackList")
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
  
  private Date blackDate;
  
  @JsonProperty
  @Field(index = org.hibernate.search.annotations.Index.UN_TOKENIZED, store = Store.NO)
  @DateBridge(resolution = Resolution.DAY)
  public Date getBlackDate() {
    return blackDate;
  }

  public void setBlackDate(Date blackDate) {
    this.blackDate = blackDate;
  }

  @JsonProperty
  @ManyToOne(fetch = FetchType.LAZY)
  @IndexedEmbedded
  @JoinColumn(unique = true, nullable = false)
  public ElderlyInfo getElderlyInfo() {
    return elderlyInfo;
  }

  public void setElderlyInfo(ElderlyInfo elderlyInfo) {
    this.elderlyInfo = elderlyInfo;
  }

  @Field(store = Store.NO, index = org.hibernate.search.annotations.Index.UN_TOKENIZED, analyzer = @Analyzer(impl = IKAnalyzer.class))
  @Index(name = "elderly_evaluating_record_tenantid")
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }

  @JsonProperty
  @Column(length = 200, nullable = false)
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
