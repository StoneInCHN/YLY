package com.yly.utils;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtils implements Serializable {

  private static final long serialVersionUID = 7708468758384338657L;

  private static final SimpleDateFormat longDateFormat =
      new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  private static final SimpleDateFormat shortDateFormat = new SimpleDateFormat("yyyy-MM-dd");

  // public static void main(String args[]){
  // System.out.println(getLastWeek()+"--"+getLastMonth());
  // }
  
  /**
   * 获取账单结算日期距起始日期的天数（不满一个月）
   * @param currentDate
   * @return
   */
  public static Integer getBillDays(Date currentDate,int billDay){
	  Calendar calendar = Calendar.getInstance();
	    if (currentDate == null) {
	      return null;
	    }
	    calendar.setTime(currentDate);
	    calendar.add(Calendar.DATE,-1);//日期，整数往后推,负数往前移动 
	    if (calendar.get(Calendar.DATE) > billDay) {
	    	 //获取当前月最后一天
	    	 Calendar ca = Calendar.getInstance();    
	         ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
	    	return ca.get(Calendar.DATE)-calendar.get(Calendar.DATE)+billDay;
	    }else if(calendar.get(Calendar.DATE) < billDay) {
			return billDay-calendar.get(Calendar.DATE);
		}
	  
	return 0;
	  
  }
  /**
   * 获取账单结算日期
   * @param currentDate
   * @return
   */
  public static Date getBillDate(Date currentDate,int billDay){
    Calendar calendar = Calendar.getInstance();
    if (currentDate == null) {
      return null;
    }
    calendar.setTime(currentDate);
    calendar.add(Calendar.DATE,-1);//日期，整数往后推,负数往前移动 
    if (calendar.get(Calendar.DATE) == billDay) {
      calendar.add(Calendar.MONTH,1);//月份
      
    }else if(calendar.get(Calendar.DATE) > billDay){
      calendar.add(Calendar.MONTH,2);
      calendar.set(Calendar.DATE, billDay);
    }else {
      calendar.add(Calendar.MONTH,1);
      calendar.set(Calendar.DATE, billDay);
    }
      
    return new Date(calendar.getTimeInMillis());
    
  }
  
  /**
   * 取当天(短日期类型)
   */
  public static String getShortToday() {
    Date today = new Date();
    return shortDateFormat.format(today);
  }

  /**
   * 取当天(长日期类型)
   */
  public static String getLongToday() {
    Date today = new Date();
    return longDateFormat.format(today);
  }


  /**
   * 取当天零点零分零秒
   */
  public static String getTodayStart() {
    Calendar calendar = Calendar.getInstance();
    // 如果没有这种设定的话回去系统的当期的时间
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    Date date = new Date(calendar.getTimeInMillis());
    return longDateFormat.format(date);
  }

  /**
   * 取得特定日期的零点零分零秒
   */
  public static String getDateStartTime(Date date) {

    return shortDateFormat.format(date) + " 00:00:00";
  }

 /**
  * 取特定日期的特定时间
  * @return
  */
  public static Date getSpecifyTimeForDate(Date date,int h,int m,int s) {
    if (date == null) {
      return null;
    } 
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.set(Calendar.HOUR_OF_DAY, h);
    calendar.set(Calendar.MINUTE, m);
    calendar.set(Calendar.SECOND, s);
    calendar.set(Calendar.MILLISECOND, 0);
    return new Date(calendar.getTimeInMillis());
  }
  
  /**
   * 取当天零点零分零秒
   */
  public static Date getTodayStartForDate() {
    Calendar calendar = Calendar.getInstance();
    // 如果没有这种设定的话回去系统的当期的时间
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    return new Date(calendar.getTimeInMillis());
  }

  /**
   * 取当天23点59分59秒
   */
  public static String getTodayEnd() {
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.HOUR_OF_DAY, 23);
    calendar.set(Calendar.MINUTE, 59);
    calendar.set(Calendar.SECOND, 59);
    Date date = new Date(calendar.getTimeInMillis());
    return longDateFormat.format(date);
  }

  /**
   * 取特定日期的零点零分零秒
   */
  public static String getDateStart(String strDate) {
    if (null == strDate || "".equals(strDate.trim())) {
      return "";
    }
    Date date = null;
    try {
      date = shortDateFormat.parse(strDate);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return longDateFormat.format(date);
  }

  public static String getDateStart(Date date) {
    return longDateFormat.format(date);
  }

  /**
   * 取特定日期的23点59分59秒
   */
  public static String getDateEnd(String strDate) {
    if (null == strDate || "".equals(strDate.trim())) {
      return "";
    }
    Date date = null;
    try {
      date = shortDateFormat.parse(strDate);
    } catch (ParseException e) {
      e.printStackTrace();
    }

    // 这样能够取到59分59秒
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.set(Calendar.HOUR_OF_DAY, 23);
    calendar.set(Calendar.MINUTE, 59);
    calendar.set(Calendar.SECOND, 59);
    calendar.set(Calendar.MILLISECOND, 0);

    return longDateFormat.format(calendar.getTime());
  }

  /**
   * 两个时间之间的天数
   */
  public static long getDays(String date1, String date2) {
    if (date1 == null || "".equals(date1.trim())) {
      return 0;
    }
    if (date2 == null || "".equals(date2.trim())) {
      return 0;
    }
    // 转换为标准时间
    Date date = null;
    Date mydate = null;
    try {
      date = shortDateFormat.parse(date1);
      mydate = shortDateFormat.parse(date2);
    } catch (Exception e) {
      e.printStackTrace();
    }
    long day = 0;
    if (date.before(mydate)) {
      day = (mydate.getTime() - date.getTime()) / (24 * 60 * 60 * 1000);
    } else {
      day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
    }
    return day;
  }

  /**
   * 两个时间之间的天数,不取绝对值，返回正负关系
   */
  public static long getDaysNotAbs(String date1, String date2) {
    if (date1 == null || "".equals(date1.trim())) {
      return 0;
    }
    if (date2 == null || "".equals(date2.trim())) {
      return 0;
    }
    // 转换为标准时间
    Date date = null;
    Date mydate = null;
    try {
      date = shortDateFormat.parse(date1);
      mydate = shortDateFormat.parse(date2);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
  }
  
  
  /**
   * 两个时间之间的天数
   */
  public static long getDays(Date date1, Date date2) {
    return (date1.getTime() - date2.getTime()) / (24 * 60 * 60 * 1000);
  }

  /**
   * 取昨天零点零分零秒
   */
  public static Date getYesterDayStart() {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DATE, -1);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    return calendar.getTime();
  }

  /**
   * 取昨天23点59分59秒
   */
  public static Date getYesterdayEnd() {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DATE, -1);
    calendar.set(Calendar.HOUR_OF_DAY, 23);
    calendar.set(Calendar.MINUTE, 59);
    calendar.set(Calendar.SECOND, 59);
    return calendar.getTime();
  }

  /**
   * 获取最近一周时间（日期减7天）
   */
  public static Date getLastWeek() {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DATE, -7);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    return calendar.getTime();
  }

  /**
   * 获取最近一个月时间（月份减1）
   */
  public static Date getLastMonth() {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.MONTH, -1);
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    return calendar.getTime();
  }

  /**
   * 判断string是否为有效日期格式
   * @param str
   * @return
   */
  public static boolean isValidDate (String str)
  {
    boolean convertSuccess = true;
    // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
    SimpleDateFormat format = new SimpleDateFormat ("yyyyMMdd");
    try
    {
      // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
      format.setLenient (false);
      format.parse (str);
    }
    catch (ParseException e)
    {
      // e.printStackTrace();
      // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
      convertSuccess = false;
    }
    return convertSuccess;
  }
  
  /**
   * Date 转String
   * @param date 需要转换日期
   * @param format 格式，默认yyyy/MM/dd
   * @return
   */
  public static String convertDateToString (Date date,String format)
  {
    // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
    if (format == null)
    {
      format = "yyyyMMdd";
    }
    if(date == null){
      return null;
    }
    SimpleDateFormat sdf = new SimpleDateFormat (format);
      // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
    sdf.setLenient (false);
    String strDate=sdf.format (date);
   
    return strDate;
  }
}
