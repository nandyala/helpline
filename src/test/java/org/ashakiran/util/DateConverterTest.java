package org.ashakiran.util;

import java.sql.Timestamp;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.Test;

import org.springframework.context.i18n.LocaleContextHolder;


/**
 * DOCUMENT ME!
 *
 * @author   $author$
 * @version  $Revision$, $Date$
 */
public class DateConverterTest {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  private final DateConverter converter = new DateConverter();

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @throws  Exception  DOCUMENT ME!
   */
  @Test public void testConvertDateToString() throws Exception {
    final Calendar cal  = new GregorianCalendar(2005, 0, 16);
    final String   date = (String) converter.convert(String.class, cal.getTime());
    assertEquals(DateUtil.convertDateToString(cal.getTime()), date);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @throws  Exception  DOCUMENT ME!
   */
  @Test public void testConvertDateWithNull() throws Exception {
    final String date = (String) converter.convert(String.class, null);
    assertNull(date);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @throws  Exception  DOCUMENT ME!
   */
  @Test public void testConvertNotSupported() throws Exception {
    final Calendar cal = new GregorianCalendar(2005, 0, 16);

    try {
      converter.convert(Object.class, cal.getTime());
      fail("Object.class is not supported");
    } catch (final Exception e) {
      // expected
    }

    try {
      converter.convertToDate(Object.class, cal.getTime(), "");
      fail("Object.class is not supported");
    } catch (final Exception e) {
      // expected
    }
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @throws  Exception  DOCUMENT ME!
   */
  @Test public void testConvertStringToDate() throws Exception {
    final Date     today         = new Date();
    final Calendar todayCalendar = new GregorianCalendar();
    todayCalendar.setTime(today);

    final String datePart = DateUtil.convertDateToString(today);

    // test empty time
    Date date = (Date) converter.convert(Date.class, "");
    assertNull(date);
    date = (Date) converter.convert(Date.class, datePart);

    final Calendar cal = new GregorianCalendar();
    cal.setTime(date);
    assertEquals(todayCalendar.get(Calendar.YEAR), cal.get(Calendar.YEAR));
    assertEquals(todayCalendar.get(Calendar.MONTH), cal.get(Calendar.MONTH));
    assertEquals(todayCalendar.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.DAY_OF_MONTH));
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @throws  Exception  DOCUMENT ME!
   */
  @Test public void testConvertStringToString() throws Exception {
    assertEquals(converter.convert(String.class, "anystring"), "anystring");
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @throws  Exception  DOCUMENT ME!
   */
  @Test public void testConvertStringToTimestamp() throws Exception {
    final Date     today         = new Date();
    final Calendar todayCalendar = new GregorianCalendar();
    todayCalendar.setTime(today);

    final String datePart = DateUtil.convertDateToString(today);

    final Timestamp time = (Timestamp) converter.convert(Timestamp.class, datePart + " 01:02:03.4");
    final Calendar  cal  = new GregorianCalendar();
    cal.setTimeInMillis(time.getTime());
    assertEquals(todayCalendar.get(Calendar.YEAR), cal.get(Calendar.YEAR));
    assertEquals(todayCalendar.get(Calendar.MONTH), cal.get(Calendar.MONTH));
    assertEquals(todayCalendar.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.DAY_OF_MONTH));
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @throws  Exception  DOCUMENT ME!
   */
  @Test public void testConvertTimestampToString() throws Exception {
    final Timestamp timestamp = Timestamp.valueOf("2005-03-10 01:02:03.4");
    final String    time      = (String) converter.convert(String.class, timestamp);
    assertEquals(DateUtil.getDateTime(DateUtil.getDateTimePattern(), timestamp), time);
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @throws  Exception  DOCUMENT ME!
   */
  @Test public void testInternationalization() throws Exception {
    final List<Locale> locales = new ArrayList<Locale>() {
      private static final long serialVersionUID = 1L;

      {
        add(Locale.US);
        add(Locale.GERMANY);
        add(Locale.FRANCE);
        add(Locale.CHINA);
        add(Locale.ITALY);
      }
    };

    for (final Locale locale : locales) {
      LocaleContextHolder.setLocale(locale);
      testConvertStringToDate();
      testConvertDateToString();
      testConvertStringToTimestamp();
      testConvertTimestampToString();
    }
  }

} // end class DateConverterTest
