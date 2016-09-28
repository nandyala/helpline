package org.ashakiran.dao.hibernate;

import java.util.Map;

import org.ashakiran.dao.BaseDaoTestCase;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.persister.entity.EntityPersister;

import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;


/**
 * This class runs a SELECT * of all mapped objects. If an object's corresponding table does not exist in the database,
 * the test will fail.
 *
 * @author   $author$
 * @version  $Revision$, $Date$
 */
public class HibernateConfigurationTest extends BaseDaoTestCase {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  /** DOCUMENT ME! */
  @Autowired SessionFactory sessionFactory;

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @throws  Exception  DOCUMENT ME!
   */
  @Test public void testColumnMapping() throws Exception {
    Session session = sessionFactory.openSession();

    try {
      Map metadata = sessionFactory.getAllClassMetadata();

      for (Object o : metadata.values()) {
        EntityPersister persister = (EntityPersister) o;
        String          className = persister.getEntityName();

        if (log.isDebugEnabled()) {
          log.debug("Trying select * from: " + className);
        }

        Query q = session.createQuery("from " + className + " c");
        q.iterate();

        if (log.isDebugEnabled()) {
          log.debug("ok: " + className);
        }
      }
    } finally {
      session.close();
    }
  } // end method testColumnMapping
} // end class HibernateConfigurationTest
