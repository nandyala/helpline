package org.ashakiran.dao;

import java.util.HashMap;
import java.util.List;

import org.ashakiran.Constants;

import org.ashakiran.model.Role;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;


/**
 * DOCUMENT ME!
 *
 * @author   $author$
 * @version  $Revision$, $Date$
 */
public class RoleDaoTest extends BaseDaoTestCase {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  @Autowired private RoleDao dao;

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @throws  Exception  DOCUMENT ME!
   */
  @Test public void testAddAndRemoveRole() throws Exception {
    Role role = new Role("testrole");
    role.setDescription("new role descr");
    dao.save(role);
    flush();

    role = dao.getRoleByName("testrole");
    assertNotNull(role.getDescription());

    dao.removeRole("testrole");
    flush();

    role = dao.getRoleByName("testrole");
    assertNull(role);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   */
  @Test public void testFindByNamedQuery() {
    HashMap<String, Object> queryParams = new HashMap<String, Object>();
    queryParams.put("name", Constants.USER_ROLE);

    List<Role> roles = dao.findByNamedQuery("findRoleByName", queryParams);
    assertNotNull(roles);
    assertTrue(roles.size() > 0);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @throws  Exception  DOCUMENT ME!
   */
  @Test public void testGetRole() throws Exception {
    Role role = dao.getRoleByName(Constants.USER_ROLE);
    assertNotNull(role);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @throws  Exception  DOCUMENT ME!
   */
  @Test public void testGetRoleInvalid() throws Exception {
    Role role = dao.getRoleByName("badrolename");
    assertNull(role);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @throws  Exception  DOCUMENT ME!
   */
  @Test public void testUpdateRole() throws Exception {
    Role role = dao.getRoleByName("ROLE_USER");
    role.setDescription("test descr");
    dao.save(role);
    flush();

    role = dao.getRoleByName("ROLE_USER");
    assertEquals("test descr", role.getDescription());
  }
} // end class RoleDaoTest
