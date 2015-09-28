package com.yly.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yly.entity.base.BaseEntity;



/**
 * 
 * @author tanbiao
 * 
 * 楼宇
 * 
 */
@Entity
@Table(name = "yly_building")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_building_sequence")
public class Building extends BaseEntity {

  /**
   * 
   */
  private static final long serialVersionUID = -4219769890667114077L;
  
  
  /**
   * 租户ID
   */
  private Long tenantID;
  
  /**
   * 楼宇名称
   */
  private String buildingName;
  
  /**
   * 描述
   */
  private String description;
  
  /**
   * 房间
   */
  private Set<Room> rooms = new HashSet<Room>();

  @JsonProperty
  @NotEmpty
  @Length(max = 20)
  @Column(length = 20, nullable = false)
  public String getBuildingName() {
    return buildingName;
  }

  public void setBuildingName(String buildingName) {
    this.buildingName = buildingName;
  }

  @JsonProperty
  @Column(length = 100)
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @OneToMany(mappedBy = "building", fetch = FetchType.LAZY)
  public Set<Room> getRooms() {
    return rooms;
  }

  public void setRooms(Set<Room> rooms) {
    this.rooms = rooms;
  }

  @Index(name = "building_tenantid")
  public Long getTenantID() {
		return tenantID;
  }
	
	public void setTenantID(Long tenantID) {
		this.tenantID = tenantID;
	}
  
}
