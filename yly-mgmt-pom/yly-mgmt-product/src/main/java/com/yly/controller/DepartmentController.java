package com.yly.controller;

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
import com.yly.entity.Department;
import com.yly.service.DepartmentService;

/**
 * 部门管理
 * 
 * @author huyong
 *
 */
@Controller("departmentController")
@RequestMapping("console/department")
public class DepartmentController extends BaseController {

  @Resource(name = "departmentServiceImpl")
  private DepartmentService departmentService;

  @RequestMapping(value = "/department", method = RequestMethod.GET)
  public String list(ModelMap model) {
    return "department/department";
  }

  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public @ResponseBody List<Department> list() {
    return departmentService.findRoots();
  }

  /**
   * get data for vendor edit page
   * 
   * @param model
   * @param vendorId
   * @return
   */
  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  public String edit(ModelMap model, Long id) {
    model.addAttribute("department", departmentService.find(id));
    return "department/edit";
  }

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public @ResponseBody Message add(Long parentId, Department department) {

    if (parentId != null) {
      Department parent = departmentService.find(parentId);
      department.setParent(parent);
      Integer grade = parent.getGrade();
      if (grade == 0) {
        grade = 1;
      }
      department.setGrade(++grade);
    } else {
      department.setGrade(1);
    }
    departmentService.save(department, true);;
    return SUCCESS_MESSAGE;
  }

  @RequestMapping(value = "/update", method = RequestMethod.POST)
  public @ResponseBody Message update(Department department, Long parentId) {
    if (parentId != null) {
      Department parent = departmentService.find(parentId);
      department.setParent(parent);
      department.setGrade(parent.getGrade() + 1);
    }
    departmentService.update(department,"tenantID");
    return SUCCESS_MESSAGE;
  }


  /**
   * 删除
   */
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public @ResponseBody Message delete(Long[] ids) {
    if (ids != null) {
      // 检查是否能被删除
      // if()
      departmentService.delete(ids);
    }
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
  public String details(ModelMap model, Long id) {

    model.addAttribute("department", departmentService.find(id));
    return "department/details";
  }

  @RequestMapping(value = "/findAllDepartments", method = RequestMethod.POST)
  public @ResponseBody List<Map<String, Object>> findAllDepartments() {
    return departmentService.findAllDepartments();
  }

}
