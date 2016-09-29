package org.ashakiran.webapp.controller;

import org.ashakiran.service.DepartmentManager;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.RequestMapping;


/**
 * DOCUMENT ME!
 *
 * @author   $author$
 * @version  $Revision$, $Date$
 */
@Controller public class DepartmentController {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  /** DOCUMENT ME! */
  @Autowired DepartmentManager departmentManager;

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @param   model  DOCUMENT ME!
   *
   * @return  DOCUMENT ME!
   */
  @RequestMapping("/departments*")
  public String execute(ModelMap model) {
    model.addAttribute("departmentList", departmentManager.getAll());

    return "departmentList";
  }
}
