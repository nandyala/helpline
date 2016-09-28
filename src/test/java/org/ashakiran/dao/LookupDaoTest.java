package org.ashakiran.dao;

import java.util.List;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;


/**
 * This class tests the current LookupDao implementation class.
 *
 * @author   mraible
 * @version  $Revision$, $Date$
 */
public class LookupDaoTest extends BaseDaoTestCase {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  /** DOCUMENT ME! */
  @Autowired LookupDao lookupDao;

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   */
  @Test public void testGetRoles() {
    List roles = lookupDao.getRoles();

    if (log.isDebugEnabled()) {
      log.debug(roles);
    }

    assertTrue(roles.size() > 0);
  }
}
