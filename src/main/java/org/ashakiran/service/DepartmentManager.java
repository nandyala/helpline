package org.ashakiran.service;

import java.util.List;

import org.ashakiran.model.Department;


/**
 * Business Service Interface to handle communication between web and persistence layer.
 *
 * @author   <a href="mailto:dan@getrolling.com">Dan Kibler</a>
 * @version  $Revision$, $Date$
 */
public interface DepartmentManager extends GenericManager<Department, Long> {
  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * {@inheritDoc}
   */
  Department getDepartment(String departmentname);

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * {@inheritDoc}
   */
  List getDepartments(Department department);

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * {@inheritDoc}
   */
  void removeDepartment(String departmentname);

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * {@inheritDoc}
   */
  Department saveDepartment(Department department);
} // end interface DepartmentManager
