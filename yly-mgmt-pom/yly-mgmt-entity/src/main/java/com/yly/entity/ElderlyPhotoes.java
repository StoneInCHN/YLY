package com.yly.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yly.entity.base.BaseEntity;

/**
 * 老人照片
 * 
 * @author shijun
 *
 */

@Entity
@Table(name = "yly_elderly_photoes")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "yly_elderly_photoes_sequence")
public class ElderlyPhotoes extends BaseEntity {

  /**
   * 
   */
  private static final long serialVersionUID = -8752653255644532354L;

  private String name;
  
  private String url;
  
  private ElderlyPhotoAlbum elderlyPhotoAlbum;
  
  @Column(length = 50)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  public ElderlyPhotoAlbum getElderlyPhotoAlbum() {
    return elderlyPhotoAlbum;
  }

  public void setElderlyPhotoAlbum(ElderlyPhotoAlbum elderlyPhotoAlbum) {
    this.elderlyPhotoAlbum = elderlyPhotoAlbum;
  }
}
