package com.yly.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yly.beans.Message;
import com.yly.beans.FileInfo.FileType;
import com.yly.controller.base.BaseController;
import com.yly.entity.ElderlyPhotoAlbum;
import com.yly.entity.ElderlyInfo;
import com.yly.entity.ElderlyPhotoes;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.service.ElderlyPhotoAlbumService;
import com.yly.service.ElderlyInfoService;
import com.yly.service.ElderlyPhotoesService;
import com.yly.service.FileService;
import com.yly.service.TenantAccountService;
import com.yly.utils.FieldFilterUtils;

/**
 * 老人相册controller
 * 
 * @author luzhang
 *
 */
@Controller("elderlyPhotoAlbumController")
@RequestMapping("/console/elderlyPhotoAlbum")
public class ElderlyPhotoAlbumController extends BaseController {

  @Resource(name = "elderlyPhotoAlbumServiceImpl")
  private ElderlyPhotoAlbumService elderlyPhotoAlbumService;

  @Resource(name = "elderlyInfoServiceImpl")
  private ElderlyInfoService elderlyInfoService;

  @Resource(name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;

  @Resource(name = "fileServiceImpl")
  private FileService fileService;

  @Resource(name = "elderlyPhotoesServiceImpl")
  private ElderlyPhotoesService elderlyPhotoesService;

  /**
   * 列表页面
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/elderlyPhotoAlbum", method = RequestMethod.GET)
  public String elderlyPhotoAlbum(ModelMap model) {
    return "/elderlyPhotoAlbum/elderlyPhotoAlbum";
  }

  /**
   * 加载相册
   * 
   * @param pageable
   * @return
   */
  @RequestMapping(value = "/loadAlbum", method = RequestMethod.GET)
  public @ResponseBody List<ElderlyPhotoAlbum> loadAlbum(ModelMap model,String keysOfElderlyName, String keysOfPhotoAlbumName) {
    List<ElderlyPhotoAlbum> elderlyPhotoAlbumList = null;   
    if (keysOfPhotoAlbumName != null) {//搜索照片名字关键字不为空
      elderlyPhotoAlbumList = elderlyPhotoAlbumService.searchByFilter(keysOfPhotoAlbumName, true);
    }else {
      elderlyPhotoAlbumList = elderlyPhotoAlbumService.findAll(true); 
    }
    
    if (keysOfElderlyName != null) {//搜索老人名字关键字不为空
      int count = 1;
      while (count <= elderlyPhotoAlbumList.size()) {
        ElderlyPhotoAlbum elderlyPhotoAlbum = elderlyPhotoAlbumList.get(count - 1);
        if (elderlyPhotoAlbum.getElderlyInfo() != null && StringUtils.isNotBlank(elderlyPhotoAlbum.getElderlyInfo().getName())){
           if (!elderlyPhotoAlbum.getElderlyInfo().getName().contains(keysOfElderlyName)) {//老人姓名不包含搜索关键字，从List中移除
             elderlyPhotoAlbumList.remove(count - 1);              
           }else{
             count ++;
           }  
        }
      }
    }
    return elderlyPhotoAlbumList;
  }
  
  

  /**
   * 查询list
   * 
   * @param pageable
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<ElderlyPhotoAlbum> list(ModelMap model, String keysOfElderlyName,
      String keysOfPhotoAlbumName, Pageable pageable) {
    if (keysOfElderlyName == null && keysOfPhotoAlbumName == null) {
      return elderlyPhotoAlbumService.findPage(pageable, true);
    } else {
      return elderlyPhotoAlbumService.searchPageByFilter(keysOfElderlyName, keysOfPhotoAlbumName,
          pageable);
    }
  }

  @RequestMapping(value = "/findAll", method = RequestMethod.POST)
  public @ResponseBody List<Map<String, Object>> findAll() {
    String[] propertys = {"id", "buildingName"};
    return FieldFilterUtils.filterCollectionMap(propertys, elderlyPhotoAlbumService.findAll(true));
  }


  /**
   * 编辑
   * 
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/detail", method = RequestMethod.GET)
  public String edit(ModelMap model, Long id, String handle) {
    if (id != null && handle != null) {
      model.addAttribute("elderlyPhotoAlbum", elderlyPhotoAlbumService.find(id));
      return "elderlyPhotoAlbum/" + handle;
    }
    return "";
  }
  @RequestMapping(value = "/uploadPhotos", method = RequestMethod.GET)
  public String uploadPhotos(ModelMap model) {
    List<ElderlyPhotoAlbum> elderlyPhotoAlbumList = elderlyPhotoAlbumService.findAll(true);
    if (elderlyPhotoAlbumList != null) {
//      Map<String,String> mapAlbumList = new HashMap<String,String>();  
//      for (ElderlyPhotoAlbum elderlyPhotoAlbum : elderlyPhotoAlbumList) {
//        mapAlbumList.put(elderlyPhotoAlbum.getId()+"", elderlyPhotoAlbum.getName());
//      }
      model.addAttribute("elderlyPhotoAlbumList", elderlyPhotoAlbumList);
      return "elderlyPhotoAlbum/uploadPhotos";
    }
    return "";
  }  
  /**
   * 相册公共搜索页面
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/albumSearch", method = RequestMethod.GET)
  public String albumSearch(ModelMap model) {
    return "/elderlyPhotoAlbum/albumSearch";
  }
  /**
   * 查看照片
   * 
   * @param model
   * @param photoAlbumID
   * @return
   */
  @RequestMapping(value = "/viewPhotos", method = RequestMethod.GET)
  public String index(ModelMap model, Long photoAlbumID) {
    ElderlyPhotoAlbum elderlyPhotoAlbum = elderlyPhotoAlbumService.find(photoAlbumID);
    model.addAttribute("albumName", elderlyPhotoAlbum.getName());
    model.addAttribute("albumRemark", elderlyPhotoAlbum.getRemark());
    model.addAttribute("elderlyPhotoes", elderlyPhotoAlbum.getElderlyPhotoes());
    return "elderlyPhotoAlbum/viewPhotos";
  }

  /**
   * 上传照片(disk)
   * 
   * @param file
   * @param identifier
   * @return
   */
  @RequestMapping(value = "/uploadAlbum", method = RequestMethod.POST)
  public @ResponseBody Message uploadAlbum(@RequestParam("file") MultipartFile file,
      String identifier, String albumName, String selectIdentifier, String selectAlbumName,String imgID) {
    Map<String, String> paramMap = new HashMap<String, String>();
    if (StringUtils.isNotBlank(identifier) && StringUtils.isNotBlank(albumName)) {//新建相册上传照片
      paramMap.put("identifier", identifier);
      paramMap.put("albumName", albumName);
    }else if(StringUtils.isNotBlank(selectIdentifier) && StringUtils.isNotBlank(selectAlbumName)) {//选择已经存在的相册上传照片
      paramMap.put("identifier", selectIdentifier);
      paramMap.put("albumName", selectAlbumName);
    }
    String filePath = fileService.upload(FileType.ALBUM, file, paramMap);
    if (filePath != null) {
      return Message.success(filePath);
    } else {
      return ERROR_MESSAGE;
    }
  }
  /**
   * 上传封面
   * 
   * @param file
   * @param identifier
   * @return
   */
  @RequestMapping(value = "/setAlbumCover", method = RequestMethod.POST)
  public @ResponseBody Message setAlbumCover(@RequestParam("file") MultipartFile file,Long coverID) {
    Map<String, String> paramMap = new HashMap<String, String>();
    if (coverID!=null) {
      ElderlyPhotoAlbum elderlyPhotoAlbum = elderlyPhotoAlbumService.find(coverID);
      if (elderlyPhotoAlbum != null && elderlyPhotoAlbum.getElderlyInfo() != null) {
        paramMap.put("identifier", elderlyPhotoAlbum.getElderlyInfo().getIdentifier());
        paramMap.put("albumName", elderlyPhotoAlbum.getName());
      }
    }
    String filePath = fileService.upload(FileType.ALBUM, file, paramMap);
    if (filePath != null) {
      return Message.success(filePath);
    } else {
      return ERROR_MESSAGE;
    }
  }
  /**
   * 保存封面
   * 
   * @param file
   * @param identifier
   * @return
   */
  @RequestMapping(value = "/saveAlbumCover", method = RequestMethod.POST)
  public @ResponseBody Message saveAlbumCover(String coverUrl,Long coverID) {
    if (coverUrl != null && coverID != null) {
      ElderlyPhotoAlbum elderlyPhotoAlbum = elderlyPhotoAlbumService.find(coverID);
      //改封面照片
      elderlyPhotoAlbum.setAlbumCover(coverUrl);
      //保存封面照片
      ElderlyPhotoes elderlyPhotoes = new ElderlyPhotoes();
      elderlyPhotoes.setUrl(coverUrl);
      elderlyPhotoes.setElderlyPhotoAlbum(elderlyPhotoAlbum);     
      elderlyPhotoes.setName(coverUrl.substring(coverUrl.lastIndexOf("/") + 1));
      elderlyPhotoesService.save(elderlyPhotoes);
      return SUCCESS_MESSAGE;
    }
      return ERROR_MESSAGE;      
  }
  /**
   * 添加相册及照片
   * @param elderlyPhotoAlbum
   * @param photoList 照片URL字符串集合，逗号区分
   * @param elderlyInfoID
   * @param selectAlbumID
   * @return
   */
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message save(ElderlyPhotoAlbum elderlyPhotoAlbum, String photoList,
      Long elderlyInfoID, Long selectAlbumID) {
    String[] photos = null;
    if (StringUtils.isNotBlank(photoList)) {
      photos = photoList.split(",");
    }
    ElderlyInfo elderlyInfo = elderlyInfoService.find(elderlyInfoID);
    /*新增相册并且添加照片*/
    if (elderlyInfo != null && elderlyPhotoAlbum != null && elderlyPhotoAlbum.getName() != null) {
      elderlyPhotoAlbum.setElderlyInfo(elderlyInfo);// 所属老人
      elderlyPhotoAlbum.setTenantID(tenantAccountService.getCurrentTenantID());// 所属老人院
      // 保存相册
      elderlyPhotoAlbumService.save(elderlyPhotoAlbum);
      if (photos != null) {
        // 依次保存相片
        for (int i = 0; i < photos.length; i++) {
          ElderlyPhotoes elderlyPhotoe = new ElderlyPhotoes();
          elderlyPhotoe.setUrl(photos[i]);
          elderlyPhotoe.setName(photos[i].substring(photos[i].lastIndexOf("/") + 1));
          elderlyPhotoe.setElderlyPhotoAlbum(elderlyPhotoAlbum);
          elderlyPhotoesService.save(elderlyPhotoe);
        }
      }
      return SUCCESS_MESSAGE;
    }
    /*选择已经存在的相册添加照片*/
    if ((elderlyPhotoAlbum != null && elderlyPhotoAlbum.getName() == null) && selectAlbumID !=null) {
      elderlyPhotoAlbum = elderlyPhotoAlbumService.find(selectAlbumID);
      if (elderlyPhotoAlbum != null) {
        // 依次保存相片
        for (int i = 0; i < photos.length; i++) {
          ElderlyPhotoes elderlyPhotoe = new ElderlyPhotoes();
          elderlyPhotoe.setUrl(photos[i]);
          elderlyPhotoe.setName(photos[i].substring(photos[i].lastIndexOf("/") + 1));
          elderlyPhotoe.setElderlyPhotoAlbum(elderlyPhotoAlbum);
          elderlyPhotoesService.save(elderlyPhotoe);
        }
      }
      return SUCCESS_MESSAGE;
    }
    return ERROR_MESSAGE;
  }

  /**
   * 更新
   * 
   * @param elderlyPhotoAlbum
   * @param elderlyInfoID
   * @return
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update(ElderlyPhotoAlbum elderlyPhotoAlbum, Long elderlyInfoID, String deletePhotoIDs) {
    ElderlyInfo elderlyInfo = elderlyInfoService.find(elderlyInfoID);
    if (elderlyInfo != null) {
      if (StringUtils.isNotBlank(deletePhotoIDs) && deletePhotoIDs.length() > 0) {
        deletePhotoIDs = deletePhotoIDs.substring(0, deletePhotoIDs.length()-1);//去掉最后的逗号
        String deletePhotoID[] = deletePhotoIDs.split(",");
        for (int i = 0; i < deletePhotoID.length; i++) {
          ElderlyPhotoes elderlyPhotoes = elderlyPhotoesService.find(new Long(deletePhotoID[i]));
          if (elderlyPhotoes != null) {
            if (StringUtils.equals(elderlyPhotoAlbum.getAlbumCover(), elderlyPhotoes.getUrl())) {//如果删除的照片巧合是封面照片
                //清空封面
                elderlyPhotoAlbum.setAlbumCover(null);
            }
            //删除照片(数据库)
            elderlyPhotoesService.delete(new Long(deletePhotoID[i]));
            //删除应用服务器上的照片(disk)
            String relativepath =  elderlyPhotoes.getUrl().substring(elderlyPhotoes.getUrl().indexOf("/upload"));
            try {
              fileService.deletefile(fileService.getRealPath(relativepath));
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
        } 
      }
      //更新相册
      elderlyPhotoAlbum.setElderlyInfo(elderlyInfo);
      elderlyPhotoAlbum.setTenantID(tenantAccountService.getCurrentTenantID());      
      elderlyPhotoAlbumService.update(elderlyPhotoAlbum, "createDate");
      return SUCCESS_MESSAGE;
    }
    return ERROR_MESSAGE;
  }



  /**
   * 删除
   * 
   * @param id
   * @return
   */
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete(Long id) {
    if (id != null) {
      ElderlyPhotoAlbum elderlyPhotoAlbum = elderlyPhotoAlbumService.find(id);
      if (elderlyPhotoAlbum != null) {
        Set<ElderlyPhotoes> elderlyPhotoesSet = elderlyPhotoAlbum.getElderlyPhotoes();
        String photoUrl = null; 
        if (elderlyPhotoesSet != null && elderlyPhotoesSet.size() > 0) {
          // 1.删相册下面的照片(数据库)
          for (ElderlyPhotoes elderlyPhotoes : elderlyPhotoesSet) {
            elderlyPhotoesService.remove(elderlyPhotoes);
            if(photoUrl == null) photoUrl = elderlyPhotoes.getUrl();
          }
        }
        // 2.删相册(数据库)
        elderlyPhotoAlbumService.delete(id);
        // 3.删除应用服务器上的照片(disk)
        try {
          String tenantOrgCode = tenantAccountService.getCurrentTenantOrgCode();
          String elderlyIdentifier =
              elderlyPhotoAlbum.getElderlyInfo() != null ? elderlyPhotoAlbum.getElderlyInfo()
                  .getIdentifier() : "";
          if (StringUtils.isNotBlank(tenantOrgCode) && StringUtils.isNotBlank(elderlyIdentifier)) {
            String albumPathFromDB = elderlyPhotoAlbum.getAlbumCover();
            if (albumPathFromDB == null && photoUrl != null) albumPathFromDB = photoUrl; //如果封面还没有自定义，则取照片的URL
            if (albumPathFromDB != null){      
                String relativepath =
                albumPathFromDB.substring(albumPathFromDB.indexOf("/upload"),
                    albumPathFromDB.lastIndexOf("/"));
                fileService.deletefile(fileService.getRealPath(relativepath));
            }
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
    return SUCCESS_MESSAGE;
  }
}
