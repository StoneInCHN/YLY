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
import com.yly.common.log.LogUtil;
import com.yly.controller.base.BaseController;
import com.yly.entity.AdvanceCharge;
import com.yly.entity.ElderlyInfo;
import com.yly.entity.commonenum.CommonEnum.BudgetType;
import com.yly.entity.commonenum.CommonEnum.DeleteStatus;
import com.yly.entity.commonenum.CommonEnum.ElderlyStatus;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.json.request.ChargeSearchRequest;
import com.yly.service.AdvanceChargeService;
import com.yly.service.ElderlyInfoService;
import com.yly.service.TenantAccountService;
import com.yly.utils.FieldFilterUtils;
import com.yly.utils.ToolsUtils;

@Controller("advanceChargeController")
@RequestMapping("/console/advanceCharge")
public class AdvanceChargeController extends BaseController {

  @Resource(name = "advanceChargeServiceImpl")
  private AdvanceChargeService advanceChargeService;

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
  @RequestMapping(value = "/advanceCharge", method = RequestMethod.GET)
  public String advanceChargeRecord(ModelMap model) {
    return "/advanceCharge/advanceCharge";
  }

  /**
   * 预存款账户列表
   * 
   * @param realName
   * @param identifier
   * @param pageable
   * @param model
   * @return
   */
  @RequestMapping(value = "/chargeAccounts", method = RequestMethod.POST)
  public @ResponseBody Page<Map<String, Object>> accountList(ElderlyInfo elderlyInfo,
      Pageable pageable, ModelMap model) {
    Page<ElderlyInfo> page = new Page<ElderlyInfo>();
    if (elderlyInfo.getName() == null && elderlyInfo.getIdentifier() == null) {
      page = elderlyInfoService.findPage(pageable, true);
    } else {
      if (LogUtil.isDebugEnabled(AdvanceChargeController.class)) {
        LogUtil.debug(AdvanceChargeController.class, "Searching elderly advanceCharge with params",
            "elderlyName=%s,identifier=%s",elderlyInfo.getName(),elderlyInfo.getIdentifier());
      }
      elderlyInfo.setDeleteStatus(DeleteStatus.NOT_DELETED);
      elderlyInfo.setElderlyStatus(ElderlyStatus.IN_NURSING_HOME);
      page = elderlyInfoService.searchElderlyInfo(null, null,elderlyInfo, pageable);
    }

    String[] properties =
        {"id", "name", "identifier", "bedLocation", "nursingLevel", "advanceChargeAmount"};

    List<Map<String, Object>> rows =
        FieldFilterUtils.filterCollectionMap(properties, page.getRows());

    Page<Map<String, Object>> filteredPage =
        new Page<Map<String, Object>>(rows, page.getTotal(), pageable);

    return filteredPage;
  }

  /**
   * 预存款收支明细列表数据
   * 
   * @param pageable
   * @param model
   * @return
   */
  @RequestMapping(value = "/chargeList", method = RequestMethod.POST)
  public @ResponseBody Page<Map<String, Object>> list(ChargeSearchRequest queryParam,
      Pageable pageable, ModelMap model) {
    Page<AdvanceCharge> page = new Page<AdvanceCharge>();
    if (queryParam.getRealName() == null && queryParam.getIdentifier() == null
        && queryParam.getBeginDate() == null && queryParam.getEndDate() == null
        && queryParam.getBudgetType() == null) {
      page = advanceChargeService.findPage(pageable, true);
    } else {
      if (LogUtil.isDebugEnabled(AdvanceChargeController.class)) {
        LogUtil.debug(AdvanceChargeController.class, "Searching advanceChargeRecord records with params",
            "elderlyName=%s,identifier=%s,budgetType=%s,beginDate=%s,endDate=%s", queryParam
                .getRealName(), queryParam.getIdentifier(), queryParam.getBudgetType()!=null?queryParam.getBudgetType().toString():null,
             queryParam.getBeginDate()!=null?queryParam.getBeginDate().toString():null, queryParam.getEndDate()!=null?queryParam.getEndDate().toString():null);
      }
      queryParam.setIsPeriod(false);
//    queryParam.setIsTenant(true);
      page = advanceChargeService.chargeRecordSearch(queryParam, pageable);
    }


    String[] properties =
        {"id", "elderlyInfo.name", "advanceAmount", "budgetType", "payTime", "operator"};

    List<Map<String, Object>> rows =
        FieldFilterUtils.filterCollectionMap(properties, page.getRows());

    Page<Map<String, Object>> filteredPage =
        new Page<Map<String, Object>>(rows, page.getTotal(), pageable);

    return filteredPage;
  }


  /**
   * 获取数据进入详情页面
   * 
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/details", method = RequestMethod.GET)
  public String details(ModelMap model, Long id) {
    AdvanceCharge record = advanceChargeService.find(id);
    model.addAttribute("advanceCharge", record);
    return "advanceCharge/details";
  }

  /**
   * 预缴款
   * 
   * @param advanceCharge
   * @return
   */
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add(Long elderlyInfoID, AdvanceCharge advanceCharge) {
    if (advanceCharge != null) {
      ElderlyInfo elderlyInfo = elderlyInfoService.find(elderlyInfoID);
      elderlyInfo.setAdvanceChargeAmount(elderlyInfo.getAdvanceChargeAmount().add(
          advanceCharge.getAdvanceAmount()));
      advanceCharge.setElderlyInfo(elderlyInfo);
      advanceCharge.setBudgetType(BudgetType.INCOME);
      advanceCharge.setPayTime(new Date());
      advanceCharge.setOperator(tenantAccountService.getCurrentUsername());
      advanceCharge.setBillingNo(ToolsUtils.generateBillNo(tenantAccountService
          .getCurrentTenantOrgCode()));
      advanceChargeService.save(advanceCharge, true);
    }
    return SUCCESS_MESSAGE;
  }

}
