package com.yly.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
import com.yly.entity.commonenum.CommonEnum.StuffDepositStatus;

/**
 * 老人物品寄存
 * 
 * @author shijun
 *
 */

@Entity
@Table(name = "yly_elderly_stuff_deposit")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_elderly_stuff_deposit_sequence")
@Indexed(index = "elderlyStuffDeposit/elderlyStuffDeposit")
public class ElderlyStuffDeposit extends BaseEntity {

  /**
   * 
   */
  private static final long serialVersionUID = -5884350766024263072L;

  /**
   * 寄存物品名称
   */
  private String name;

  /**
   * 数量
   */
  private Short count;

  /**
   * 编号
   */
  private String stuffNumber;

  /**
   * 备注
   */
  private String remark;

  /**
   * 物品寄存状态
   */
  private StuffDepositStatus stuffDepositStatus;

  /**
   * 寄存时间
   */
  private Date putinDate;

  /**
   * 取出时间
   */
  private Date takeAlwayDate;

  /**
   * 操作员/接收人
   */
  private String operator;

  /**
   * 物品所属老人
   */
  private ElderlyInfo elderlyInfo;

  @JsonProperty
  @Column(length = 30)
  @Field(store = Store.YES, index = org.hibernate.search.annotations.Index.TOKENIZED,
  analyzer = @Analyzer(impl = IKAnalyzer.class))
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @JsonProperty
  public Short getCount() {
    return count;
  }

  public void setCount(Short count) {
    this.count = count;
  }

  @JsonProperty
  @Column(length = 150)
  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  @JsonProperty
  public StuffDepositStatus getStuffDepositStatus() {
    return stuffDepositStatus;
  }

  public void setStuffDepositStatus(StuffDepositStatus stuffDepositStatus) {
    this.stuffDepositStatus = stuffDepositStatus;
  }

  @JsonProperty
  @Field(index = org.hibernate.search.annotations.Index.UN_TOKENIZED, store = Store.NO)
  @DateBridge(resolution = Resolution.DAY)
  public Date getPutinDate() {
    return putinDate;
  }

  public void setPutinDate(Date putinDate) {
    this.putinDate = putinDate;
  }

  @JsonProperty
  @Field(index = org.hibernate.search.annotations.Index.UN_TOKENIZED, store = Store.NO)
  @DateBridge(resolution = Resolution.DAY)
  public Date getTakeAlwayDate() {
    return takeAlwayDate;
  }

  public void setTakeAlwayDate(Date takeAlwayDate) {
    this.takeAlwayDate = takeAlwayDate;
  }

  @JsonProperty
  @Column(length = 15)
  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }

  @JsonProperty
  @IndexedEmbedded
  @ManyToOne(fetch = FetchType.LAZY)
  public ElderlyInfo getElderlyInfo() {
    return elderlyInfo;
  }

  public void setElderlyInfo(ElderlyInfo elderlyInfo) {
    this.elderlyInfo = elderlyInfo;
  }

  @JsonProperty
  @Column(length = 15)
  @Field(store = Store.YES, index = org.hibernate.search.annotations.Index.TOKENIZED,
      analyzer = @Analyzer(impl = IKAnalyzer.class))
  public String getStuffNumber() {
    return stuffNumber;
  }

  public void setStuffNumber(String stuffNumber) {
    this.stuffNumber = stuffNumber;
  }


}
