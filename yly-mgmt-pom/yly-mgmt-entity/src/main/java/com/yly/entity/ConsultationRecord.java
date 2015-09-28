package com.yly.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yly.entity.base.BaseEntity;
import com.yly.entity.commonenum.CommonEnum.CheckinIntention;
import com.yly.entity.commonenum.CommonEnum.Gender;
import com.yly.entity.commonenum.CommonEnum.InfoChannel;
import com.yly.entity.commonenum.CommonEnum.Relation;

/**
 * 咨询记录
 * 
 * @author shijun
 *
 */
@Entity
@Table(name = "yly_consultation_record")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_consultation_record_sequence")
public class ConsultationRecord extends BaseEntity {

	/**
   * 
   */
	private static final long serialVersionUID = 2807294073579440441L;

	/**
	 * 租户ID
	 */
	private Long tenantID;

	/**
	 * 来访咨询人员
	 */
	private String vistor;

	/**
	 * 接待人员
	 */
	private String receptionist;

	/** 员工编号 */
	private String staffID;

	/**
	 * 入住老人数
	 */
	private Integer elderlyNumber;

	/**
	 * 电话号码
	 */
	private String phoneNumber;

	/**
	 * 信息来源渠道
	 */
	private InfoChannel infoChannel;

	/**
	 * 入住意向
	 */
	private CheckinIntention checkinIntention;

	/**
	 * 入住老人姓名
	 */
	private String elderlyName;

	/**
	 * 性别
	 */
	private Gender gender;

	/**
	 * 是否需要回访
	 */
	private Boolean returnVisit;

	/**
	 * 回访时间设定
	 */
	private Date returnVisitDate;

	/**
	 * 老人健康状况
	 */
	private String elderlyHealth;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 咨询人和老人关系
	 */
	private Relation relation;

	@JsonProperty
	@Column(length = 15)
	public String getVistor() {
		return vistor;
	}

	public void setVistor(String vistor) {
		this.vistor = vistor;
	}

	@JsonProperty
	@Column(length = 15)
	public String getReceptionist() {
		return receptionist;
	}

	public void setReceptionist(String receptionist) {
		this.receptionist = receptionist;
	}

	public Integer getElderlyNumber() {
		return elderlyNumber;
	}

	public void setElderlyNumber(Integer elderlyNumber) {
		this.elderlyNumber = elderlyNumber;
	}

	@JsonProperty
	@Column(length = 15)
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@JsonProperty
	public InfoChannel getInfoChannel() {
		return infoChannel;
	}

	public void setInfoChannel(InfoChannel infoChannel) {
		this.infoChannel = infoChannel;
	}

	@JsonProperty
	public CheckinIntention getCheckinIntention() {
		return checkinIntention;
	}

	public void setCheckinIntention(CheckinIntention checkinIntention) {
		this.checkinIntention = checkinIntention;
	}

	@JsonProperty
	@Column(length = 15)
	public String getElderlyName() {
		return elderlyName;
	}

	public void setElderlyName(String elderlyName) {
		this.elderlyName = elderlyName;
	}

	@JsonProperty
	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@JsonProperty
	public Boolean getReturnVisit() {
		return returnVisit;
	}

	public void setReturnVisit(Boolean returnVisit) {
		this.returnVisit = returnVisit;
	}

	@JsonProperty
	public Date getReturnVisitDate() {
		return returnVisitDate;
	}

	public void setReturnVisitDate(Date returnVisitDate) {
		this.returnVisitDate = returnVisitDate;
	}

	@JsonProperty
	@Column(length = 150)
	public String getElderlyHealth() {
		return elderlyHealth;
	}

	public void setElderlyHealth(String elderlyHealth) {
		this.elderlyHealth = elderlyHealth;
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
	public Relation getRelation() {
		return relation;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
	}

	
	@Column(length = 30)
	public String getStaffID() {
		return staffID;
	}

	public void setStaffID(String staffID) {
		this.staffID = staffID;
	}

	@Index(name = "consultation_record_tenantid")
	public Long getTenantID() {
		return tenantID;
	}

	public void setTenantID(Long tenantID) {
		this.tenantID = tenantID;
	}
}
