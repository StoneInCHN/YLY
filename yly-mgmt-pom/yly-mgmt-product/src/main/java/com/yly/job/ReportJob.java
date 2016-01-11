package com.yly.job;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.yly.common.log.LogUtil;
import com.yly.service.ReportElderlyLiveStatiticsService;
import com.yly.service.ReportProcedureService;
import com.yly.utils.DateTimeUtils;

@Component
@Lazy(false)
public class ReportJob
{
  
  @Resource(name = "reportElderlyLiveStatiticsServiceImpl")
  private ReportElderlyLiveStatiticsService reportElderlyLiveStatiticsService;
  
  @Resource(name = "reportProcedureServiceImpl")
  private ReportProcedureService reportProcedureService;
  
//  @Scheduled(cron="0/60 * *  * * ? ")   //每5秒执行一次  
  public void reprotNurseLevel()
  {
    
    Date currentDate = new Date ();
    
    LogUtil.debug (ReportJob.class, "reprotNurseLevel", "reprot data generate start !");
    reportProcedureService.callProcedure ("report_nurse_level_pr","1",DateTimeUtils.convertDateToString (currentDate, "YYYY-MM-DD"));
    LogUtil.debug (ReportJob.class, "reprotNurseLevel", "reprot data generate end!");
  }
//@Scheduled(cron="0/60 * *  * * ? ")   //每5秒执行一次  
public void reportElderlyEvlauting()
{
  
  Date currentDate = new Date ();
  
  LogUtil.debug (ReportJob.class, "reportElderlyEvlauting", "reprot data generate start !");
  reportProcedureService.callProcedure ("report_elderly_evaluating_record_pr","1",DateTimeUtils.convertDateToString (currentDate, "YYYY-MM-DD"));
  LogUtil.debug (ReportJob.class, "reportElderlyEvlauting", "reprot data generate end!");
}
  
//  @Scheduled(cron="0/10 * *  * * ? ")   //每5秒执行一次  
  public void reprotDonateStatistics()
  {
    
    Date currentDate = new Date ();
    
    LogUtil.debug (ReportJob.class, "reprotDonateStatistics", "reprot data generate start !");
    reportProcedureService.callProcedure ("report_donate_statistics_pr","1",DateTimeUtils.convertDateToString (currentDate, "YYYY-MM-DD"));
    LogUtil.debug (ReportJob.class, "reprotDonateStatistics", "reprot data generate end!");
  }
}
