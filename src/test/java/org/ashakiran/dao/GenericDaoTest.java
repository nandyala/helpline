package org.ashakiran.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.ashakiran.dao.hibernate.GenericDaoHibernate;

import org.ashakiran.model.User;

import org.hibernate.SessionFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;


/**
 * DOCUMENT ME!
 *
 * @author   $author$
 * @version  $Revision$, $Date$
 */
public class GenericDaoTest extends BaseDaoTestCase {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  /** DOCUMENT ME! */
  GenericDao<User, Long> genericDao;

  /** DOCUMENT ME! */
  Log log = LogFactory.getLog(GenericDaoTest.class);

  /** DOCUMENT ME! */
  @Autowired SessionFactory sessionFactory;

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   */
  @Test public void getUser() {
    User user = genericDao.get(-1L);
    assertNotNull(user);
    assertEquals("user", user.getUsername());
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   */
  @Before public void setUp() {
    genericDao = new GenericDaoHibernate<>(User.class, sessionFactory);
  }
} // end class GenericDaoTest
