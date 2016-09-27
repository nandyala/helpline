package com.ashakiran.dao;

import java.util.List;

import static org.junit.Assert.*;

import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;

import com.ashakiran.Constants;

import com.ashakiran.model.Address;
import com.ashakiran.model.Role;
import com.ashakiran.model.User;


/**
 * DOCUMENT ME!
 *
 * @author   $author$
 * @version  $Revision$, $Date$
 */
public class UserDaoTest extends BaseDaoTestCase {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  @Autowired private UserDao dao;
  @Autowired private RoleDao rdao;

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @throws  Exception  DOCUMENT ME!
   */
  @Test(expected = DataAccessException.class)
  public void testAddAndRemoveUser() throws Exception {
    User user = new User("testuser");
    user.setPassword("testpass");
    user.setFirstName("Test");
    user.setLastName("Last");

    Address address = new Address();
    address.setCity("Denver");
    address.setProvince("CO");
    address.setCountry("USA");
    address.setPostalCode("80210");
    user.setAddress(address);
    user.setEmail("testuser@appfuse.org");
    user.setWebsite("http://raibledesigns.com");

    Role role = rdao.getRoleByName(Constants.USER_ROLE);
    assertNotNull(role.getId());
    user.addRole(role);

    user = dao.saveUser(user);
    flush();

    assertNotNull(user.getId());
    user = dao.get(user.getId());
    assertEquals("testpass", user.getPassword());

    dao.remove(user);
    flush();

    // should throw DataAccessException
    dao.get(user.getId());
  } // end method testAddAndRemoveUser

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @throws  Exception  DOCUMENT ME!
   */
  @Test public void testAddUserRole() throws Exception {
    User user = dao.get(-1L);
    assertEquals(1, user.getRoles().size());

    Role role = rdao.getRoleByName(Constants.ADMIN_ROLE);
    user.addRole(role);
    dao.saveUser(user);
    flush();

    user = dao.get(-1L);
    assertEquals(2, user.getRoles().size());

    // add the same role twice - should result in no additional role
    user.addRole(role);
    dao.saveUser(user);
    flush();

    user = dao.get(-1L);
    assertEquals("more than 2 roles", 2, user.getRoles().size());

    user.getRoles().remove(role);
    dao.saveUser(user);
    flush();

    user = dao.get(-1L);
    assertEquals(1, user.getRoles().size());
  } // end method testAddUserRole

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @throws  Exception  DOCUMENT ME!
   */
  @Test public void testGetUser() throws Exception {
    User user = dao.get(-1L);

    assertNotNull(user);
    assertEquals(1, user.getRoles().size());
    assertTrue(user.isEnabled());
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @throws  Exception  DOCUMENT ME!
   */
  @Test(expected = DataAccessException.class)
  public void testGetUserInvalid() throws Exception {
    // should throw DataAccessException
    dao.get(1000L);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @throws  Exception  DOCUMENT ME!
   */
  @Test public void testGetUserPassword() throws Exception {
    User   user     = dao.get(-1L);
    String password = dao.getUserPassword(user.getId());
    assertNotNull(password);

    if (log.isDebugEnabled()) {
      log.debug("password: " + password);
    }
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @throws  Exception  DOCUMENT ME!
   */
  @Test(expected = DataIntegrityViolationException.class)
  public void testUpdateUser() throws Exception {
    User user = dao.get(-1L);

    Address address = user.getAddress();
    address.setAddress("new address");

    dao.saveUser(user);
    flush();

    user = dao.get(-1L);
    assertEquals(address, user.getAddress());
    assertEquals("new address", user.getAddress().getAddress());

    // verify that violation occurs when adding new user with same username
    User user2 = new User();
    user2.setAddress(user.getAddress());
    user2.setConfirmPassword(user.getConfirmPassword());
    user2.setEmail(user.getEmail());
    user2.setFirstName(user.getFirstName());
    user2.setLastName(user.getLastName());
    user2.setPassword(user.getPassword());
    user2.setPasswordHint(user.getPasswordHint());
    user2.setRoles(user.getRoles());
    user2.setUsername(user.getUsername());
    user2.setWebsite(user.getWebsite());

    // should throw DataIntegrityViolationException
    dao.saveUser(user2);
  } // end method testUpdateUser

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @throws  Exception  DOCUMENT ME!
   */
  @Test public void testUserExists() throws Exception {
    boolean b = dao.exists(-1L);
    assertTrue(b);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @throws  Exception  DOCUMENT ME!
   */
  @Test public void testUserNotExists() throws Exception {
    boolean b = dao.exists(111L);
    assertFalse(b);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @throws  Exception  DOCUMENT ME!
   */
  @Test public void testUserSearch() throws Exception {
    // reindex all the data
    dao.reindex();

    List<User> found = dao.search("Matt");
    assertEquals(1, found.size());

    User user = found.get(0);
    assertEquals("Matt", user.getFirstName());

    // test mirroring
    user = dao.get(-2L);
    user.setFirstName("MattX");
    dao.saveUser(user);
    flush();
    flushSearchIndexes();

    // now verify it is reflected in the index
    found = dao.search("MattX");
    assertEquals(1, found.size());
    user = found.get(0);
    assertEquals("MattX", user.getFirstName());
  }
} // end class UserDaoTest
