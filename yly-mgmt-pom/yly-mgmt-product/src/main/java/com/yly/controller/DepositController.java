package com.yly.controller;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yly.common.log.LogUtil;
import com.yly.controller.base.BaseController;
import com.yly.entity.Deposit;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.json.request.ChargeSearchRequest;
import com.yly.service.DepositService;
import com.yly.utils.FieldFilterUtils;

@Controller("depositController")
@RequestMapping("/console/deposit")
public class DepositController extends BaseController {

  @Resource(name = "depositServiceImpl")
  private DepositService depositService;


  /**
   * 列表页面
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/deposit", method = RequestMethod.GET)
  public String depositChargeRecord(ModelMap model) {
    return "/deposit/deposit";
  }

  /**
   * 列表数据
   * 
   * @param pageable
   * @param model
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<Map<String, Object>> list(ChargeSearchRequest queryParam, Pageable pageable, ModelMap model) {
    Page<Deposit> page = new Page<Deposit>();
    if (queryParam.getRealName() == null && queryParam.getIdentifier() == null && queryParam.getBeginDate() == null && queryParam.getEndDate() == null
            && queryParam.getStatus() == null) {
      page = depositService.findPage(pageable, true);
    } else {
      if (LogUtil.isDebugEnabled(DepositController.class)) {
        LogUtil.debug(DepositController.class, "Searching deposit records with params",
            "elderlyName=%s,identifier=%s,chargeStatus=%s,beginDate=%s,endDate=%s", queryParam
                .getRealName(), queryParam.getIdentifier(),queryParam.getStatus()!=null?queryParam.getStatus().toString():null,
                    queryParam.getBeginDate()!=null?queryParam.getBeginDate().toString():null, queryParam.getEndDate()!=null?queryParam.getEndDate().toString():null);
      }
      queryParam.setIsPeriod(false);
//      queryParam.setIsTenant(true);
      page = depositService.chargeRecordSearch(queryParam,pageable);
    }


    String[] properties =
        {"id", "elderlyInfo.name", "elderlyInfo.identifier", "elderlyInfo.bedLocation",
            "elderlyInfo.nursingLevel", "depositAmount", "payTime","operator","chargeStatus"};

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
    Deposit record = depositService.find(id);
    model.addAttribute("deposit", record);
    return "deposit/details";
  }


}
