package com.yly.utils;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ToolsUtils implements Serializable {

  private static final long serialVersionUID = 7708468758384338657L;

  private static final String billDateFormat ="yyMMddHHmmss";

  private static Integer baseNo = 1;
  
  
  /**
   * 生成账单号
   * 格式 yyMMddHHmmss-组织机构代码-(1-1000累加)
   * @return
   */
  public static String generateBillNo (String orgCode)
  {
    StringBuffer strBuffer = new StringBuffer();
    SimpleDateFormat sdf = new SimpleDateFormat (billDateFormat);
    String strDate=sdf.format (new Date());
    strBuffer.append(strDate);
    strBuffer.append(orgCode);
    //订单尾号增自999时重置
    if (baseNo == 999) {
      baseNo = 1;
    }
    if (baseNo.toString().length()==1) {
      strBuffer.append("00");
    }
    if (baseNo.toString().length()==2) {
      strBuffer.append("0");
    }
    strBuffer.append(baseNo++);
    return strBuffer.toString();
  }
}
