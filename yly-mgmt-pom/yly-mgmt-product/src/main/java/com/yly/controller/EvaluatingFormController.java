package com.yly.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yly.beans.Message;
import com.yly.controller.base.BaseController;
import com.yly.entity.EvaluatingForm;
import com.yly.entity.ElderlyInfo;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.service.EvaluatingFormService;
import com.yly.service.ElderlyInfoService;
import com.yly.service.TenantAccountService;
import com.yly.utils.FieldFilterUtils;

/**
 * 评估表controller
 * 
 * @author luzhang
 *
 */
@Controller("evaluatingFormController")
@RequestMapping("/console/evaluatingForm")
public class EvaluatingFormController extends BaseController {

  @Resource(name = "evaluatingFormServiceImpl")
  private EvaluatingFormService evaluatingFormService;

  @Resource(name = "elderlyInfoServiceImpl")
  private ElderlyInfoService elderlyInfoService;

  @Resource(name = "tenantAccountServiceImpl")
  private TenantAccountService tenantAccountService;

  /**
   * 列表页面
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/evaluatingForm", method = RequestMethod.GET)
  public String evaluatingForm(ModelMap model) {
    return "/evaluatingForm/evaluatingForm";
  }

  /**
   * 查询list
   * 
   * @param pageable
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<EvaluatingForm> list(String keysOfElderlyName, Date beginDate,
      Date endDate, Pageable pageable) {
    Page<EvaluatingForm> evaluatingFormPage = null;
    if (keysOfElderlyName == null && beginDate == null && endDate == null) {
      evaluatingFormPage = evaluatingFormService.findPage(pageable, true);
    } else {
      evaluatingFormPage = evaluatingFormService.searchPageByFilter(keysOfElderlyName, beginDate, endDate,
          pageable);
    }
    return evaluatingFormPage;
  }

  @RequestMapping(value = "/findAll", method = RequestMethod.POST)
  public @ResponseBody List<Map<String, Object>> findAll() {
    String[] propertys = {"id", "buildingName"};
    return FieldFilterUtils.filterCollectionMap(propertys, evaluatingFormService.findAll(true));
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
      model.addAttribute("evaluatingForm", evaluatingFormService.find(id));
      return "evaluatingForm/" + handle;
    }
    return "";
  }

  /**
   * 添加
   * 
   * @param evaluatingForm
   * @return
   */
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message save(EvaluatingForm evaluatingForm, Long elderlyInfoID) {
    ElderlyInfo elderlyInfo = elderlyInfoService.find(elderlyInfoID);
    if (elderlyInfo != null && evaluatingForm != null) {
      evaluatingForm.setTenantID(tenantAccountService.getCurrentTenantID());
      if (evaluatingForm.getEvaluatingFormStatus() != null) {
        evaluatingFormService.save(evaluatingForm);
        return SUCCESS_MESSAGE;
      }
    }
    return ERROR_MESSAGE;
  }

  /**
   * 更新
   * 
   * @param evaluatingForm
   * @param elderlyInfoID
   * @return
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update(EvaluatingForm evaluatingForm, Long elderlyInfoID) {
    ElderlyInfo elderlyInfo = elderlyInfoService.find(elderlyInfoID);
    if (elderlyInfo != null) {
      evaluatingForm.setTenantID(tenantAccountService.getCurrentTenantID());
      evaluatingFormService.update(evaluatingForm, "createDate");
      return SUCCESS_MESSAGE;
    }
    return ERROR_MESSAGE;
  }



  /**
   * 删除
   * 
   * @param ids
   * @return
   */
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete(Long[] ids) {
    if (ids != null) {
      for (Long id : ids) {
        EvaluatingForm evaluatingForm = evaluatingFormService.find(id);
        if (evaluatingForm != null) {
          evaluatingFormService.delete(ids);
        }
      }
    }
    return SUCCESS_MESSAGE;
  }
}
