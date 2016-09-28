package org.ashakiran.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import org.springframework.security.core.GrantedAuthority;


/**
 * This class is used to represent available roles in the database.
 *
 * @author   <a href="mailto:matt@raibledesigns.com">Matt Raible</a> Version by Dan Kibler dan@getrolling.com Extended
 *           to implement Acegi GrantedAuthority interface by David Carter david@carter.net
 * @version  $Revision$, $Date$
 */
@Entity
@NamedQueries(
  {
    @NamedQuery(
      name = "findRoleByName",
      query = "select r from Role r where r.name = :name "
    )
  }
)
@Table(name = "role")
public class Role extends BaseObject implements Serializable, GrantedAuthority {
  //~ Static fields/initializers ---------------------------------------------------------------------------------------

  private static final long serialVersionUID = 3690197650654049848L;

  //~ Instance fields --------------------------------------------------------------------------------------------------

  private String description;
  private Long   id;
  private String name;

  //~ Constructors -----------------------------------------------------------------------------------------------------

  /**
   * Default constructor - creates a new instance with no values set.
   */
  public Role() { }

  /**
   * Create a new instance and set the name.
   *
   * @param  name  name of the role.
   */
  public Role(final String name) {
    this.name = name;
  }

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * {@inheritDoc}
   */
  @Override public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof Role)) {
      return false;
    }

    final Role role = (Role) o;

    return !((name != null) ? (!name.equals(role.name)) : (role.name != null));

  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * The name property (getAuthority required by Acegi's GrantedAuthority interface).
   *
   * @return  the name property (getAuthority required by Acegi's GrantedAuthority interface)
   *
   * @see     org.springframework.security.core.GrantedAuthority#getAuthority()
   */
  @Override @Transient public String getAuthority() {
    return getName();
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @return  DOCUMENT ME!
   */
  @Column(length = 64)
  public String getDescription() {
    return this.description;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @return  DOCUMENT ME!
   */
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Id public Long getId() {
    return id;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @return  DOCUMENT ME!
   */
  @Column(length = 20)
  public String getName() {
    return this.name;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * {@inheritDoc}
   */
  @Override public int hashCode() {
    return ((name != null) ? name.hashCode() : 0);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @param  description  DOCUMENT ME!
   */
  public void setDescription(String description) {
    this.description = description;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @param  id  DOCUMENT ME!
   */
  public void setId(Long id) {
    this.id = id;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @param  name  DOCUMENT ME!
   */
  public void setName(String name) {
    this.name = name;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * {@inheritDoc}
   */
  @Override public String toString() {
    return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append(this.name).toString();
  }
} // end class Role