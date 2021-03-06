package org.ashakiran.util;

import java.text.DecimalFormat;
import java.text.ParseException;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * This class is converts a Double to a double-digit String (and vise-versa) by BeanUtils when copying properties.
 *
 * @author   <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 * @version  $Revision$, $Date$
 */
public class CurrencyConverter implements Converter {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  private DecimalFormat formatter = new DecimalFormat("###,###.00");
  private final Log     log       = LogFactory.getLog(CurrencyConverter.class);

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * Convert a String to a Double and a Double to a String.
   *
   * @param   type   the class type to output
   * @param   value  the object to convert
   *
   * @return  object the converted object (Double or String)
   *
   * @throws  ConversionException  DOCUMENT ME!
   */
  @Override public final Object convert(final Class type, final Object value) {
    // for a null value, return null
    if (value == null) {
      return null;
    } else {
      if (value instanceof String) {
        if (log.isDebugEnabled()) {
          log.debug("value (" + value + ") instance of String");
        }

        try {
          if (StringUtils.isBlank(String.valueOf(value))) {
            return null;
          }

          if (log.isDebugEnabled()) {
            log.debug("converting '" + value + "' to a decimal");
          }

          // formatter.setDecimalSeparatorAlwaysShown(true);
          Number num = formatter.parse(String.valueOf(value));

          return num.doubleValue();
        } catch (ParseException pe) {
          pe.printStackTrace();
        }
      } else if (value instanceof Double) {
        if (log.isDebugEnabled()) {
          log.debug("value (" + value + ") instance of Double");
          log.debug("returning double: " + formatter.format(value));
        }

        return formatter.format(value);
      } // end if-else
    }   // end if-else

    throw new ConversionException("Could not convert " + value + " to " + type.getName() + "!");
  } // end method convert

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @param  df  DOCUMENT ME!
   */
  public void setDecimalFormatter(DecimalFormat df) {
    this.formatter = df;
  }
} // end class CurrencyConverter
