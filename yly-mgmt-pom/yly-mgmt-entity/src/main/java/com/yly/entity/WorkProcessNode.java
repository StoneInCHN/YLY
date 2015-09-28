package com.yly.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yly.entity.base.BaseEntity;

/**
 * 租户工作流任务节点
 * 
 * @author sujinxuan
 *
 */
@Entity
@Table(name = "yly_work_process_node")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_work_process_node_sequence")
public class WorkProcessNode extends BaseEntity {

  private static final long serialVersionUID = 2342688410044202569L;


  /**
   * 任务名
   */
  private String nodeName;

  /**
   * 任务节点处理人
   */
  private String nodeOperator;

  /**
   * 任务节点处理顺序
   */
  private Integer processOrder;

  /**
   * 工作流实例
   */
  private WorkProcess workProcess;


  public String getNodeName() {
    return nodeName;
  }

  public void setNodeName(String nodeName) {
    this.nodeName = nodeName;
  }

  public String getNodeOperator() {
    return nodeOperator;
  }

  public void setNodeOperator(String nodeOperator) {
    this.nodeOperator = nodeOperator;
  }

  public Integer getProcessOrder() {
    return processOrder;
  }

  public void setProcessOrder(Integer processOrder) {
    this.processOrder = processOrder;
  }

  @ManyToOne
  public WorkProcess getWorkProcess() {
    return workProcess;
  }

  public void setWorkProcess(WorkProcess workProcess) {
    this.workProcess = workProcess;
  }
}
