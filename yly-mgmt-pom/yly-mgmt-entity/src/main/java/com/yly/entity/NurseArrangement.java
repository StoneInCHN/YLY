package com.yly.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Index;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yly.entity.base.BaseEntity;
import com.yly.lucene.DateBridgeImpl;

/**
 * 护理员安排
 * @author luzhang
 *
 */
@Entity
@Table(name = "yly_nurse_arrangement")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_nurse_arrangement_sequence")
@Indexed(index = "nurseArrangement/nurseArrangement")
public class NurseArrangement extends BaseEntity{

  private static final long serialVersionUID = 378146953353247686L;
  
  /**
   * 租户ID
   */
  private Long tenantID;
  /**
   * 护理开始日期
   */
  private Date nurseStartDate;
  /**
   * 护理结束日期
   */
  private Date nurseEndDate;
  /**
   * 护理名称
   */
  private String nurseName;
  /**
   * 被护理的老人
   */
  private ElderlyInfo elderlyInfo;
  /**
   * 护理员(指'否参与护理'为true,即属性isJoinNurse=true的TenantUser)
   */
  private TenantUser nurseAssistant;
  /**
   * 护理级别
   */
  private String nursingLevel;
  /**
   * 床位位置
   */
  private String bedLocation;
  /**
   * 备注
   */
  private String remark;
  /**
   * 护理安排记录
   */
  private Set<NurseArrangementRecord> nurseArrangementRecords = new HashSet<NurseArrangementRecord>();
  
  @Index(name="nurse_arrangement_tenantid")
  @Field(store = Store.NO, index = org.hibernate.search.annotations.Index.UN_TOKENIZED,
  analyzer = @Analyzer(impl = IKAnalyzer.class))
  public Long getTenantID() {
    return tenantID;
  }
  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }
  @JsonProperty
  @Field(index = org.hibernate.search.annotations.Index.UN_TOKENIZED, store = Store.NO)
  @FieldBridge(impl = DateBridgeImpl.class)
  public Date getNurseStartDate() {
    return nurseStartDate;
  }
  public void setNurseStartDate(Date nurseStartDate) {
    this.nurseStartDate = nurseStartDate;
  }
  @JsonProperty
  @Field(index = org.hibernate.search.annotations.Index.UN_TOKENIZED, store = Store.NO)
  @FieldBridge(impl = DateBridgeImpl.class)
  public Date getNurseEndDate() {
    return nurseEndDate;
  }
  public void setNurseEndDate(Date nurseEndDate) {
    this.nurseEndDate = nurseEndDate;
  }
  @JsonProperty
  @Column(length=50)
  @Field(store = Store.NO, index = org.hibernate.search.annotations.Index.TOKENIZED,analyzer = @Analyzer(impl = IKAnalyzer.class))
  public String getNurseName() {
    return nurseName;
  }
  public void setNurseName(String nurseName) {
    this.nurseName = nurseName;
  }
  @JsonProperty
  @ManyToOne(fetch = FetchType.LAZY)
  @IndexedEmbedded
  public ElderlyInfo getElderlyInfo() {
    return elderlyInfo;
  }
  public void setElderlyInfo(ElderlyInfo elderlyInfo) {
    this.elderlyInfo = elderlyInfo;
  }
  @JsonProperty
  @ManyToOne(fetch = FetchType.LAZY)
  @IndexedEmbedded
  public TenantUser getNurseAssistant() {
    return nurseAssistant;
  }
  public void setNurseAssistant(TenantUser nurseAssistant) {
    this.nurseAssistant = nurseAssistant;
  }
  /**
   * 获取护理级别配置的value
   */
  @JsonProperty
  @Transient
  public String getNursingLevel() {
    if (elderlyInfo != null && elderlyInfo.getNursingLevel() != null) {
      nursingLevel = elderlyInfo.getNursingLevel().getConfigValue();
    }
    return nursingLevel;
  }
  public void setNursingLevel(String nursingLevel) {
    this.nursingLevel = nursingLevel;
  }
  /**
   * 获取床位所在位置（格式：楼宇+床位号+房间类型）
   */
  @JsonProperty
  @Transient
  public String getBedLocation() {
    StringBuffer str = new StringBuffer();
    if (elderlyInfo != null && elderlyInfo.getBed() != null) {
      Room room = elderlyInfo.getBed().getRoom();
      str.append(room.getBuilding().getBuildingName());
      str.append(elderlyInfo.getBed().getBedNumber());
      str.append(room.getRoomType().getConfigValue());
      bedLocation = str.toString();
    }
    return bedLocation;
  
  }
  public void setBedLocation(String bedLocation) {
    this.bedLocation = bedLocation;
  }
  @JsonProperty
  @Column(length=500)
  public String getRemark() {
    return remark;
  }
  public void setRemark(String remark) {
    this.remark = remark;
  }
  @OneToMany(mappedBy = "nurseArrangement", cascade = CascadeType.ALL)
  public Set<NurseArrangementRecord> getNurseArrangementRecords() {
    return nurseArrangementRecords;
  }
  public void setNurseArrangementRecords(Set<NurseArrangementRecord> nurseArrangementRecords) {
    this.nurseArrangementRecords = nurseArrangementRecords;
  }
    
}
