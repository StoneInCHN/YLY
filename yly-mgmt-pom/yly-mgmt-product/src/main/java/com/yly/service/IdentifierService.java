package com.yly.service;

import com.yly.entity.commonenum.CommonEnum.IdentifierType;

/**
 * 人员编号
 * 
 * @author shijun
 *
 */
public interface IdentifierService {

  /**
   * 生成编号
   * 
   * @param type
   *            类型
   * @return 编号
   */
  String generate(IdentifierType idType);

}
