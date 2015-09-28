package com.yly.entity;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yly.entity.base.BaseEntity;

/**
 * 租户配置关系,租户可能会修改每个功能之前的包含关系
 * 
 * @author shijun
 *
 */
@Entity
@Table(name = "yly_tenant_config_relation")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_tenant_config_relation_sequence")
public class TenantConfigRelation extends BaseEntity {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  /**
   * 主配置元ID
   */
  private Long mainID;

  /**
   * 关系配置元ID
   */
  private Long relationID;
  
  /**
   * 关系类型
   */
  private MetaRelation metaRelation;

  public Long getMainID() {
    return mainID;
  }

  public void setMainID(Long mainID) {
    this.mainID = mainID;
  }

  public Long getRelationID() {
    return relationID;
  }

  public void setRelationID(Long relationID) {
    this.relationID = relationID;
  }

  public MetaRelation getMetaRelation() {
    return metaRelation;
  }

  public void setMetaRelation(MetaRelation metaRelation) {
    this.metaRelation = metaRelation;
  }
}
