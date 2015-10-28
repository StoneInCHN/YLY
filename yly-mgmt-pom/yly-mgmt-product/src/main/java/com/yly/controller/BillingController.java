package com.yly.controller;


import java.util.ArrayList;
import java.util.HashMap;
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
import com.yly.entity.Billing;
import com.yly.entity.ElderlyInfo;
import com.yly.entity.commonenum.CommonEnum.BillingType;
import com.yly.entity.commonenum.CommonEnum.ElderlyStatus;
import com.yly.framework.filter.Filter;
import com.yly.framework.filter.Filter.Operator;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.json.request.ChargeSearchRequest;
import com.yly.service.BillingService;
import com.yly.service.ElderlyInfoService;
import com.yly.utils.FieldFilterUtils;

@Controller("billingController")
@RequestMapping("/console/billing")
public class BillingController extends BaseController {

  @Resource(name = "billingServiceImpl")
  private BillingService billingService;

  @Resource(name = "elderlyInfoServiceImpl")
  private ElderlyInfoService elderlyInfoService;


  /**
   * 入院缴费页面
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/checkinCharge", method = RequestMethod.GET)
  public String checkinCharge(ModelMap model) {
    return "/checkinCharge/checkin";
  }

  /**
   * 退住结算页面
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/checkoutCharge", method = RequestMethod.GET)
  public String checkoutCharge(ModelMap model) {
    return "/checkoutCharge/checkout";
  }

  /**
   * 日常缴费页面
   * 
   * @param model
   * @return
   */
  @RequestMapping(value = "/billing", method = RequestMethod.GET)
  public String dailyCharge(ModelMap model) {
    return "/billing/billing";
  }

  /**
   * 列表数据
   * 
   * @param beginDate
   * @param endDate
   * @param pageable
   * @param model
   * @return
   */
  @RequestMapping(value = "/list", method = RequestMethod.POST)
  public @ResponseBody Page<Map<String, Object>> list(ChargeSearchRequest queryParam,
      Pageable pageable, ModelMap model) {
    Page<Billing> page = new Page<Billing>();
    if (queryParam.getRealName() == null && queryParam.getIdentifier() == null
        && queryParam.getBeginDate() == null && queryParam.getEndDate() == null) {
      List<Filter> filters = new ArrayList<Filter>();
      Filter filter = new Filter("billType", Operator.eq, queryParam.getBillingType());
      filters.add(filter);
      pageable.setFilters(filters);
      page = billingService.findPage(pageable, true);
    } else {
      if (LogUtil.isDebugEnabled(BillingController.class)) {
        LogUtil.debug(BillingController.class, "Searching billing records with params",
            "elderlyName=%s,identifier=%s,billType=%s,beginDate=%s,endDate=%s",
            queryParam.getRealName(), queryParam.getIdentifier(),
            queryParam.getBillingType() != null ? queryParam.getBillingType().toString() : null,
            queryParam.getBeginDate() != null ? queryParam.getBeginDate().toString() : null,
            queryParam.getEndDate() != null ? queryParam.getEndDate().toString() : null);
      }
      if (queryParam.getBillingType()!=BillingType.DAILY) {
    	  queryParam.setIsPeriod(false);
	  }else {
		  queryParam.setIsPeriod(true);
	  }
     
      queryParam.setIsTenant(true);
      page = billingService.chargeRecordSearch(queryParam, pageable);
    }


    String[] properties =
        {"id", "elderlyInfo.name", "elderlyInfo.identifier", "elderlyInfo.bedLocation",
            "elderlyInfo.nursingLevel", "nurseAmount", "mealAmount", "depositAmount",
            "totalAmount", "bedAmount", "payTime", "operator"};

    List<Map<String, Object>> rows =
        FieldFilterUtils.filterCollectionMap(properties, page.getRows());

    Page<Map<String, Object>> filteredPage =
        new Page<Map<String, Object>>(rows, page.getTotal(), pageable);

    return filteredPage;
  }



  /**
   * 根据老人获取床位护理费配置
   * 
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/getBedNurseConfig", method = RequestMethod.POST)
  public @ResponseBody List<Map<String, Object>> getBedNurseConfig(ModelMap model,
      Long elderlyInfoID) {
    ElderlyInfo elderlyInfo = elderlyInfoService.find(elderlyInfoID);
    if (!ElderlyStatus.IN_PROGRESS_CHECKIN.equals(elderlyInfo.getElderlyStatus())) {
    	List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("errMsg", Message.error("yly.checkin.elderlyStatus.invalid").getContent());
    	list.add(map);
		return list;
	}
    String[] properties = {"chargeItem.configValue", "amountPerDay", "amountPerMonth"};

    return billingService.getBedNurseConfigByElderly(properties, elderlyInfo);
  }


  @RequestMapping(value = "/checkin", method = RequestMethod.POST)
  public @ResponseBody Message add(Billing checkinBill, Long mealTypeId,Long elderlyInfoID) {

    // billingService.save(checkinBill,true);
    return SUCCESS_MESSAGE;
  }

  /**
   * 获取数据进入详情页面
   * 
   * @param model
   * @param id
   * @return
   */
  @RequestMapping(value = "/details", method = RequestMethod.GET)
  public String details(ModelMap model, Long id, String path) {
    Billing record = billingService.find(id);
    model.addAttribute("billing", record);
    return path + "/details";
  }

}
