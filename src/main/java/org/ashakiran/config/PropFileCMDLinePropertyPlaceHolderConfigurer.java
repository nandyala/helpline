package org.ashakiran.config;

import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import org.springframework.core.io.FileSystemResource;

import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.util.PropertiesPersister;
import org.springframework.util.StringUtils;


/**
 * DOCUMENT ME!
 *
 * @author   $author$
 * @version  $Revision$, $Date$
 */
public class PropFileCMDLinePropertyPlaceHolderConfigurer extends PropertyPlaceholderConfigurer {
  //~ Static fields/initializers ---------------------------------------------------------------------------------------

  private static final Log log = LogFactory.getLog(PropFileCMDLinePropertyPlaceHolderConfigurer.class);

  //~ Instance fields --------------------------------------------------------------------------------------------------

  /** DOCUMENT ME! */
  @SuppressWarnings("unchecked")
  protected Map  commandLineParams;
  private String commandLinePropFileKey = "entPropFile";

  private String commandLinePropFileName = null;

  private Properties priorityProperties       = null;
  private boolean    searchSystemEnvironment  = true;
  private boolean    triedCommandLinePropFile = false;

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @return  DOCUMENT ME!
   */
  public String getCommandLinePropFileKey() {
    return commandLinePropFileKey;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @param  commandLineParams  DOCUMENT ME!
   */
  @SuppressWarnings("unchecked")
  public void setCommandLineParams(Map commandLineParams) {
    this.commandLineParams = commandLineParams;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @param  commandLinePropFileKey  DOCUMENT ME!
   */
  public void setCommandLinePropFileKey(String commandLinePropFileKey) {
    this.commandLinePropFileKey = commandLinePropFileKey;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * @see  org.springframework.beans.factory.config.PropertyPlaceholderConfigurer#setSearchSystemEnvironment(boolean)
   */
  @Override public void setSearchSystemEnvironment(boolean searchSystemEnvironment) {
    this.searchSystemEnvironment = searchSystemEnvironment;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * @see  org.springframework.beans.factory.config.PropertyPlaceholderConfigurer#resolvePlaceholder(java.lang.String,java.util.Properties,
   *       int)
   */
  @Override protected String resolvePlaceholder(String placeholder, Properties props, int systemPropertiesMode) {
    // Resolve using command line parameters and command line prop file.
    // commandLine parameter has higher priority
    try {
      String result = null;

      if (this.commandLineParams != null) {
        Properties commandProps = (Properties) commandLineParams.get("jobProps");

        if (commandProps != null) {
          result = super.resolvePlaceholder(placeholder, commandProps);
        }
      }

      if (result != null) {
        log.info("Resolved " + placeholder + " from command line params.");
        logPlaceholderValue(placeholder, result);

        return result;
      }

      if ((this.priorityProperties == null) && !triedCommandLinePropFile) {
        triedCommandLinePropFile = true;

        try {
          String propFileName = System.getProperty(this.getCommandLinePropFileKey());
          log.info("Load command line prop file: " + propFileName);

          if (StringUtils.hasText(propFileName)) {
            PropertiesPersister propertiesPersister = new DefaultPropertiesPersister();
            FileSystemResource  r                   = new FileSystemResource(propFileName);
            commandLinePropFileName = r.getFilename();
            this.priorityProperties = new Properties();
            propertiesPersister.load(this.priorityProperties, r.getInputStream());
            propertiesPersister = null;
            r                   = null;
          }
        } catch (Exception e) {
          // Simply let it go - not required to provide the prop file
        }
      }

      if (this.priorityProperties != null) {
        result = super.resolvePlaceholder(placeholder, this.priorityProperties);
      }

      if (result != null) {
        log.info("Resolved " + placeholder + " from command line prop file " + commandLinePropFileKey + ": "
          + this.commandLinePropFileName + ".");
        logPlaceholderValue(placeholder, result);

        return result;
      }
    } catch (Exception e) {
      // simply let it go.
    } // end try-catch

    // return super.resolvePlaceholder(placeholder, props, systemPropertiesMode);
    String propVal = null;

    if (systemPropertiesMode == SYSTEM_PROPERTIES_MODE_OVERRIDE) {
      propVal = resolveSystemProperty(placeholder);
    }

    if (propVal != null) {
      logPlaceholderValue(placeholder, propVal);

      return propVal;
    }

    if (propVal == null) {
      propVal = resolvePlaceholder(placeholder, props);
    }

    if (propVal != null) {
      log.info("Resolved " + placeholder + " from files.");
      logPlaceholderValue(placeholder, propVal);

      return propVal;
    }

    if ((propVal == null) && (systemPropertiesMode == SYSTEM_PROPERTIES_MODE_FALLBACK)) {
      propVal = resolveSystemProperty(placeholder);
    }

    if (propVal != null) {
      log.info("Resolved " + placeholder + " from system properties fall back.");
      logPlaceholderValue(placeholder, propVal);
    }

    return propVal;
  } // end method resolvePlaceholder

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * @see  org.springframework.beans.factory.config.PropertyPlaceholderConfigurer#resolveSystemProperty(java.lang.String)
   */
  @Override protected String resolveSystemProperty(String key) {
    try {
      String value = System.getProperty(key);

      if (value != null) {
        log.info("Resolved " + key + " from JVM params (-D notation).");
      }

      if ((value == null) && this.searchSystemEnvironment) {
        value = System.getenv(key);

        if (value != null) {
          log.info("Resolved " + key + " from Shell System Environment Variables.");
        }
      }

      return value;
    } catch (Throwable ex) {
      if (logger.isDebugEnabled()) {
        logger.debug("Could not access system property '" + key + "': " + ex);
      }

      return null;
    }
  } // end method resolveSystemProperty

  //~ ------------------------------------------------------------------------------------------------------------------

  private void logPlaceholderValue(String placeholder, String result) {
    if (placeholder.toLowerCase().contains("password") || placeholder.toLowerCase().contains("username")) {
      log.info(placeholder + " is " + "XXXXXXXXXXXXX");
    } else {
      log.info(placeholder + " is " + result);
    }
  }


} // end class PropFileCMDLinePropertyPlaceHolderConfigurer
