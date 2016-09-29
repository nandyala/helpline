package org.ashakiran.webapp.filter;


import java.io.IOException;
import java.io.UnsupportedEncodingException;

import java.net.URLDecoder;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.ashakiran.util.HelpLineEncryptionUtil;

import org.springframework.util.StringUtils;

import org.springframework.web.filter.OncePerRequestFilter;


/**
 * DOCUMENT ME!
 *
 * @author   $author$
 * @version  $Revision$, $Date$
 */
public class QueryParamDecrypter extends OncePerRequestFilter {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  /** DOCUMENT ME! */
  protected final transient Log log = LogFactory.getLog(this.getClass());

  private HelpLineEncryptionUtil helpLineEncryptionUtil = null;

  private String randomKey;

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @return  DOCUMENT ME!
   */
  public HelpLineEncryptionUtil getHelpLineEncryptionUtil() {
    return helpLineEncryptionUtil;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @return  DOCUMENT ME!
   */
  public String getRandomKey() {
    return randomKey;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @param  helpLineEncryptionUtil  DOCUMENT ME!
   */
  public void setHelpLineEncryptionUtil(HelpLineEncryptionUtil helpLineEncryptionUtil) {
    this.helpLineEncryptionUtil = helpLineEncryptionUtil;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @param  randomKey  DOCUMENT ME!
   */
  public void setRandomKey(String randomKey) {
    this.randomKey = randomKey;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * @see  org.springframework.web.filter.OncePerRequestFilter#doFilterInternal(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse,
   *       javax.servlet.FilterChain)
   */
  @Override protected void doFilterInternal(HttpServletRequest request,
    HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
    String queryString = request.getQueryString();

    if (StringUtils.hasText(queryString)) {
      if (queryString.contains(getRandomKey()) && queryString.startsWith(getRandomKey())) {
        queryString = helpLineEncryptionUtil.decrypt(queryString);

        // Param Values
        Map<String, String> decryptQueryMap = new HashMap<String, String>();
        String[]            params          = queryString.split("\\&");

        for (String param : params) {
          String[] keyValue = param.split("=");

          try {
            decryptQueryMap.put(keyValue[0], ((keyValue.length > 1) ? URLDecoder.decode(keyValue[1], "UTF-8") : ""));
          } catch (UnsupportedEncodingException e) {
            log.error("Exception while Decoding:\n" + e);
          }
        }

        request.setAttribute("decryptMap", decryptQueryMap);
      }

    }

    chain.doFilter(request, response); // end if
  }                                    // end method doFilterInternal
} // end class QueryParamDecrypter
