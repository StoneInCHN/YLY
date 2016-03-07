package com.yly.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yly.beans.ExportDataAttribute;
import com.yly.beans.Message;
import com.yly.controller.base.BaseController;
import com.yly.entity.ElderlyConsigner;
import com.yly.entity.ElderlyFamilyMembers;
import com.yly.entity.ElderlyInfo;
import com.yly.entity.commonenum.CommonEnum.IdentifierType;
import com.yly.service.ElderlyInfoService;
import com.yly.service.FileService;
import com.yly.service.IdentifierService;
import com.yly.service.SystemConfigService;
import com.yly.service.TenantAccountService;
import com.yly.utils.DateTimeUtils;
import com.yly.utils.ImportDataUtils;

/**
 * 导入数据 Controller
 * 
 */
@Controller("importDataController")
@RequestMapping("/console/importData")
public class ImportDataController extends BaseController {

  @Resource(name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;
  @Resource(name = "fileServiceImpl")
  private FileService fileService;
  @Resource(name = "elderlyInfoServiceImpl")
  private ElderlyInfoService elderlyInfoService;
  
  @Resource(name = "systemConfigServiceImpl")
  private SystemConfigService systemConfigService;
  
  @Resource(name = "identifierServiceImpl")
  private IdentifierService identifierService;
  
  /**
   * 下载excel模板
   * @throws IOException 
   */
  @RequestMapping(value = "/downloadTemplate", method = RequestMethod.GET)
  public void downloadTemplate(HttpServletResponse response, String fileName, HttpSession session) throws IOException {
          response.setContentType("octets/stream");
          // 导出文件名
          String filename =  fileName + "_" + DateTimeUtils.getSimpleFormatString(DateTimeUtils.filePostfixFormat, new Date());
          response.addHeader("Content-Disposition", "attachment;filename=" + filename + ".xls");
          //得到要下载的文件
          File file = new File(fileService.getRealPath("/resources/template/yly_elderly_info.xlsx"));
         //如果文件不存在
         if(file.exists()){

           //创建输出流
           OutputStream out = response.getOutputStream();
           
           InputStream is = new FileInputStream(fileService.getRealPath("/resources/template/yly_elderly_info.xlsx"));
           XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is); 
           
           //导入数据字典中的的配置信息到excel中
           ImportDataUtils importData = new ImportDataUtils();
           xssfWorkbook = importData.writeSysConfigToExcel(xssfWorkbook, systemConfigService);
                      
           xssfWorkbook.write(out);//将excel工作簿写入到输出流中
           //关闭输出流
           out.close();
         }
  }  
  
  
    /**
     * 导入数据页面
     */
    @RequestMapping(value = "/importData", method = RequestMethod.GET)
    public String importData(ModelMap model,  HttpSession session) {
      return "/importData/importData";
    }
    /**
     * 导入数据到数据库中
     */
    @RequestMapping(value = "/insertData", method = RequestMethod.POST)
    public @ResponseBody Message insertData(@RequestParam("excelFile") MultipartFile excelFile, HttpServletResponse response, HttpSession session) {
      if (excelFile == null) {
        return ERROR_MESSAGE;
      }
      Long currnetTenantId = tenantAccountService.getCurrentTenantID();//获取租户ID
      try {
        ImportDataUtils importData = new ImportDataUtils();
        Map<String,List<Map<String, Object>>> XSSFWorkbookSheetMap = importData.readExcelToMapList(excelFile);
                
        //config 的 sheet
        List<Map<String, Object>> configMapList = XSSFWorkbookSheetMap.get(ExportDataAttribute.CONFIG);
        Map<String, Map<String, Object>> configMaps = new HashMap<String, Map<String,Object>>();
        for (Map<String, Object> map : configMapList) {
          configMaps.put(map.get(ExportDataAttribute.ID).toString(), map);
        }
        //dynamic_config 的 sheet
        List<Map<String, Object>> dynamicConfigMapList = XSSFWorkbookSheetMap.get(ExportDataAttribute.DYNAMIC_CONFIG);
        Map<String, Map<String, Object>> dynamicConfigMaps = new HashMap<String, Map<String,Object>>();
        for (Map<String, Object> map : dynamicConfigMapList) {
          dynamicConfigMaps.put(map.get(ExportDataAttribute.ID).toString(), map);
        }
        //委托人 的 sheet
        List<Map<String, Object>> consignerMapList = XSSFWorkbookSheetMap.get(ExportDataAttribute.CONSIGNER);
        Map<String, ElderlyConsigner> consignerMaps = new HashMap<String, ElderlyConsigner>();
        for (int i = 0; i < consignerMapList.size(); i++) {
          Map<String, Object> consignerMap = consignerMapList.get(i);
          if(StringUtils.equals(consignerMap.get(ExportDataAttribute.IMPORT_STATUS).toString(), ExportDataAttribute.IMPORT_BEFORE) ||
              StringUtils.equals(consignerMap.get(ExportDataAttribute.IMPORT_STATUS).toString(), ExportDataAttribute.IMPORT_FAILED)){
            ElderlyConsigner elderlyConsigner = importData.constructConsigner(consignerMap, configMaps);
            elderlyConsigner.setTenantID(currnetTenantId);
            consignerMaps.put(consignerMap.get(ExportDataAttribute.ELDERLYINFO).toString(), elderlyConsigner);
          }
        }
        //家庭成员 的 sheet
        List<Map<String, Object>> familyMembersMapList = XSSFWorkbookSheetMap.get(ExportDataAttribute.FAMILY_MEMBERS);
        Map<String, List<ElderlyFamilyMembers>> familyMembersMaps = importData.constructFamilyMembers(familyMembersMapList, configMaps);
        
        //老人信息 的 sheet
        List<Map<String, Object>> elderlyInfoMapList = XSSFWorkbookSheetMap.get(ExportDataAttribute.ELDERLY_INFO);
        Map<String, ElderlyInfo> elderlyInfoMaps = new HashMap<String, ElderlyInfo>();
        for (int i = 0; i < elderlyInfoMapList.size(); i++) {
          Map<String, Object> elderlyInfomap = elderlyInfoMapList.get(i);
          if(StringUtils.equals(elderlyInfomap.get(ExportDataAttribute.IMPORT_STATUS).toString(), ExportDataAttribute.IMPORT_BEFORE) ||
              StringUtils.equals(elderlyInfomap.get(ExportDataAttribute.IMPORT_STATUS).toString(), ExportDataAttribute.IMPORT_FAILED)){
            ElderlyInfo elderlyInfo = importData.constructElderlyInfo(elderlyInfomap, configMaps, dynamicConfigMaps, systemConfigService);
            consignerMaps.get(elderlyInfomap.get(ExportDataAttribute.ID).toString()).setElderlyInfo(elderlyInfo);
            elderlyInfo.setElderlyConsigner(consignerMaps.get(elderlyInfomap.get(ExportDataAttribute.ID).toString()));
            elderlyInfo.setElderlyFamilyMembers(familyMembersMaps.get(elderlyInfomap.get(ExportDataAttribute.ID).toString()));
            elderlyInfo.setTenantID(currnetTenantId);
            elderlyInfo.setIdentifier(identifierService.generate(IdentifierType.ELDERLYINFO_IDENTIFIER));
            elderlyInfoMaps.put(elderlyInfomap.get(ExportDataAttribute.ID).toString(), elderlyInfo);
          }
        }
        Iterator iter = elderlyInfoMaps.entrySet().iterator();
        List<Map<String, String>> failedImportIDs_reason = new ArrayList<Map<String, String>>();
        List<String> successImportIDs = new ArrayList<String>();
        while (iter.hasNext()) {
          ElderlyInfo elderlyInfo = null;
          Object key = null;
          try {
            Map.Entry entry = (Map.Entry) iter.next();
            key = entry.getKey();
            elderlyInfo = (ElderlyInfo)entry.getValue();
            elderlyInfoService.save(elderlyInfo);
            successImportIDs.add(key.toString());
          } catch (Exception e) {
            if (key != null) {
              Map<String, String> failedId_reason = new HashMap<String, String>();
              failedId_reason.put(key.toString(), e.getCause().getCause().getMessage());
              failedImportIDs_reason.add(failedId_reason);
            }
          }
        }
        if (failedImportIDs_reason.size() > 0 || successImportIDs.size() > 0) {
          //返回Excel中将导入失败的标记为"导入失败"，将导入成功的标记为"导入成功"
          importData.markInExcel(response, failedImportIDs_reason, successImportIDs, excelFile);
        }
      } catch (Exception e) {
        e.printStackTrace();
        return ERROR_MESSAGE;
      }
      return SUCCESS_MESSAGE;
    }
       
}
