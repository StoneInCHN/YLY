package com.yly.utils;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.json.JSONArray;
import org.json.JSONObject;

import com.yly.entity.base.BaseEntity;

/**
 * 导出excel
 * @author luzhang
 */
public class ExportExcel implements Runnable {
    
    private String title;
    private JSONArray jsonArray;
    private Collection<? extends BaseEntity> baseEntityList;
    private OutputStream out;
    public ExportExcel(String title, JSONArray jsonArray,
      Collection<? extends BaseEntity> baseEntityList, OutputStream out){
    this.title = title;
    this.jsonArray = jsonArray;
    this.baseEntityList = baseEntityList;
    this.out = out;
  }
   public void exportExcel(Collection<? extends BaseEntity> dataset, OutputStream out) {
      exportExcel("YLY_DATA", null, dataset, out, "yyyy-MM-dd");
   }
 
   public void exportExcel(String title, JSONArray jsonArray, Collection<? extends BaseEntity> dataset,
         OutputStream out) {
      exportExcel(title, jsonArray, dataset, out, "yyyy-MM-dd");
   }
   @Override
   public void run() {
     exportExcel(title, jsonArray, baseEntityList, out);
   }
   
   //@SuppressWarnings("unchecked")
   public void exportExcel(String title, JSONArray jsonArray,
         Collection<? extends BaseEntity> dataset, OutputStream out, String pattern) {

      HSSFWorkbook workbook = new HSSFWorkbook();

      HSSFSheet sheet = workbook.createSheet(title);

      sheet.setDefaultColumnWidth(20);

      HSSFCellStyle style = workbook.createCellStyle();
      HSSFPalette palette = workbook.getCustomPalette();  
      palette.setColorAtIndex((short) 63, (byte) (50), (byte) (126), (byte) (179));
      style.setFillForegroundColor((short) 63);
      style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
      style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
      style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
      style.setBorderRight(HSSFCellStyle.BORDER_THIN);
      style.setBorderTop(HSSFCellStyle.BORDER_THIN);
      style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
      HSSFFont font = workbook.createFont();
      font.setColor(HSSFColor.WHITE.index);
      font.setFontHeightInPoints((short) 12);
      font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
      style.setFont(font);
      style.setWrapText(false);  
            
      HSSFCellStyle style2 = workbook.createCellStyle();      
      style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
      style2.setFillForegroundColor(HSSFColor.WHITE.index);
      style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
      style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
      style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
      style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
      style2.setAlignment(HSSFCellStyle.ALIGN_LEFT);
      style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);     
      HSSFFont font2 = workbook.createFont();
      font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
      style2.setFont(font2);
      style2.setWrapText(true);  

      HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
      HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0, 0, 0, 0, (short) 4, 2, (short) 6, 5));
      comment.setString(new HSSFRichTextString("数据列表"));
      comment.setAuthor("yly");
 
      HSSFRow row = sheet.createRow(0);
      row.setHeight((short)500);
      //填充标题
      for (short i = 0; i < jsonArray.length(); i++) {
         HSSFCell cell = row.createCell(i);
         cell.setCellStyle(style);      
         JSONObject jsonObject = jsonArray.getJSONObject(i);
         HSSFRichTextString text = new HSSFRichTextString(jsonObject.getString("headerName"));
         cell.setCellValue(text);
      }
 
      Iterator it = dataset.iterator();
      int index = 0;
      //boolean ready = false;
      while (it.hasNext()) {
         index++;
         row = sheet.createRow(index);
         row.setHeight((short)350);
         BaseEntity t = (BaseEntity)it.next();
         //填充内容
         for (int i = 0; i < jsonArray.length(); i++) {
           JSONObject jsonObject = jsonArray.getJSONObject(i);
            HSSFCell cell = row.createCell(i);
    		cell.setCellStyle(style2);
            try {
                String textValue = getTextValue(jsonObject.getString("header"), t);
                if(textValue != null){
                   Pattern p = Pattern.compile("^//d+(//.//d+)?$");  
                   Matcher matcher = p.matcher(textValue);
                   if(matcher.matches()){
                      cell.setCellValue(Double.parseDouble(textValue));
                   }else{             	   
                      HSSFRichTextString richString = new HSSFRichTextString(textValue);
                      HSSFFont font3 = workbook.createFont();
                      font3.setColor(HSSFColor.BLACK.index);
                      richString.applyFont(font3);
                      cell.setCellValue(richString);
                   }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {

            }
         } 
         //if (index == dataset.size()) {
          //ready = true;
        //}
      }
      try {
        //if (ready) {
          workbook.write(out);
          workbook.close();
        //}

      } catch (IOException e) {
         e.printStackTrace();
      }
 
   }

   /**
    * 返回某个属性或者级联属性所对应的值
    * @return
    */
   private String getTextValue(String fieldName, BaseEntity t){
     String textValue = "";
     try {
       if (!fieldName.contains(".")) {//参数fieldName为单个属性，例如operator
         String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);//get方法，例如getOperator
         Class tCls = t.getClass();
         Method getMethod = tCls.getMethod(getMethodName, new Class[] {});
         Object object = getMethod.invoke(t, new Object[] {});
         if (object instanceof Date) {
           Date date = (Date) object;
           textValue = DateTimeUtils.getSimpleFormatString(DateTimeUtils.shortDateFormat, date);
         }else {
           textValue = object.toString();
         }
      }else{//参数fieldName为级联属性，例如elderlyInfo.name
        int point = fieldName.indexOf(".");//第一个点"."的位置
        String parentFieldName = fieldName.substring(0, point);//获取到第一个点之前的部分elderlyInfo
        String getMethodName = "get" + parentFieldName.substring(0, 1).toUpperCase() + parentFieldName.substring(1);//get方法，例如getElderlyInfo
        Class tCls = t.getClass();
        Method getMethod = tCls.getMethod(getMethodName, new Class[] {});
        BaseEntity object = (BaseEntity)getMethod.invoke(t, new Object[] {});
        t = object;
        fieldName = fieldName.substring(point + 1);//剩下的部分name
        textValue = getTextValue(fieldName, t);//递归调用
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
     return textValue;
   }

}