package com.yly.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.yly.entity.base.BaseEntity;

/**
 * 老人照片分组
 * 
 * @author shijun
 *
 */
@Entity
@Table(name = "yly_elderly_photo_album")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_elderly_photo_album_sequence")
public class ElderlyPhotoAlbum extends BaseEntity {

  /**
   * 
   */
  private static final long serialVersionUID = -7449721699698597668L;

  /**
   * 租户ID
   */
  private Long tenantID;
  
  /**
   * 相册名称
   */
  private String name;

  /**
   * 备注
   */
  private String remark;

  /**
   * 封面
   */
  private String albumCover;
  
  private Set<ElderlyPhotoes> elderlyPhotoes = new HashSet<ElderlyPhotoes>();
  
  private ElderlyInfo elderlyInfo;

  @Column(length = 15)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Column(length = 30)
  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public String getAlbumCover() {
    return albumCover;
  }

  public void setAlbumCover(String albumCover) {
    this.albumCover = albumCover;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  public ElderlyInfo getElderlyInfo() {
    return elderlyInfo;
  }

  public void setElderlyInfo(ElderlyInfo elderlyInfo) {
    this.elderlyInfo = elderlyInfo;
  }

  @OneToMany(mappedBy = "elderlyPhotoAlbum" , fetch = FetchType.LAZY)
  public Set<ElderlyPhotoes> getElderlyPhotoes() {
    return elderlyPhotoes;
  }

  public void setElderlyPhotoes(Set<ElderlyPhotoes> elderlyPhotoes) {
    this.elderlyPhotoes = elderlyPhotoes;
  }

  @Index(name = "elderlyphoto_ablum_tenantid")
public Long getTenantID() {
	return tenantID;
}

public void setTenantID(Long tenantID) {
	this.tenantID = tenantID;
}
  
  
}
