package org.ashakiran.dao.hibernate;

import java.util.List;

import org.ashakiran.dao.DepartmentDao;

import org.ashakiran.model.Department;

import org.hibernate.Session;

import org.hibernate.criterion.Restrictions;

import org.springframework.stereotype.Repository;


/**
 * This class interacts with hibernate session to save/delete and retrieve Department objects.
 *
 * @author   <a href="mailto:bwnoll@gmail.com">Bryan Noll</a>
 * @author   jgarcia (updated to hibernate 4)
 * @version  $Revision$, $Date$
 */
@Repository("departmentDao")
public class DepartmentDaoHibernate extends GenericDaoHibernate<Department, Long> implements DepartmentDao {
  //~ Constructors -----------------------------------------------------------------------------------------------------

  /**
   * Constructor to create a Generics-based version using Department as the entity.
   */
  public DepartmentDaoHibernate() {
    super(Department.class);
  }

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * {@inheritDoc}
   */
  @Override public Department getDepartmentByName(String departmentname) {
    List departments = getSession().createCriteria(Department.class).add(Restrictions.eq("name", departmentname))
      .list();

    if (departments.isEmpty()) {
      return null;
    } else {
      return (Department) departments.get(0);
    }
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * {@inheritDoc}
   */
  @Override public void removeDepartment(String departmentname) {
    Object  department = getDepartmentByName(departmentname);
    Session session    = getSessionFactory().getCurrentSession();
    session.delete(department);
  }
} // end class DepartmentDaoHibernate
