package org.ashakiran.webapp;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;


/**
 * Override UserSecurityAdvice from appfuse-service in order to allow anyone to update a user's information.
 *
 * @author   mraible
 * @version  $Revision$, $Date$
 */
public class UserSecurityAdvice implements MethodBeforeAdvice, AfterReturningAdvice {
  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * After returning, grab the user, check if they've been modified and reset the SecurityContext if they have.
   *
   * @param   returnValue  the user object
   * @param   method       the name of the method executed
   * @param   args         the arguments to the method
   * @param   target       the target class
   *
   * @throws  Throwable  thrown when args[0] is null or not a User object
   */
  @Override public void afterReturning(Object returnValue, Method method, Object[] args, Object target)
    throws Throwable { }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * Method to enforce security and only allow administrators to modify users. Regular users are allowed to modify
   * themselves.
   *
   * @param   method  the name of the method executed
   * @param   args    the arguments to the method
   * @param   target  the target class
   *
   * @throws  Throwable  thrown when args[0] is null or not a User object
   */
  @Override public void before(Method method, Object[] args, Object target) throws Throwable { }
} // end class UserSecurityAdvice
