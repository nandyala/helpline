package com.ashakiran.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.junit.After;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import com.ashakiran.Constants;

import com.ashakiran.model.User;


/**
 * DOCUMENT ME!
 *
 * @author   $author$
 * @version  $Revision$, $Date$
 */
@Transactional(Transactional.TxType.NOT_SUPPORTED)
public class UserManagerTest extends BaseManagerTestCase {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  private Log                    log         = LogFactory.getLog(UserManagerTest.class);
  @Autowired private UserManager mgr;
  @Autowired private RoleManager roleManager;

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   */
  @After public void after() {
    User user = mgr.getUserByUsername("john");
    mgr.removeUser(user.getId().toString());

    try {
      mgr.getUserByUsername("john");
      fail("Expected 'Exception' not thrown");
    } catch (Exception e) {
      if (log.isDebugEnabled()) {
        log.debug(e);
      }

      assertNotNull(e);
    }
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @throws  Exception  DOCUMENT ME!
   */
  @Before public void before() throws Exception {
    User user = new User();

    // call populate method in super class to populate test data
    // from a properties file matching this class name
    user = (User) populate(user);

    user.addRole(roleManager.getRole(Constants.USER_ROLE));

    user = mgr.saveUser(user);
    assertEquals("john", user.getUsername());
    assertEquals(1, user.getRoles().size());
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @throws  Exception  DOCUMENT ME!
   */
  @Test public void testGetAll() throws Exception {
    List<User> found = mgr.getAll();

    if (log.isDebugEnabled()) {
      log.debug("Users found: " + found.size());
    }

    // don't assume exact number so tests can run in parallel
    assertFalse(found.isEmpty());
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @throws  Exception  DOCUMENT ME!
   */
  @Test public void testGetUser() throws Exception {
    User user = mgr.getUserByUsername("john");
    assertNotNull(user);
    assertEquals(1, user.getRoles().size());
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @throws  Exception  DOCUMENT ME!
   */
  @Test public void testSaveUser() throws Exception {
    User user = mgr.getUserByUsername("john");
    user.setPhoneNumber("303-555-1212");

    if (log.isDebugEnabled()) {
      log.debug("saving user with updated phone number: " + user);
    }

    user = mgr.saveUser(user);
    assertEquals("303-555-1212", user.getPhoneNumber());
    assertEquals(1, user.getRoles().size());
  }
} // end class UserManagerTest
