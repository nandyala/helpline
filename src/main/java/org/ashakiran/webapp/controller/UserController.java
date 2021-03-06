package org.ashakiran.webapp.controller;

import org.ashakiran.service.UserManager;

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
@Controller public class UserController {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  /** DOCUMENT ME! */
  @Autowired UserManager userManager;

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @param   model  DOCUMENT ME!
   *
   * @return  DOCUMENT ME!
   */
  @RequestMapping("/users*")
  public String execute(ModelMap model) {
    model.addAttribute("userList", userManager.getUsers());

    return "userList";
  }
}
