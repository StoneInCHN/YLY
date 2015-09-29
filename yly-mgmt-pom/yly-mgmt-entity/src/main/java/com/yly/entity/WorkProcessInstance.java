package com.yly.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.yly.entity.base.BaseEntity;
import com.yly.entity.commonenum.CommonEnum.NodeApprovalStatus;
import com.yly.entity.commonenum.CommonEnum.ProcessStatus;

/**
 * 租户工作流实例
 * 
 * @author sujinxuan
 *
 */
@Entity
@Table(name = "yly_work_process_instance")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_work_process_instance_sequence")
public class WorkProcessInstance extends BaseEntity {

  private static final long serialVersionUID = 8304371813309918141L;

  /**
   * 租户ID
   */
  private Long tenantID;

  /**
   * 租户流程实例提交人
   */
  private String processCreator;

  /**
   * 流程实例对应的节点实例
   */
  private Set<WorkProcessNodeInstance> workProcessNodeInstance =
      new HashSet<WorkProcessNodeInstance>();

  /**
   * 自定义表单实例
   */
  private FormInstance formInstance;

  /**
   * 任务流程整体状态
   */
  private ProcessStatus processStatus;

  /**
   * 任务流程整体处理后状态
   */
  private NodeApprovalStatus processApprovalStatus;

  public ProcessStatus getProcessStatus() {
    return processStatus;
  }

  public void setProcessStatus(ProcessStatus processStatus) {
    this.processStatus = processStatus;
  }

  public NodeApprovalStatus getProcessApprovalStatus() {
    return processApprovalStatus;
  }

  public void setProcessApprovalStatus(NodeApprovalStatus processApprovalStatus) {
    this.processApprovalStatus = processApprovalStatus;
  }

  @OneToOne
  public FormInstance getFormInstance() {
    return formInstance;
  }

  public void setFormInstance(FormInstance formInstance) {
    this.formInstance = formInstance;
  }

  @Index(name = "work_process_instance_tenantid")
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }

  @OneToMany(mappedBy = "")
  public Set<WorkProcessNodeInstance> getWorkProcessNodeInstance() {
    return workProcessNodeInstance;
  }

  public void setWorkProcessNodeInstance(Set<WorkProcessNodeInstance> workProcessNodeInstance) {
    this.workProcessNodeInstance = workProcessNodeInstance;
  }

  public String getProcessCreator() {
    return processCreator;
  }

  public void setProcessCreator(String processCreator) {
    this.processCreator = processCreator;
  }

}
