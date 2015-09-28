package com.yly.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yly.entity.base.BaseEntity;
import com.yly.entity.commonenum.CommonEnum.NodeApprovalStatus;
import com.yly.entity.commonenum.CommonEnum.NodeStatus;

/**
 * 租户工作流任务节点实例
 * 
 * @author sujinxuan
 *
 */
@Entity
@Table(name = "yly_work_process_node_instance")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_work_process_node_instance_sequence")
public class WorkProcessNodeInstance extends BaseEntity {

  private static final long serialVersionUID = -3510209255272586531L;
  
  /**
   * 任务实例提交人
   */
  private String tenantUserName;

  /**
   * 任务节点处理时间
   */
  private Date operateDate;

  /**
   * 任务节点处理人
   */
  private String nodeOperator;

  /**
   * 租户流程实例
   */
  private WorkProcessInstance workProcessInstance;

  /**
   * 任务节点状态
   */
  private NodeStatus nodeStatus;

  /**
   * 任务节点处理后状态
   */
  private NodeApprovalStatus nodeApprovalStatus;

  /**
   * 备注
   */
  private String remark;
  
  @ManyToOne
  public WorkProcessInstance getWorkProcessInstance() {
    return workProcessInstance;
  }

  public void setWorkProcessInstance(WorkProcessInstance workProcessInstance) {
    this.workProcessInstance = workProcessInstance;
  }

  @Column(length=15)
  public String getTenantUserName() {
    return tenantUserName;
  }

  public void setTenantUserName(String tenantUserName) {
    this.tenantUserName = tenantUserName;
  }

  @Column(length=50)
  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public Date getOperateDate() {
    return operateDate;
  }

  public void setOperateDate(Date operateDate) {
    this.operateDate = operateDate;
  }

  public String getNodeOperator() {
    return nodeOperator;
  }

  public void setNodeOperator(String nodeOperator) {
    this.nodeOperator = nodeOperator;
  }

  public NodeStatus getNodeStatus() {
    return nodeStatus;
  }

  public void setNodeStatus(NodeStatus nodeStatus) {
    this.nodeStatus = nodeStatus;
  }

  public NodeApprovalStatus getNodeApprovalStatus() {
    return nodeApprovalStatus;
  }

  public void setNodeApprovalStatus(NodeApprovalStatus nodeApprovalStatus) {
    this.nodeApprovalStatus = nodeApprovalStatus;
  }
}
