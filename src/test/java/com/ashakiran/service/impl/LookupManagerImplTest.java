package com.ashakiran.service.impl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import static org.mockito.BDDMockito.given;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.ashakiran.Constants;

import com.ashakiran.dao.LookupDao;

import com.ashakiran.model.LabelValue;
import com.ashakiran.model.Role;


/**
 * DOCUMENT ME!
 *
 * @author   $author$
 * @version  $Revision$, $Date$
 */
public class LookupManagerImplTest extends BaseManagerMockTestCase {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  @Mock private LookupDao lookupDao;

  @InjectMocks private LookupManagerImpl mgr = new LookupManagerImpl();

  //~ Methods ----------------------------------------------------------------------------------------------------------


  /**
   * DOCUMENT ME!
   */
  @Test public void testGetAllRoles() {
    if (log.isDebugEnabled()) {
      log.debug("entered 'testGetAllRoles' method");
    }

    // given
    Role             role     = new Role(Constants.ADMIN_ROLE);
    final List<Role> testData = new ArrayList<Role>();
    testData.add(role);

    given(lookupDao.getRoles()).willReturn(testData);

    // when
    List<LabelValue> roles = mgr.getAllRoles();

    // then
    assertTrue(roles.size() > 0);
  }

} // end class LookupManagerImplTest
