package com.ashakiran.webapp.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ashakiran.model.User;

import com.ashakiran.service.UserExistsException;
import com.ashakiran.service.UserManager;


/**
 * DOCUMENT ME!
 *
 * @author   $author$
 * @version  $Revision$, $Date$
 */
@Controller
@RequestMapping("/userform*")
public class UserFormController extends BaseFormController {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  /** DOCUMENT ME! */
  @Autowired UserManager userManager;

  /** DOCUMENT ME! */
  @Autowired(required = false)
  Validator         validator;
  private final Log log = LogFactory.getLog(UserFormController.class);

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @param   user     DOCUMENT ME!
   * @param   result   DOCUMENT ME!
   * @param   request  DOCUMENT ME!
   *
   * @return  DOCUMENT ME!
   *
   * @throws  Exception  DOCUMENT ME!
   */
  @RequestMapping(method = RequestMethod.POST)
  public String onSubmit(User user, BindingResult result, HttpServletRequest request) throws Exception {
    if (request.getParameter("cancel") != null) {
      return "redirect:users";
    }

    if ((validator != null) && (request.getParameter("delete") == null)) { // validator is null during testing
      validator.validate(user, result);

      if (result.hasErrors()) {
        return "userform";
      }
    }

    if (log.isDebugEnabled()) {
      log.debug("entering 'onSubmit' method...");
    }

    if (request.getParameter("delete") != null) {
      userManager.removeUser(user.getId().toString());
      saveMessage(request, getText("user.deleted", user.getFullName(), request.getLocale()));
    } else {
      try {
        userManager.saveUser(user);
      } catch (UserExistsException uex) {
        result.addError(new ObjectError("user", uex.getMessage()));

        return "userform";
      }

      saveMessage(request, getText("user.saved", user.getFullName(), request.getLocale()));
    }

    return "redirect:users";
  } // end method onSubmit

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @param   request  DOCUMENT ME!
   *
   * @return  DOCUMENT ME!
   */
  @ModelAttribute
  @RequestMapping(method = RequestMethod.GET)
  protected User getUser(HttpServletRequest request) {
    String userId = request.getParameter("id");

    if ((userId != null) && !userId.equals("")) {
      return userManager.getUser(userId);
    } else {
      return new User();
    }
  }
} // end class UserFormController
