package com.yly.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yly.entity.base.BaseEntity;
import com.yly.entity.commonenum.CommonEnum.BedStatus;
import com.yly.entity.commonenum.CommonEnum.UsageState;

/**
 * 
 * @author tanbiao
 *
 *  床位管理
 */

@Entity
@Table(name = "yly_bed")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_bed_sequence")
public class Bed extends BaseEntity{

  /**
   * 
   */
  private static final long serialVersionUID = -6055255863529939746L;

  
  /**
   * 租户ID
   */
  private Long tenantID;
  
  /**
   * 床位号
   */
  private String bedNumber;
  
  
  /**
   * 床位状态
   */
  private BedStatus status;
  
  /**
   * 床位使用状态
   */
  private  UsageState  usageState;
  
  /**
   * 描述
   */
  private String description;
  
  /**
   * 床位所在房间
   */
  private Room room;
  
  private ElderlyInfo elderlyInfo;
  
  @JsonProperty
  @Column(length = 20, nullable = false)
  public String getBedNumber() {
    return bedNumber;
  }

  public void setBedNumber(String bedNumber) {
    this.bedNumber = bedNumber;
  }

  @JsonProperty
  @Column(length=4)
  public BedStatus getStatus() {
    return status;
  }

  public void setStatus(BedStatus status) {
    this.status = status;
  }

  @JsonProperty
  @Column(length=100)
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @ManyToOne(fetch = FetchType.EAGER)
  public Room getRoom() {
    return room;
  }

  public void setRoom(Room room) {
    this.room = room;
  }


  @OneToOne
  public ElderlyInfo getElderlyInfo() {
  	return elderlyInfo;
  }
  
  public void setElderlyInfo(ElderlyInfo elderlyInfo) {
  	this.elderlyInfo = elderlyInfo;
  }
  
  @Index(name = "bed_tenantid")
  public Long getTenantID() {
        return tenantID;
  }
    
   public void setTenantID(Long tenantID) {
      this.tenantID = tenantID;
   }

   @JsonProperty
   @Column(length=4)
  public UsageState getUsageState() {
    return usageState;
  }

  public void setUsageState(UsageState usageState) {
    this.usageState = usageState;
  }
   
}
