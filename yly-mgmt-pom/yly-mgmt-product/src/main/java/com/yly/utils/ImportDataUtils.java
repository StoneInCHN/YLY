package com.yly.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.bcel.generic.NEW;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFDataFormatter;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.yly.beans.ExportDataAttribute;
import com.yly.entity.ElderlyConsigner;
import com.yly.entity.ElderlyFamilyMembers;
import com.yly.entity.ElderlyInfo;
import com.yly.entity.SystemConfig;
import com.yly.entity.commonenum.CommonEnum.ConfigKey;
import com.yly.entity.commonenum.CommonEnum.DeleteStatus;
import com.yly.entity.commonenum.CommonEnum.EducationLevel;
import com.yly.entity.commonenum.CommonEnum.ElderlyStatus;
import com.yly.entity.commonenum.CommonEnum.Gender;
import com.yly.entity.commonenum.CommonEnum.HousingInfo;
import com.yly.entity.commonenum.CommonEnum.LivingState;
import com.yly.entity.commonenum.CommonEnum.MarriageState;
import com.yly.entity.commonenum.CommonEnum.MedicalExpPaymentWay;
import com.yly.entity.commonenum.CommonEnum.PaymentWay;
import com.yly.entity.commonenum.CommonEnum.PoliticalOutlook;
import com.yly.entity.commonenum.CommonEnum.Relation;
import com.yly.entity.commonenum.CommonEnum.Religion;
import com.yly.entity.commonenum.CommonEnum.SourceOfIncome;
import com.yly.framework.ordering.Ordering.Direction;
import com.yly.service.SystemConfigService;

public class ImportDataUtils {
 
  
  public Map<String,List<Map<String, Object>>> readExcelToMapList(MultipartFile excelFile) throws IOException {
    
    Map<String,List<Map<String, Object>>> sheetMap = new HashMap<String, List<Map<String,Object>>>();
    InputStream is = excelFile.getInputStream();
    XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
    for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
      XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
      String sheetName = xssfWorkbook.getSheetName(numSheet);
      if (xssfSheet == null) {
        continue;
      }
      List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
      if (sheetName.equalsIgnoreCase(ExportDataAttribute.ELDERLY_INFO) || 
          sheetName.equalsIgnoreCase(ExportDataAttribute.CONSIGNER) ||
          sheetName.equalsIgnoreCase(ExportDataAttribute.FAMILY_MEMBERS)) {// 老人信息 ，委托人，家庭成员Sheet
        // 第一行，标题行
        XSSFRow headerRow = xssfSheet.getRow(0);
        String[] headers = new String[headerRow.getLastCellNum()];
        for (int i = 0; i < headers.length; i++) {
          XSSFCell hederCell = headerRow.getCell(i);
          if (hederCell != null) {
            if (hederCell.getCellComment() != null) {
              headers[i] = hederCell.getCellComment().getString().toString();
            } else {
              return sheetMap;
            }
          }
        }
        // 其他行，内容行
        for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
          Map<String, Object> map = new HashMap<String, Object>();
          XSSFRow xssfRow = xssfSheet.getRow(rowNum);
          if (xssfRow != null) {
            for (int i = 0; i < headers.length; i++) {
              map.put(headers[i], getValue(xssfRow.getCell(i)));
            }
          }
          mapList.add(map);
        }
        sheetMap.put(sheetName, mapList);
      }
      if (sheetName.equalsIgnoreCase(ExportDataAttribute.CONFIG) || 
          sheetName.equalsIgnoreCase(ExportDataAttribute.DYNAMIC_CONFIG)){//config 和 dynamic_config 的 sheet
        for (int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum+=2) {
          Map<String, Object> map = new HashMap<String, Object>();
          XSSFRow xssfRow = xssfSheet.getRow(rowNum);
          XSSFRow xssfRow_next = xssfSheet.getRow(rowNum + 1);
          if (xssfRow != null) {
            map.put("id", sheetName.equalsIgnoreCase(ExportDataAttribute.CONFIG)?xssfRow_next.getCell(0):xssfRow.getCell(0));//比如gender
            for (int i = 1; i < xssfRow.getLastCellNum(); i++) {
              map.put(xssfRow.getCell(i).toString(), xssfRow_next.getCell(i));
            }
          }
          mapList.add(map);
        }
      }
      sheetMap.put(sheetName, mapList);
    }
    return sheetMap;
  }
  public XSSFWorkbook writeSysConfigToExcel(XSSFWorkbook xssfWorkbook, SystemConfigService systemConfigService) throws IOException {
    for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
      XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
      String sheetName = xssfWorkbook.getSheetName(numSheet);
      if (xssfSheet == null) {
        continue;
      }
      if (sheetName.equalsIgnoreCase(ExportDataAttribute.DYNAMIC_CONFIG)) {//system config 动态配置Sheet
        // 其他行，内容行
        for (int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum+=2) {
          XSSFRow xssfRow = xssfSheet.getRow(rowNum);
          XSSFRow xssfRow_next = xssfSheet.createRow(rowNum + 1);
          if (xssfRow != null) {
            if (StringUtils.equalsIgnoreCase(ExportDataAttribute.MEAL_TYPE, xssfRow.getCell(0).getStringCellValue())) {
              populateConfigValue_id(systemConfigService, ConfigKey.MEALTYPE, xssfRow, xssfRow_next);
            }
            if (StringUtils.equalsIgnoreCase(ExportDataAttribute.NURSING_LEVEL, xssfRow.getCell(0).getStringCellValue())) {
              populateConfigValue_id(systemConfigService, ConfigKey.NURSELEVEL, xssfRow, xssfRow_next);
            }
            if (StringUtils.equalsIgnoreCase(ExportDataAttribute.PERSONNEL_CATEGORY, xssfRow.getCell(0).getStringCellValue())) {
              populateConfigValue_id(systemConfigService, ConfigKey.PERSONNELCATEGORY, xssfRow, xssfRow_next);
            }
          }
        }
      }
    }
    return xssfWorkbook;
  }
  private void populateConfigValue_id(SystemConfigService systemConfigService, ConfigKey configKey, XSSFRow xssfRow, XSSFRow xssfRow_next){
    List<Map<String, Object>> maps = systemConfigService.findByConfigKey(configKey, Direction.asc);
    for (int i = 0; i < maps.size(); i++) {
      Map<String, Object> map = maps.get(i);
      xssfRow.createCell(i + 1).setCellValue(map.get("configValue").toString());
      xssfRow_next.createCell(i + 1).setCellValue(map.get("id").toString());
    }
  }
  private Object getValue(XSSFCell xssfCell) {
    if (xssfCell == null) {
      return "";
    }
    if (xssfCell.getCellType() == xssfCell.CELL_TYPE_BOOLEAN) {
      // 返回布尔类型的值
      return xssfCell.getBooleanCellValue();
    } else if (xssfCell.getCellType() == xssfCell.CELL_TYPE_NUMERIC) {
      // 返回数值类型的值,日期类型
      if (HSSFDateUtil.isCellDateFormatted(xssfCell)) {
        return xssfCell.getDateCellValue();
      }
      HSSFDataFormatter dataFormatter = new HSSFDataFormatter();
      String cellFormatted = dataFormatter.formatCellValue(xssfCell);
      return cellFormatted;
    } else {
       //返回字符串类型的值
      return String.valueOf(xssfCell.getStringCellValue());
    }
  }
  public ElderlyInfo constructElderlyInfo(Map<String, Object> elderlyInfomap,
      Map<String, Map<String, Object>> configMaps, Map<String, Map<String, Object>> dynamicConfigMaps, SystemConfigService systemConfigService) {
    ElderlyInfo elderlyInfo = new ElderlyInfo();

    elderlyInfo.setName(elderlyInfomap.get(ExportDataAttribute.NAME).toString());
    elderlyInfo.setIDCard(elderlyInfomap.get(ExportDataAttribute.IDCARD).toString());
    elderlyInfo.setElderlyPhoneNumber(elderlyInfomap.get(ExportDataAttribute.ELDERLY_PHONE_NUMBER).toString());
    elderlyInfo.setAge(Integer.parseInt(elderlyInfomap.get(ExportDataAttribute.AGE).toString()));
    elderlyInfo.setGender(Gender.valueOf(
        configMaps.get(ExportDataAttribute.GENDER).get(elderlyInfomap.get(ExportDataAttribute.GENDER)).toString()));
    Object in_hospitalized_date = elderlyInfomap.get(ExportDataAttribute.BE_HOSPITALIZED_DATE);
    if (in_hospitalized_date != null) {
      if (in_hospitalized_date instanceof Date) {
        elderlyInfo.setBeHospitalizedDate((Date)in_hospitalized_date);
      }
    }
    Object out_hospitalized_date = elderlyInfomap.get(ExportDataAttribute.OUT_HOSPITALIZED_DATE);
    if (out_hospitalized_date != null) {
      if (out_hospitalized_date instanceof Date) {
        elderlyInfo.setOutHospitalizedDate((Date)out_hospitalized_date);
      }
    }
    elderlyInfo.setNation(elderlyInfomap.get(ExportDataAttribute.NATION).toString());
    elderlyInfo.setPlaceOfOrigin(elderlyInfomap.get(ExportDataAttribute.PLACE_OF_ORIGIN).toString());
    Object birthday = elderlyInfomap.get(ExportDataAttribute.BIRTHDAY);
    if (birthday != null) {
      if (birthday instanceof Date) {
        elderlyInfo.setBirthday((Date)birthday);
      }
    }
    elderlyInfo.setDeleteStatus(DeleteStatus.valueOf(
        configMaps.get(ExportDataAttribute.DELETE_STATUS).get(elderlyInfomap.get(ExportDataAttribute.DELETE_STATUS)).toString()));
    elderlyInfo.setElderlyStatus(ElderlyStatus.valueOf(
        configMaps.get(ExportDataAttribute.ELDERLY_STATUS).get(elderlyInfomap.get(ExportDataAttribute.ELDERLY_STATUS)).toString()));
    elderlyInfo.setEducationLevel(EducationLevel.valueOf(
        configMaps.get(ExportDataAttribute.EDUCATION_LEVEL).get(elderlyInfomap.get(ExportDataAttribute.EDUCATION_LEVEL)).toString()));
    elderlyInfo.setPoliticalOutlook(PoliticalOutlook.valueOf(
        configMaps.get(ExportDataAttribute.POLITICAL_OUTLOOK).get(elderlyInfomap.get(ExportDataAttribute.POLITICAL_OUTLOOK)).toString()));
    elderlyInfo.setReligion(Religion.valueOf(
        configMaps.get(ExportDataAttribute.RELIGION).get(elderlyInfomap.get(ExportDataAttribute.RELIGION)).toString()));
    elderlyInfo.setHobbies(elderlyInfomap.get(ExportDataAttribute.HOBBIES).toString());
    elderlyInfo.setPersonalHabits(elderlyInfomap.get(ExportDataAttribute.PERSONAL_HABITS).toString());
    elderlyInfo.setHonors(elderlyInfomap.get(ExportDataAttribute.HONORS).toString());
    elderlyInfo.setHousingInfo(HousingInfo.valueOf(
        configMaps.get(ExportDataAttribute.HOUSING_INFO).get(elderlyInfomap.get(ExportDataAttribute.HOUSING_INFO)).toString()));
    elderlyInfo.setLivingState(LivingState.valueOf(
        configMaps.get(ExportDataAttribute.LIVING_STATE).get(elderlyInfomap.get(ExportDataAttribute.LIVING_STATE)).toString()));
    elderlyInfo.setMarriageState(MarriageState.valueOf(
        configMaps.get(ExportDataAttribute.MARRIAGE_STATE).get(elderlyInfomap.get(ExportDataAttribute.MARRIAGE_STATE)).toString()));
    elderlyInfo.setMedicalExpPaymentWay(MedicalExpPaymentWay.valueOf(
        configMaps.get(ExportDataAttribute.MEDICAL_EXP_PAYMENT_WAY).get(elderlyInfomap.get(ExportDataAttribute.MEDICAL_EXP_PAYMENT_WAY)).toString()));
    elderlyInfo.setMonthlyIncome(new BigDecimal(elderlyInfomap.get(ExportDataAttribute.MONTHLY_INCOME).toString()));
    elderlyInfo.setSourceOfIncome(SourceOfIncome.valueOf(
        configMaps.get(ExportDataAttribute.SOURCE_OF_INCOME).get(elderlyInfomap.get(ExportDataAttribute.SOURCE_OF_INCOME)).toString()));
    elderlyInfo.setPaymentWay(PaymentWay.valueOf(
        configMaps.get(ExportDataAttribute.PAYMENT_WAY).get(elderlyInfomap.get(ExportDataAttribute.PAYMENT_WAY)).toString()));
    elderlyInfo.setSocialInsuranceNumber(elderlyInfomap.get(ExportDataAttribute.SOCIAL_INSURANCE_NUMBER).toString());
    elderlyInfo.setOriginalCompany(elderlyInfomap.get(ExportDataAttribute.ORIGINAL_COMPANY).toString());
    elderlyInfo.setPosition(elderlyInfomap.get(ExportDataAttribute.POSITION).toString());
    elderlyInfo.setRegisteredResidence(elderlyInfomap.get(ExportDataAttribute.REGISTERED_RESIDENCE).toString());
    elderlyInfo.setResidentialAddress(elderlyInfomap.get(ExportDataAttribute.RESIDENTIAL_ADDRESS).toString());
    
    Object meal_type = elderlyInfomap.get(ExportDataAttribute.MEAL_TYPE);
    if (meal_type != null && meal_type instanceof String && StringUtils.isNotBlank(meal_type.toString())) {
      Long mealTypeID = new Long(dynamicConfigMaps.get(ExportDataAttribute.MEAL_TYPE).get(meal_type).toString()); 
      SystemConfig mealType = systemConfigService.find(mealTypeID); 
      elderlyInfo.setMealType(mealType != null ? mealType : null);
    }

    Object nursing_level = elderlyInfomap.get(ExportDataAttribute.NURSING_LEVEL);
    if (nursing_level != null  && nursing_level instanceof String && StringUtils.isNotBlank(nursing_level.toString())) {
      Long nursingLevelID = new Long(dynamicConfigMaps.get(ExportDataAttribute.NURSING_LEVEL).get(nursing_level).toString()); 
      SystemConfig nursingLevel = systemConfigService.find(nursingLevelID); 
      elderlyInfo.setNursingLevel(nursingLevel != null ? nursingLevel : null);
    }
    
    Object personnel_category = elderlyInfomap.get(ExportDataAttribute.PERSONNEL_CATEGORY);
    if (personnel_category != null  && personnel_category instanceof String && StringUtils.isNotBlank(personnel_category.toString())) {
      Long personnelCategoryID = new Long(dynamicConfigMaps.get(ExportDataAttribute.PERSONNEL_CATEGORY).get(personnel_category).toString()); 
      SystemConfig personnelCategory = systemConfigService.find(personnelCategoryID); 
      elderlyInfo.setPersonnelCategory(personnelCategory != null ? personnelCategory : null); 
    }
                  
    
    return elderlyInfo;
  }
  public ElderlyConsigner constructConsigner(Map<String, Object> consignerMap,
      Map<String, Map<String, Object>> configMaps) {
    ElderlyConsigner elderlyConsigner = new ElderlyConsigner();
    elderlyConsigner.setCompanyAddress(consignerMap.get(ExportDataAttribute.COMPANY_ADDRESS).toString());
    elderlyConsigner.setConsignerName(consignerMap.get(ExportDataAttribute.CONSIGNER_NAME).toString());
    elderlyConsigner.setConsignerPhoneNumber(consignerMap.get(ExportDataAttribute.CONSIGNER_PHONE_NUMBER).toString());
    elderlyConsigner.setConsignerRelation(Relation.valueOf(
        configMaps.get(ExportDataAttribute.RELATION).get(consignerMap.get(ExportDataAttribute.CONSIGNER_RELATION)).toString()));
    elderlyConsigner.setIsSameCity(configMaps.get(ExportDataAttribute.YES_NO).get(consignerMap.get(ExportDataAttribute.IS_SAME_CITY)).toString().
        equalsIgnoreCase(ExportDataAttribute.YES) ? true :false);
    return elderlyConsigner;
  }
  public Map<String, List<ElderlyFamilyMembers>> constructFamilyMembers(
      List<Map<String, Object>> familyMembersMapList, Map<String, Map<String, Object>> configMaps) {
    Map<String, List<ElderlyFamilyMembers>> familyMembersMaps = new HashMap<String, List<ElderlyFamilyMembers>>();
    for (int i = 0; i < familyMembersMapList.size(); i++) {
      Map<String, Object> familyMembersMap = familyMembersMapList.get(i);
      if(StringUtils.equals(familyMembersMap.get(ExportDataAttribute.IMPORT_STATUS).toString(), ExportDataAttribute.IMPORT_BEFORE) ||
          StringUtils.equals(familyMembersMap.get(ExportDataAttribute.IMPORT_STATUS).toString(), ExportDataAttribute.IMPORT_FAILED)){
        ElderlyFamilyMembers familyMembers = new ElderlyFamilyMembers();
        familyMembers.setMemberName(familyMembersMap.get(ExportDataAttribute.MEMBER_NAME).toString());
        familyMembers.setMemberPhoneNumber(familyMembersMap.get(ExportDataAttribute.MEMBER_PHONE_NUMBER).toString());
        familyMembers.setMemberResidentialAddress(familyMembersMap.get(ExportDataAttribute.MEMBER_RESIDENTIAL_ADDRESS).toString());
        familyMembers.setMemberRelation(Relation.valueOf(
            configMaps.get(ExportDataAttribute.RELATION).get(familyMembersMap.get(ExportDataAttribute.MEMBER_RELATION)).toString()));
        
        List<ElderlyFamilyMembers> familyMemberList = null;
        String ID = familyMembersMap.get(ExportDataAttribute.ELDERLYINFO).toString();
        if (familyMembersMaps.get(ID) == null) {
          familyMemberList = new ArrayList<ElderlyFamilyMembers>();
        }else{
          familyMemberList = familyMembersMaps.get(ID);
        }
        familyMemberList.add(familyMembers);
        familyMembersMaps.put(ID, familyMemberList);
      }
    }
    return familyMembersMaps;
  }
  public void markInExcel(HttpServletResponse response, List<Map<String, String>> failedImportIDs_reason,
      List<String> successImportIDs, MultipartFile excelFile) throws IOException {
    InputStream is = excelFile.getInputStream();
    XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
    for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
      XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
      String sheetName = xssfWorkbook.getSheetName(numSheet);
      if (xssfSheet == null) {
        continue;
      }
      if (sheetName.equalsIgnoreCase(ExportDataAttribute.ELDERLY_INFO) || 
          sheetName.equalsIgnoreCase(ExportDataAttribute.CONSIGNER) ||
          sheetName.equalsIgnoreCase(ExportDataAttribute.FAMILY_MEMBERS)) {// 老人信息 ，委托人，家庭成员Sheet
        // 第一行，标题行
        XSSFRow headerRow = xssfSheet.getRow(0);
        Map<String, String> column_title = new HashMap<String, String>();
        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
          XSSFCell hederCell = headerRow.getCell(i);
          if (hederCell != null) {
            if (hederCell.getCellComment() != null) {
              column_title.put(hederCell.getCellComment().getString().toString(), hederCell.getStringCellValue());
            }
          }
        }
        for (int i = 0; i < failedImportIDs_reason.size(); i++) {//失败的
          Map<String, String> failedId_reason = failedImportIDs_reason.get(i);
          String rowID = null;
          String reason = null;
          for (String key : failedId_reason.keySet()) {  
            rowID = key; 
            break;
          } 
          for (String value : failedId_reason.values()) {  
            reason = value; 
            break;
          } 
          if (rowID != null) {
            if (sheetName.equalsIgnoreCase(ExportDataAttribute.FAMILY_MEMBERS)) {
              family_member_loop: for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                Map<String, Object> map = new HashMap<String, Object>();
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                if (xssfRow != null && xssfRow.getCell(1) != null) {
                  if (StringUtils.equals(rowID, getValue(xssfRow.getCell(1)).toString())) {
                    markRedReason(xssfRow, reason, xssfWorkbook, xssfSheet, column_title);
                    while (xssfSheet.getRow(++rowNum) != null && 
                        StringUtils.equals(rowID, getValue(xssfSheet.getRow(++rowNum).getCell(1)).toString())) {
                      markRedReason(xssfSheet.getRow(rowNum), reason, xssfWorkbook, xssfSheet, column_title);
                      if (rowNum >= xssfSheet.getLastRowNum()) {
                        break family_member_loop;
                      }
                    }
                    break family_member_loop;
                  }
                }
              }
            }else {
              XSSFRow row = xssfSheet.getRow(Integer.parseInt(rowID));
              markRedReason(row, reason, xssfWorkbook, xssfSheet, column_title);
            }
          }
        }
        for (int i = 0; i < successImportIDs.size(); i++) {//成功的
          if (sheetName.equalsIgnoreCase(ExportDataAttribute.FAMILY_MEMBERS)) {
            family_member_loop: for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
              Map<String, Object> map = new HashMap<String, Object>();
              XSSFRow xssfRow = xssfSheet.getRow(rowNum);
              if (xssfRow != null && xssfRow.getCell(1) != null) {
                if (StringUtils.equals(successImportIDs.get(i), getValue(xssfRow.getCell(1)).toString())) {
                  markSuccess(xssfRow, xssfWorkbook);
                  while (xssfSheet.getRow(++rowNum) != null && 
                      StringUtils.equals(successImportIDs.get(i), getValue(xssfSheet.getRow(++rowNum).getCell(1)).toString())) {
                    markSuccess(xssfSheet.getRow(rowNum), xssfWorkbook);
                    if (rowNum >= xssfSheet.getLastRowNum()) {
                      break family_member_loop;
                    }
                  }
                  break family_member_loop;
                }
              }
            }
          }else{
            XSSFRow row = xssfSheet.getRow(Integer.parseInt(successImportIDs.get(i)));
            markSuccess(row, xssfWorkbook);
          }
        }
      }
    }
    response.setContentType("octets/stream");
    // 导出文件名
    String filename =  "ImportFeedback" + DateTimeUtils.getSimpleFormatString(DateTimeUtils.filePostfixFormat, new Date());
    response.addHeader("Content-Disposition", "attachment;filename=" + filename + ".xls");
    //创建输出流
    OutputStream out = response.getOutputStream();
    xssfWorkbook.write(out);//将excel工作簿写入到输出流中
    //关闭输出流
    out.close();
  }
  private void markSuccess(XSSFRow row, XSSFWorkbook xssfWorkbook) {
    XSSFCell cell = row.getCell(0);
    if (cell == null) {
      cell = row.createCell(0);
    }
    cell.setCellValue(ExportDataAttribute.IMPORT_SUCCESS);
    XSSFCellStyle failedStyle = xssfWorkbook.createCellStyle();      
    failedStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
    failedStyle.setFillForegroundColor(HSSFColor.WHITE.index);
    cell.setCellStyle(failedStyle);
  }
  private void markRedReason(XSSFRow row, String reason, XSSFWorkbook xssfWorkbook, XSSFSheet xssfSheet, Map<String, String> column_title) {
    XSSFCell cell = row.getCell(0);
    if (cell == null) {
      cell = row.createCell(0);
    }
    cell.setCellValue(ExportDataAttribute.IMPORT_FAILED);
    XSSFCellStyle failedStyle = xssfWorkbook.createCellStyle();      
    failedStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
    failedStyle.setFillForegroundColor(HSSFColor.RED.index);
    cell.setCellStyle(failedStyle);
    if (reason != null) {
      reason = packReason(reason, column_title);
      if (reason != null) {
        XSSFDrawing patr = xssfSheet.createDrawingPatriarch();
        ClientAnchor anchor = new XSSFClientAnchor();
        XSSFComment comment = patr.createCellComment(anchor);
        comment.setString(reason);
        cell.setCellComment(comment);
      }
    }
    
  }
  private String packReason(String reason, Map<String, String> column_title) {
    String column = "";
    String title = "";
    if (reason.indexOf("'") != -1) {
      String leftPart = reason.substring(reason.indexOf("'") + 1);
      if (leftPart.indexOf("'") != -1) {
        column = leftPart.substring(0, leftPart.indexOf("'"));
      }
      if (column_title.get(column) != null) {
        title = column_title.get(column);
      }else {
        return null;
      }
      if(reason.replace(column, ExportDataAttribute.PLACEHOLDER).indexOf(ExportDataAttribute.IMPORT_ERROR1) != -1){
        return ExportDataAttribute.IMPORT_ERROR_DATA_TOO_LONG.replace(ExportDataAttribute.PLACEHOLDER, title);
      }
      return ExportDataAttribute.IMPORT_ERROR_INPUT_INVALID.replace(ExportDataAttribute.PLACEHOLDER, title);
    }
    return null;
  }
}
