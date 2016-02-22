package com.yly.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.yly.entity.base.BaseEntity;
import com.yly.entity.commonenum.CommonEnum.VersionStatus;

@Entity
@Table (name = "yly_version_config")
@SequenceGenerator (name = "sequenceGenerator", sequenceName = "yly_version_config")
public class VersionConfig extends BaseEntity
{

  /**
   * 
   */
  private static final long serialVersionUID = -1856670758805303986L;
  /**
   * 版本名称
   */
  private String versionName;

  /**
   * 元数据
   */
  private Set<ConfigMeta> configMeta;
  /**
   * 租户
   */
  private Set<TenantInfo> tenantInfos;

  /**
   * 版本状态
   */
  private VersionStatus versionStatus;

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

  @OneToMany (mappedBy = "versionConfig", fetch = FetchType.LAZY)
  public Set<TenantInfo> getTenantInfos ()
  {
    return tenantInfos;
  }

  public void setTenantInfos (Set<TenantInfo> tenantInfos)
  {
    this.tenantInfos = tenantInfos;
  }

  public VersionStatus getVersionStatus ()
  {
    return versionStatus;
  }

  public void setVersionStatus (VersionStatus versionStatus)
  {
    this.versionStatus = versionStatus;
  }

}
