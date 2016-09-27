package com.ashakiran.dao.hibernate;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import com.ashakiran.dao.LookupDao;

import com.ashakiran.model.Role;


/**
 * Hibernate implementation of LookupDao.
 *
 * @author   <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Modified by jgarcia: updated to hibernate 4
 * @version  $Revision$, $Date$
 */
@Repository public class LookupDaoHibernate implements LookupDao {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  private Log                  log            = LogFactory.getLog(LookupDaoHibernate.class);
  private final SessionFactory sessionFactory;

  //~ Constructors -----------------------------------------------------------------------------------------------------

  /**
   * Initialize LookupDaoHibernate with Hibernate SessionFactory.
   *
   * @param  sessionFactory  DOCUMENT ME!
   */
  @Autowired public LookupDaoHibernate(final SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * {@inheritDoc}
   */
  @SuppressWarnings("unchecked")
  public List<Role> getRoles() {
    if (log.isDebugEnabled()) {
      log.debug("Retrieving all role names...");
    }

    Session session = sessionFactory.getCurrentSession();

    return session.createCriteria(Role.class).list();
  }
} // end class LookupDaoHibernate
