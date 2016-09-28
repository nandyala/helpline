package org.ashakiran.util;

import java.text.ParseException;

import java.util.Date;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import org.springframework.context.i18n.LocaleContextHolder;


/**
 * DOCUMENT ME!
 *
 * @author   $author$
 * @version  $Revision$, $Date$
 */
public class DateUtilTest {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  private final Log log = LogFactory.getLog(DateUtilTest.class);

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @throws  Exception  DOCUMENT ME!
   */
  @Test public void testGetDate() throws Exception {
    if (log.isDebugEnabled()) {
      log.debug("db date to convert: " + new Date());
    }

    String date = DateUtil.getDate(new Date());

    if (log.isDebugEnabled()) {
      log.debug("converted ui date: " + date);
    }

    assertTrue(date != null);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   */
  @Test public void testGetDateTime() {
    if (log.isDebugEnabled()) {
      log.debug("entered 'testGetDateTime' method");
    }

    String now = DateUtil.getTimeNow(new Date());
    assertTrue(now != null);

    if (log.isDebugEnabled()) {
      log.debug(now);
    }
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   */
  @Test public void testGetDateTimeWithNull() {
    final String date = DateUtil.getDateTime(null, null);
    assertEquals("", date);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   */
  @Test public void testGetDateWithNull() {
    final String date = DateUtil.getDate(null);
    assertEquals("", date);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   */
  @Test public void testGetInternationalDatePattern() {
    LocaleContextHolder.setLocale(new Locale("nl"));
    assertEquals("dd-MMM-yyyy", DateUtil.getDatePattern());

    LocaleContextHolder.setLocale(Locale.FRANCE);
    assertEquals("dd/MM/yyyy", DateUtil.getDatePattern());

    LocaleContextHolder.setLocale(Locale.GERMANY);
    assertEquals("dd.MM.yyyy", DateUtil.getDatePattern());

    // non-existant bundle should default to default locale
    LocaleContextHolder.setLocale(new Locale("fi"));

    String fiPattern = DateUtil.getDatePattern();
    LocaleContextHolder.setLocale(Locale.getDefault());

    String defaultPattern = DateUtil.getDatePattern();

    assertEquals(defaultPattern, fiPattern);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @throws  ParseException  DOCUMENT ME!
   */
  @Test public void testGetToday() throws ParseException {
    assertNotNull(DateUtil.getToday());
  }
} // end class DateUtilTest
