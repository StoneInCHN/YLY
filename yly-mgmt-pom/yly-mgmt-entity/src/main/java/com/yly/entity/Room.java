package com.yly.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.Index;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
import org.wltea.analyzer.lucene.IKAnalyzer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yly.entity.Bed;
import com.yly.entity.Building;
import com.yly.entity.SystemConfig;
import com.yly.entity.WaterElectricityRecord;
import com.yly.entity.base.BaseEntity;
import com.yly.entity.commonenum.CommonEnum.RoomStatus;



/**
 * 
 * @author tanbiao
 *
 *         房 间
 *
 */
@Entity
@Table(name = "yly_room")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_room_sequence")
@Indexed(index="room")
public class Room extends BaseEntity {

  /**
   * 
   */
  private static final long serialVersionUID = 6025035825115932519L;

  
  /**
   * 租户ID
   */
  private Long tenantID;
  
  /**
   * 房间名称
   */
  private String roomName;

  /**
   * 房间编号
   */
  private String roomNumber;

  /**
   * 房间所在楼层
   */
  private Integer floor;

  /**
   * 房间类型(由数据字典进行管理)
   */
  private SystemConfig roomType;

  /**
   * 房间状态
   */
  private RoomStatus roomStatus;

  /**
   * 所属楼宇
   */
  private Building building;

  /**
   * 房间描述
   */
  private String description;


  /**
   * 房间
   */
  private Set<Bed> beds = new HashSet<Bed>();

  /**
   * 水电费记录
   */
  private Set<WaterElectricityRecord> waterElectricityRecord =
      new HashSet<WaterElectricityRecord>();

  @OneToMany(mappedBy = "room")
  public Set<WaterElectricityRecord> getWaterElectricityRecord() {
    return waterElectricityRecord;
  }

  public void setWaterElectricityRecord(Set<WaterElectricityRecord> waterElectricityRecord) {
    this.waterElectricityRecord = waterElectricityRecord;
  }

  @JsonProperty
  @Column(length = 20)
  @Field(store = Store.NO, index = org.hibernate.search.annotations.Index.TOKENIZED, analyzer = @Analyzer(impl = IKAnalyzer.class))
  public String getRoomName() {
    return roomName;
  }

  public void setRoomName(String roomName) {
    this.roomName = roomName;
  }

  @JsonProperty
  @Column(length = 20)
  @Field(store = Store.NO, index = org.hibernate.search.annotations.Index.TOKENIZED, analyzer = @Analyzer(impl = IKAnalyzer.class))
  public String getRoomNumber() {
    return roomNumber;
  }

  public void setRoomNumber(String roomNumber) {
    this.roomNumber = roomNumber;
  }


  @JsonProperty
  @OneToOne
  public SystemConfig getRoomType() {
    return roomType;
  }

  public void setRoomType(SystemConfig roomType) {
    this.roomType = roomType;
  }

  @JsonProperty
  public RoomStatus getRoomStatus() {
    return roomStatus;
  }

  public void setRoomStatus(RoomStatus roomStatus) {
    this.roomStatus = roomStatus;
  }

  @JsonProperty
  @ManyToOne(fetch = FetchType.EAGER)
  public Building getBuilding() {
    return building;
  }

  public void setBuilding(Building building) {
    this.building = building;
  }

  @JsonProperty
  @Column(length = 100)
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
  public Set<Bed> getBeds() {
    return beds;
  }

  public void setBeds(Set<Bed> beds) {
    this.beds = beds;
  }

  @JsonProperty
  public Integer getFloor() {
    return floor;
  }

  public void setFloor(Integer floor) {
    this.floor = floor;
  }

  @Index(name = "room_tenantid")
  public Long getTenantID() {
        return tenantID;
  }
    
    public void setTenantID(Long tenantID) {
        this.tenantID = tenantID;
    }

}
