package com.yly.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.yly.dao.ReportNurseLevelStatisticsDao;
import com.yly.entity.ReportNurseLevelStatistics;
import com.yly.framework.dao.impl.BaseDaoImpl;

/**
 * DaoImpl - 护理统计报表
 * @author yohu
 *
 */
@Repository("reportNurseLevelStatisticsDaoImpl")
public class ReportNurseLevelStatisticsDaoImpl extends BaseDaoImpl<ReportNurseLevelStatistics, Long> implements ReportNurseLevelStatisticsDao {

  @Override
  public void report ()
  {
    Query query = entityManager.createQuery("select p.sex, count(p) from Person p group by p.sex having count(*) >?1");
    List result = query.getResultList();
  }

}
