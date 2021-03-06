package com.akamai.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.akamai.TestNgTestBase.getTimeOutInSec;

/**
 * Abstract class representation of a Page in the UI. Page object pattern
 *
 * @author baranov.r.p
 */
public abstract class Page {

  protected WebDriver driver;
  protected WebDriverWait wait;

  /**
   * Constructor injecting the WebDriver interface
   *
   * @param driver Webdriver
   */
  public Page(WebDriver driver) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, getTimeOutInSec());
    driver.manage().window().maximize();
  }
}
