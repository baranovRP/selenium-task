package com.akamai.util;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Initial boilerplate based on @see https://github.com/barancev/webdriver-testng-archetype
 * Class that extracts properties from the prop file.
 */
public class PropertyLoader {

  private static final String DEBUG_PROPERTIES = "/debug.properties";

  public static Capabilities loadCapabilities() {
    return loadCapabilities(System.getProperty("application.properties", DEBUG_PROPERTIES));
  }

  public static Capabilities loadCapabilities(String fromResource) {
    Properties props = new Properties();
    try {
      props.load(PropertyLoader.class.getResourceAsStream(fromResource));
    } catch (IOException e) {
      throw new RuntimeException("Can't find a resource with a name " + fromResource, e);
    }
    String capabilitiesFile = props.getProperty("capabilities");

    Properties capsProps = new Properties();
    try {
      capsProps.load(PropertyLoader.class.getResourceAsStream(capabilitiesFile));
    } catch (IOException e) {
      throw new RuntimeException("Can't find a resource with a name " + capabilitiesFile, e);
    }

    DesiredCapabilities capabilities = new DesiredCapabilities();
    for (String name : capsProps.stringPropertyNames()) {
      String value = capsProps.getProperty(name);
      if (value.toLowerCase().equals("true") || value.toLowerCase().equals("false")) {
        capabilities.setCapability(name, Boolean.valueOf(value));
      } else if (value.startsWith("file:")) {
        try {
          capabilities.setCapability(name,
            new File(".", value.substring(5)).getCanonicalFile().getAbsolutePath());
        } catch (IOException e) {
          throw new RuntimeException("Can't return the canonical form of pathname for " + value, e);
        }
      } else {
        capabilities.setCapability(name, value);
      }
    }

    return capabilities;
  }

  public static String loadProperty(String name) throws IOException {
    return loadProperty(name, System.getProperty("application.properties", DEBUG_PROPERTIES));
  }

  public static String loadProperty(String name, String fromResource) throws IOException {
    Properties props = new Properties();
    props.load(PropertyLoader.class.getResourceAsStream(fromResource));

    return props.getProperty(name);
  }
}
