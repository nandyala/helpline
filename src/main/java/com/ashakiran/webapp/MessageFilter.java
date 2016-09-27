package com.ashakiran.webapp;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;


/**
 * DOCUMENT ME!
 *
 * @author   $author$
 * @version  $Revision$, $Date$
 */
public class MessageFilter implements Filter {
  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   */
  public void destroy() { }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @param   req    DOCUMENT ME!
   * @param   res    DOCUMENT ME!
   * @param   chain  DOCUMENT ME!
   *
   * @throws  IOException       DOCUMENT ME!
   * @throws  ServletException  DOCUMENT ME!
   */
  public void doFilter(ServletRequest req, ServletResponse res,
    FilterChain chain) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) req;

    // grab messages from the session and put them into request
    // this is so they're not lost in a redirect
    Object message = request.getSession().getAttribute("successMessages");

    if (!request.getRequestURI().startsWith("/app") && (message != null)) {
      request.setAttribute("successMessages", message);
      request.getSession().removeAttribute("successMessages");
    }

    chain.doFilter(req, res);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @param  filterConfig  DOCUMENT ME!
   */
  public void init(FilterConfig filterConfig) { }
} // end class MessageFilter
