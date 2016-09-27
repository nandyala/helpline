package com.ashakiran.dao.hibernate;

import java.util.List;

import org.hibernate.Session;

import org.hibernate.criterion.Restrictions;

import org.springframework.stereotype.Repository;

import com.ashakiran.dao.RoleDao;

import com.ashakiran.model.Role;


/**
 * This class interacts with hibernate session to save/delete and retrieve Role objects.
 *
 * @author   <a href="mailto:bwnoll@gmail.com">Bryan Noll</a>
 * @author   jgarcia (updated to hibernate 4)
 * @version  $Revision$, $Date$
 */
@Repository public class RoleDaoHibernate extends GenericDaoHibernate<Role, Long> implements RoleDao {
  //~ Constructors -----------------------------------------------------------------------------------------------------

  /**
   * Constructor to create a Generics-based version using Role as the entity.
   */
  public RoleDaoHibernate() {
    super(Role.class);
  }

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * {@inheritDoc}
   */
  public Role getRoleByName(String rolename) {
    List roles = getSession().createCriteria(Role.class).add(Restrictions.eq("name", rolename)).list();

    if (roles.isEmpty()) {
      return null;
    } else {
      return (Role) roles.get(0);
    }
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * {@inheritDoc}
   */
  public void removeRole(String rolename) {
    Object  role    = getRoleByName(rolename);
    Session session = getSessionFactory().getCurrentSession();
    session.delete(role);
  }
} // end class RoleDaoHibernate
