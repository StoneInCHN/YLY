package com.yly.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.yly.entity.base.BaseEntity;

/**
 * 租户工作流实例
 * 
 * @author sujinxuan
 *
 */
@Entity
@Table(name = "yly_work_process")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_work_process_sequence")
public class WorkProcess extends BaseEntity {

  private static final long serialVersionUID = 8304371813309918141L;

  /**
   * 租户ID
   */
  private Long tenantID;

  /**
   * 租户流程实例名称
   */
  private String processName;

  /**
   * 租户流程实例创建人
   */
  private String processCreator;

  /**
   * 工作流任务节点
   */
  private Set<WorkProcessNode> workProcessNode = new HashSet<WorkProcessNode>();

  /**
   * 自定义表单
   */
  private FormTemplate formTemplate;


  @OneToOne
  public FormTemplate getFormTemplate() {
    return formTemplate;
  }

  public void setFormTemplate(FormTemplate formTemplate) {
    this.formTemplate = formTemplate;
  }

  @Index(name = "work_process_tenantid")
  public Long getTenantID() {
    return tenantID;
  }

  public void setTenantID(Long tenantID) {
    this.tenantID = tenantID;
  }

  @OneToMany(mappedBy = "workProcess")
  public Set<WorkProcessNode> getWorkProcessNode() {
    return workProcessNode;
  }

  public void setWorkProcessNode(Set<WorkProcessNode> workProcessNode) {
    this.workProcessNode = workProcessNode;
  }

  @Column(length = 50)
  public String getProcessName() {
    return processName;
  }

  public void setProcessName(String processName) {
    this.processName = processName;
  }

  public String getProcessCreator() {
    return processCreator;
  }

  public void setProcessCreator(String processCreator) {
    this.processCreator = processCreator;
  }

}
