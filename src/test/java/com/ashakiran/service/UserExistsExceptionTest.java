package com.ashakiran.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import org.junit.runner.RunWith;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ashakiran.model.User;


/**
 * DOCUMENT ME!
 *
 * @author   $author$
 * @version  $Revision$, $Date$
 */
@ContextConfiguration(
  locations = {
    "/applicationContext-service.xml",
    "/applicationContext-resources.xml",
    "classpath:/applicationContext-dao.xml"
  }
)
@RunWith(SpringJUnit4ClassRunner.class)
public class UserExistsExceptionTest {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  private Log                    log     = LogFactory.getLog(UserExistsExceptionTest.class);
  @Autowired private UserManager manager;

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @throws  Exception  DOCUMENT ME!
   */
  @Test(expected = UserExistsException.class)
  public void testAddExistingUser() throws Exception {
    if (log.isDebugEnabled()) {
      log.debug("entered 'testAddExistingUser' method");
    }

    assertNotNull(manager);

    User user = manager.getUser("-1");

    // create new object with null id - Hibernate doesn't like setId(null)
    User user2 = new User();
    BeanUtils.copyProperties(user, user2);
    user2.setId(null);
    user2.setVersion(null);
    user2.setRoles(null);

    // try saving as new user, this should fail UserExistsException b/c of unique keys
    manager.saveUser(user2);
  }
} // end class UserExistsExceptionTest
