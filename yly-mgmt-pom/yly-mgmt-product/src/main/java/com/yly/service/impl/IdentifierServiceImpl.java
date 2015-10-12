package com.yly.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yly.dao.IdentifierDao;
import com.yly.entity.commonenum.CommonEnum.IdentifierType;
import com.yly.service.IdentifierService;

/**
 * 人员编号
 * 
 * @author shijun
 *
 */
@Service("identifierServiceImpl")
public class IdentifierServiceImpl implements IdentifierService {

  @Resource(name = "identifierDaoImpl")
  private IdentifierDao identifierDao;

  @Transactional
  public String generate(IdentifierType idType) {
      return identifierDao.generate(idType);
  }

}
