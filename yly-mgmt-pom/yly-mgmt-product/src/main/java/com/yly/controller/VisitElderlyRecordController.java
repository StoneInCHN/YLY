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
import com.yly.entity.ElderlyInfo;
import com.yly.entity.VisitElderlyRecord;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.service.ElderlyInfoService;
import com.yly.service.TenantAccountService;
import com.yly.service.VisitElderlyRecordService;
import com.yly.utils.FieldFilterUtils;

/**
 * 老人探望
 * 
 * @author shijun
 *
 */
@Controller("visitElderlyRecordController")
@RequestMapping("console/visitElderly")
public class VisitElderlyRecordController extends BaseController {

	@Resource(name = "visitElderlyRecordServiceImpl")
	private VisitElderlyRecordService visitElderlyRecordService;

	@Resource(name = "tenantAccountServiceImpl")
	private TenantAccountService tenantAccountService;

	@Resource(name = "elderlyInfoServiceImpl")
	private ElderlyInfoService elderlyInfoService;

	/**
	 * 列表页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/visitElderly", method = RequestMethod.GET)
	public String list(ModelMap model) {
		return "/visitElderly/visitElderly";
	}

	/**
	 * 查询探望记录
	 * 
	 * @param beginDate
	 * @param endDate
	 * @param pageable
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public @ResponseBody Page<VisitElderlyRecord> list(Date beginDate, Date endDate, Pageable pageable,
			ModelMap model) {

//		Page<VisitElderlyRecord> page = visitElderlyRecordService.findPage(pageable, true);
//
//		String[] properties = { "id", "visitor", "visitDate", "dueLeaveDate", "visitPersonnelNumber", "IDCard",
//				"phoneNumber", "reasonForVisit", "relation", "remark", "elderlyInfo.id", "elderlyInfo.name" };
//
//		List<Map<String, Object>> rows = FieldFilterUtils.filterCollectionMap(properties, page.getRows());
//
//		Page<Map<String, Object>> filteredPage = new Page<Map<String, Object>>(rows, page.getTotal(), pageable);
//
//		return filteredPage;
		
		return  visitElderlyRecordService.findPage(pageable, true);
	}

	/**
	 * 添加
	 * 
	 * @param visitElderlyRecord
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody Message add(Long elderlyInfoID, VisitElderlyRecord visitElderlyRecord) {

		ElderlyInfo elderlyInfo = elderlyInfoService.find(elderlyInfoID);

		if (visitElderlyRecord != null) {
			visitElderlyRecord.setTenantID(tenantAccountService.getCurrentTenantID());
			visitElderlyRecord.setElderlyInfo(elderlyInfo);
			visitElderlyRecordService.save(visitElderlyRecord);
		}
		return SUCCESS_MESSAGE;
	}

	/**
	 * 获取数据进入编辑页面
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(ModelMap model, Long id) {
		VisitElderlyRecord visitElderlyRecord = visitElderlyRecordService.find(id);
		model.addAttribute("visitElderlyRecord", visitElderlyRecord);
		return "visitElderly/edit";
	}

	/**
	 * 更新
	 * 
	 * @param visitElderlyRecord
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody Message update(Long elderlyInfoID, VisitElderlyRecord visitElderlyRecord) {
		ElderlyInfo elderlyInfo = elderlyInfoService.find(elderlyInfoID);
		visitElderlyRecord.setElderlyInfo(elderlyInfo);
		visitElderlyRecordService.update(visitElderlyRecord);
		return SUCCESS_MESSAGE;
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Long[] ids) {
		if (ids != null) {
			visitElderlyRecordService.delete(ids);
		}
		return SUCCESS_MESSAGE;
	}

	/**
	 * 老人信息页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/elderlyInfoSearch", method = RequestMethod.GET)
	public String elderlyInfoSearch(ModelMap model) {
		return "/visitElderly/elderlyInfoList";
	}

	/**
	 * 查询探望记录
	 * 
	 * @param beginDate
	 * @param endDate
	 * @param pageable
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/elderlyInfoList", method = RequestMethod.POST)
	public @ResponseBody Page<ElderlyInfo> elderlyinfolist(Date beginDate, Date endDate, Pageable pageable,
			ModelMap model) {
		return elderlyInfoService.findPage(pageable, true);
	}
}
