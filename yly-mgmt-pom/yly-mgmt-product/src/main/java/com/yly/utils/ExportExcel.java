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
 * 导出数据到excel
 * @author luzhang
 */
public class ExportExcel extends Thread {
    
    private String title;
    private JSONArray jsonArray;
    private List<Map<String, String>> eventRecordMapList;
    private OutputStream out;
    public ExportExcel(String title, JSONArray jsonArray, List<Map<String, String>> eventRecordMapList, OutputStream out){
        this.title = title;
        this.jsonArray = jsonArray;
        this.eventRecordMapList = eventRecordMapList;
        this.out = out;
    }
   @Override
   public void run() {
     exportExcel(title, jsonArray, eventRecordMapList, out);
   }
   
   public void exportExcel(String title, JSONArray jsonArray, List<Map<String, String>> eventRecordMapList, OutputStream out) {
      //创建一个excel工作簿
      HSSFWorkbook workbook = new HSSFWorkbook();
      HSSFSheet sheet = workbook.createSheet(title);
      //excel列默认宽度
      sheet.setDefaultColumnWidth(20);
      
      //第一行标题样式（白字蓝底）
      HSSFCellStyle titleStyle = workbook.createCellStyle();
      HSSFPalette palette = workbook.getCustomPalette();  
      palette.setColorAtIndex((short) 63, (byte) (50), (byte) (126), (byte) (179));
      titleStyle.setFillForegroundColor((short) 63);
      titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
      titleStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
      titleStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
      titleStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
      titleStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
      titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
      HSSFFont font = workbook.createFont();
      font.setColor(HSSFColor.WHITE.index);
      font.setFontHeightInPoints((short) 12);
      font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
      titleStyle.setFont(font);
      titleStyle.setWrapText(false);  
      
      //内容行样式   （白底黑字）
      HSSFCellStyle contentStyle = workbook.createCellStyle();      
      contentStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
      contentStyle.setFillForegroundColor(HSSFColor.WHITE.index);
      contentStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
      contentStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
      contentStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
      contentStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
      contentStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
      contentStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);     
      HSSFFont font2 = workbook.createFont();
      font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
      contentStyle.setFont(font2);
      contentStyle.setWrapText(true);  

      HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
      HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0, 0, 0, 0, (short) 4, 2, (short) 6, 5));
      comment.setString(new HSSFRichTextString("数据列表"));
      comment.setAuthor("yly");
      
      //填充标题,就是第一行啦
      HSSFRow row = sheet.createRow(0);
      row.setHeight((short)500);
      for (short i = 0; i < jsonArray.length(); i++) {
         HSSFCell cell = row.createCell(i);
         cell.setCellStyle(titleStyle);      
         JSONObject jsonObject = jsonArray.getJSONObject(i);
         HSSFRichTextString text = new HSSFRichTextString(jsonObject.getString("headerName"));
         cell.setCellValue(text);
      }
      
      //填充内容行，就是除第一行外的所有行，从第二行开始
      for (int i = 0; i < eventRecordMapList.size(); i++) {
        row = sheet.createRow(i+1);
        row.setHeight((short)350);
        Map<String, String> eventRecordMap = eventRecordMapList.get(i);
        for (int j = 0; j < jsonArray.length(); j++) {
          JSONObject jsonObject = jsonArray.getJSONObject(j);
           HSSFCell cell = row.createCell(j);
           cell.setCellStyle(contentStyle);
           try {
               String textValue = eventRecordMap.get(jsonObject.getString("header"));
               if(textValue != null){
                  Pattern p = Pattern.compile("^//d+(//.//d+)?$"); //匹配是否是数值类型
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
     
      }
      try {
          workbook.write(out);//将excel工作簿写入到输出流中
          workbook.close();//关闭

      } catch (IOException e) {
         e.printStackTrace();
      }
 
   }
}