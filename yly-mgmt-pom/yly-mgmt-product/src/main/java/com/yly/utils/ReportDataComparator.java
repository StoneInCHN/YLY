package com.yly.utils;

import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.Date;


public class ReportDataComparator implements Comparator
{

  private String compareProperty;
  public ReportDataComparator (String compareProperty)
  {
    this.compareProperty = compareProperty;
  }
  @Override
  public int compare (Object arg1, Object arg2)
  {

    Class<? extends Object> entity1 = arg1.getClass ();
    Class<? extends Object> entity2 = arg1.getClass ();
    Method m1;
    Method m2;
    try
    {
      m1 = entity1.getDeclaredMethod("get"+captureName (compareProperty));
      Date date1 = (Date) m1.invoke(arg1); 
      m2 = entity2.getDeclaredMethod("get"+captureName (compareProperty));
      Date date2 = (Date) m2.invoke(arg2);
      
      return date1.compareTo (date2);
    }
    catch (Exception e)
    {
      return 0;
    }
  }
  
  //首字母大写
  public String captureName(String str) {
    str = str.substring(0, 1).toUpperCase() + str.substring(1);
   return  str;
  
}
}
