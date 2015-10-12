package com.yly.dao;

import com.yly.entity.commonenum.CommonEnum.IdentifierType;

/**
 * 人员编号
 * 
 * @author shijun
 *
 */
public interface IdentifierDao {
  
  /**
   * 生成序列号
   * 
   * @param type
   *            类型
   * @return 序列号
   */
  String generate(IdentifierType idType);


}
