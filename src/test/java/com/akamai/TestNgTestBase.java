package com.akamai;

import java.io.IOException;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import ru.stqa.selenium.factory.WebDriverPool;

import static com.akamai.util.PropertyLoader.loadCapabilities;
import static com.akamai.util.PropertyLoader.loadProperty;
import static java.lang.Long.parseLong;

/**
 * Base class for TestNG-based test classes with initial setup, tearUp, tearDown.
 * Based on @see https://github.com/barancev/webdriver-testng-archetype
 *
 * @author baranov.r.p
 */
public class TestNgTestBase {

  protected static long timeOutInSec;

  protected static String gridHubUrl;
  protected static String baseUrl;
  protected static Capabilities capabilities;

  protected WebDriver driver;

  public static long getTimeOutInSec() {
    return timeOutInSec;
  }

  @BeforeSuite
  public void initTestSuite() throws IOException {
    baseUrl = loadProperty("site.url");
    gridHubUrl = loadProperty("grid.url");
    timeOutInSec = parseLong(loadProperty("timeout.in.sec"));
    if ("".equals(gridHubUrl)) {
      gridHubUrl = null;
    }
    capabilities = loadCapabilities();
  }

  @BeforeMethod
  public void initWebDriver() {
    driver = WebDriverPool.DEFAULT.getDriver(gridHubUrl, capabilities);
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() {
    WebDriverPool.DEFAULT.dismissAll();
  }
}
