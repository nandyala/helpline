package org.ashakiran.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.search.annotations.Indexed;


/**
 * Created by knandyala on 9/28/2016.
 *
 * @author   $author$
 * @version  $Revision$, $Date$
 */
@Entity @Indexed
@Table(name = "Department")
public class Department extends BaseObject implements Serializable {
  //~ Static fields/initializers ---------------------------------------------------------------------------------------

  private static final long serialVersionUID = -7032689701375471451L;

  //~ Instance fields --------------------------------------------------------------------------------------------------

  /** DOCUMENT ME! */
  @Column(
    name   = "departmentDescription",
    length = 255
  )
  protected String departmentDescription;

  /** DOCUMENT ME! */
  @Column(
    name     = "departmentId",
    nullable = false
  )
  @GeneratedValue(strategy = IDENTITY)
  @Id protected Long departmentId;

  /** DOCUMENT ME! */
  @Column(
    name   = "departmentName",
    length = 96
  )
  protected String departmentName;

} // end class Department
