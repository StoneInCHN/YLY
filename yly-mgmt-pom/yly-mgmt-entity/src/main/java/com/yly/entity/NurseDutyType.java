package com.yly.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yly.entity.base.BaseEntity;

/**
 * 护理员排班类型
 * @author tanbiao
 *
 */
@Entity
@Table(name = "yly_nurse_duty_type")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_nurse_duty_type_sequence")
public class NurseDutyType extends BaseEntity {

	/**
   * 
   */
	private static final long serialVersionUID = 790617090017918707L;

	/**
	 * 租户ID
	 */
	private Long tenantID;

	/**
	 * 排班开始时间
	 */
	private String dutyStartTime;

	/**
	 * 排班结束时间
	 */
	private String dutyEndTime;

	/**
	 * 班次名称
	 */
	private String dutyName;
	
	/**
	 * 排序
	 */
	private Integer orderIndex;

	/**
	 * 备注
	 */
	private String remark;

	@Index(name = "nurse_duty_type_tenantid")
	public Long getTenantID() {
		return tenantID;
	}

	public void setTenantID(Long tenantID) {
		this.tenantID = tenantID;
	}

	@JsonProperty
	@Column(length = 10)
	public String getDutyStartTime() {
		return dutyStartTime;
	}

	public void setDutyStartTime(String dutyStartTime) {
		this.dutyStartTime = dutyStartTime;
	}

	@JsonProperty
	@Column(length = 10)
	public String getDutyEndTime() {
		return dutyEndTime;
	}

	public void setDutyEndTime(String dutyEndTime) {
		this.dutyEndTime = dutyEndTime;
	}

	@JsonProperty
	@Column(length = 10)
	public String getDutyName() {
		return dutyName;
	}

	public void setDutyName(String dutyName) {
		this.dutyName = dutyName;
	}

	@JsonProperty
	@Column(length = 200)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@JsonProperty
    @Column(length = 4)
  public Integer getOrderIndex() {
    return orderIndex;
  }

  public void setOrderIndex(Integer orderIndex) {
    this.orderIndex = orderIndex;
  }

	

	
	
}
