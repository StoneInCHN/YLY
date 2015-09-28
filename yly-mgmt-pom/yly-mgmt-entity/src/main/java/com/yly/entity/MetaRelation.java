package com.yly.entity;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yly.entity.base.BaseEntity;

/**
 * 配置元关系
 * 
 * @author shijun
 *
 */
@Entity
@Table(name = "yly_meta_relation")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_meta_relation_sequence")
public class MetaRelation extends BaseEntity {

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
