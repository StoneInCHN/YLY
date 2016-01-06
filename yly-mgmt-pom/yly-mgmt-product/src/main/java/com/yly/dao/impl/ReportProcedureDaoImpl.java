package com.yly.dao.impl;

import org.springframework.stereotype.Repository;

import com.yly.dao.ReportProcedureDao;
import com.yly.entity.base.BaseEntity;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * DaoImpl - 报表存储过程调用
 * @author yohu
 *
 */
@Repository("reportProcedureDaoImpl")
public class ReportProcedureDaoImpl extends BaseDaoImpl<BaseEntity, Long> implements ReportProcedureDao {


}
