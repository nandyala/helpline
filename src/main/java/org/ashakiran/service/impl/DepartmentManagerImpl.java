package org.ashakiran.service.impl;

import java.util.List;

import org.ashakiran.dao.DepartmentDao;

import org.ashakiran.model.Department;

import org.ashakiran.service.DepartmentManager;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


/**
 * Implementation of DepartmentManager interface.
 *
 * @author   <a href="mailto:dan@getrolling.com">Dan Kibler</a>
 * @version  $Revision$, $Date$
 */
@Service("departmentManager")
public class DepartmentManagerImpl extends GenericManagerImpl<Department, Long> implements DepartmentManager {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  /** DOCUMENT ME! */
  DepartmentDao departmentDao;

  //~ Constructors -----------------------------------------------------------------------------------------------------

  /**
   * Creates a new DepartmentManagerImpl object.
   *
   * @param  departmentDao  DOCUMENT ME!
   */
  @Autowired public DepartmentManagerImpl(DepartmentDao departmentDao) {
    super(departmentDao);
    this.departmentDao = departmentDao;
  }

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * {@inheritDoc}
   */
  @Override public Department getDepartment(String departmentname) {
    return departmentDao.getDepartmentByName(departmentname);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * {@inheritDoc}
   */
  @Override public List<Department> getDepartments(Department department) {
    return dao.getAll();
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * {@inheritDoc}
   */
  @Override public void removeDepartment(String departmentname) {
    departmentDao.removeDepartment(departmentname);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * {@inheritDoc}
   */
  @Override public Department saveDepartment(Department department) {
    return dao.save(department);
  }
} // end class DepartmentManagerImpl
