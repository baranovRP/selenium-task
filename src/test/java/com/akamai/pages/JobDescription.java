package com.akamai.pages;

import org.openqa.selenium.WebDriver;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

/**
 * @author baranov.r.p
 */
public class JobDescription extends Page {

  private static final String CONTENT = ".full_content";
  private static final String POST_DATE = CONTENT + " .job_post_date .field_value";


  public JobDescription(WebDriver webDriver) {
    super(webDriver);
  }

  public String getPostDate() {
    return wait.until(visibilityOfElementLocated(cssSelector(POST_DATE))).getText();
  }
}
