package com.akamai.pages;

import org.openqa.selenium.WebDriver;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

/**
 * This class represents a page with <b>Job Description</b>.
 * Contains selectors for HTML elements, methods to interact
 * with UI controls (e.g. text fields, dropdowns etc.).
 *
 * @author baranov.r.p
 */
public class JobDescription extends Page {

  /**
   * CSS selectors for UI elements.
   */
  private static final String CONTENT = ".full_content";
  private static final String POST_DATE = CONTENT + " .job_post_date .field_value";


  public JobDescription(WebDriver webDriver) {
    super(webDriver);
  }

  /**
   * Find postdate of the job.
   *
   * @return postdate
   */
  public String findPostDate() {
    return wait.until(visibilityOfElementLocated(cssSelector(POST_DATE))).getText();
  }
}
