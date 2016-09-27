package com.ashakiran.service.impl;

import org.junit.Ignore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ashakiran.service.UserManager;


/**
 * DOCUMENT ME!
 *
 * @author   ivangsa
 * @version  $Revision$, $Date$
 */
@Ignore("create table password_reset_token before running this test")
public class PersistentPasswordTokenManagerTest extends PasswordTokenManagerTest {
  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @param  passwordTokenManager  DOCUMENT ME!
   */
  @Autowired
  @Qualifier("persistentPasswordTokenManager")
  public void setPasswordTokenManager(PasswordTokenManager passwordTokenManager) {
    super.setPasswordTokenManager(passwordTokenManager);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @param  userManager  DOCUMENT ME!
   */
  @Autowired
  @Qualifier("persistentPasswordTokenManager.userManager")
  public void setUserManager(UserManager userManager) {
    super.setUserManager(userManager);
  }
} // end class PersistentPasswordTokenManagerTest
