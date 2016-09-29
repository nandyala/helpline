package org.ashakiran.dao;

import org.ashakiran.model.Department;


/**
 * Department Data Access Object (DAO) interface.
 *
 * @author   <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 * @version  $Revision$, $Date$
 */
public interface DepartmentDao extends GenericDao<Department, Long> {
  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * Gets department information based on departmentname.
   *
   * @param   departmentname  the departmentname
   *
   * @return  populated department object
   */
  Department getDepartmentByName(String departmentname);

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * Removes a department from the database by name.
   *
   * @param  departmentname  the department's departmentname
   */
  void removeDepartment(String departmentname);
}
