package com.yly.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yly.beans.Message;
import com.yly.controller.base.BaseController;
import com.yly.entity.NurseDutyType;
import com.yly.framework.paging.Page;
import com.yly.framework.paging.Pageable;
import com.yly.service.NurseDutyTypeService;

@Controller("nurseDutyTypeController")
@RequestMapping("console/nurseDutyType")
public class NurseDutyTypeController extends BaseController {

	@Resource(name = "nurseDutyTypeServiceImpl")
	private NurseDutyTypeService nurseDutyTypeService;

	@RequestMapping(value = "/nurseDutyType", method = RequestMethod.GET)
	public String bed(ModelMap model) {
		return "nurseDutyType/nurseDutyType";
	}

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public @ResponseBody Page<NurseDutyType> list(Pageable pageable) {
		return nurseDutyTypeService.findPage(pageable, true);
	}

	/**
	 * 编辑
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(ModelMap model, Long id) {
		model.addAttribute("nurseDutyType", nurseDutyTypeService.find(id));
		return "nurseDutyType/edit";
	}

	/**
	 * 保存
	 * 
	 * @param building
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody Message add(NurseDutyType nurseDutyType) {
		nurseDutyTypeService.save(nurseDutyType, true);
		return SUCCESS_MESSAGE;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody Message update(NurseDutyType nurseDutyType) {
		nurseDutyTypeService.update(nurseDutyType,"tenantID");
		return SUCCESS_MESSAGE;
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
			nurseDutyTypeService.delete(ids);
		}
		return SUCCESS_MESSAGE;
	}

}
