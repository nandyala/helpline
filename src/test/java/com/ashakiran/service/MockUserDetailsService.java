package com.ashakiran.service;

import org.springframework.dao.DataAccessException;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ashakiran.model.User;


/**
 * DOCUMENT ME!
 *
 * @author   $author$
 * @version  $Revision$, $Date$
 */
public class MockUserDetailsService implements UserDetailsService {
  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @param   username  DOCUMENT ME!
   *
   * @return  DOCUMENT ME!
   *
   * @throws  UsernameNotFoundException  DOCUMENT ME!
   * @throws  DataAccessException        DOCUMENT ME!
   */
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
    return new User("testuser");
  }
}
