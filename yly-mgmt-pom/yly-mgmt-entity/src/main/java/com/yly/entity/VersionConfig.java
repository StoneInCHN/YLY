package com.yly.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yly.entity.base.BaseEntity;

@Entity
@Table(name = "yly_version_config")
@SequenceGenerator(name = "sequenceGenerator",
    sequenceName = "yly_version_config")
public class VersionConfig extends BaseEntity
{

  /**
   * 
   */
  private static final long serialVersionUID = -1856670758805303986L;
  private String versionName;
  private Set<ConfigMeta> configMeta;
  private Set<TenantConfigInfo> tenantConfigInfo;

  public String getVersionName ()
  {
    return versionName;
  }

  public void setVersionName (String versionName)
  {
    this.versionName = versionName;
  }

  @ManyToMany
  public Set<ConfigMeta> getConfigMeta ()
  {
    return configMeta;
  }

  public void setConfigMeta (Set<ConfigMeta> configMeta)
  {
    this.configMeta = configMeta;
  }
  
  @OneToMany(mappedBy = "versionConfig", fetch = FetchType.LAZY)
  public Set<TenantConfigInfo> getTenantConfigInfo ()
  {
    return tenantConfigInfo;
  }

  public void setTenantConfigInfo (Set<TenantConfigInfo> tenantConfigInfo)
  {
    this.tenantConfigInfo = tenantConfigInfo;
  }

}
