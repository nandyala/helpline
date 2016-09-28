package org.ashakiran.webapp.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.junit.runner.RunWith;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * DOCUMENT ME!
 *
 * @author   $author$
 * @version  $Revision$, $Date$
 */
@ContextConfiguration(
  locations = {
    "classpath:/applicationContext-resources.xml",
    "classpath:/applicationContext-dao.xml",
    "classpath:/applicationContext-service.xml",
    "/WEB-INF/applicationContext*.xml",
    "/WEB-INF/dispatcher-servlet.xml"
  }
)
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class BaseControllerTestCase {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  /** DOCUMENT ME! */
  protected final transient Log log = LogFactory.getLog(getClass());
}
