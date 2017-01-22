package com.akamai.components;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.akamai.pages.Page;
import com.akamai.pages.JobDescription;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.linkText;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElementsLocatedBy;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

/**
 * This class represents a fragment of the page, component <b>Search Results</b>.
 * Contains selectors for HTML elements, methods to interact
 * with UI controls (e.g. text fields, dropdowns etc.).
 *
 * @author baranov.r.p
 */
public class SearchResults extends Page {

  private static final int FIRST_EL = 0;

  /**
   * CSS selectors for UI elements.
   */
  private static final String CONTENT = "#portal_content";
  private static final String NUMBER_OF_RESULTS = CONTENT + " .total_results";
  private static final String ALL_ELS = CONTENT + " .full_content .job_list_row";


  public SearchResults(WebDriver webDriver) {
    super(webDriver);
  }

  /**
   * Find total results.
   *
   * @return number
   */
  public String findTotalResults() {
    return wait.until(visibilityOfElementLocated(cssSelector(NUMBER_OF_RESULTS))).getText();
  }

  /**
   * Find all Job's WebElements on Page.
   *
   * @return list of WebElements
   */
  public List<WebElement> findAllJobElsOnPage() {
    return wait.until(visibilityOfAllElementsLocatedBy(cssSelector(ALL_ELS)));
  }

  /**
   * Find Job's titles that contain provided text.
   *
   * @param text that expected to be present in title
   * @return list of titles
   */
  public List<String> findJobTitlesContains(String text) {
    return findAllJobElsOnPage().stream()
      .map(el -> el.findElement(cssSelector("a")).getText().trim())
      .filter(el -> el.toLowerCase().contains(text.toLowerCase()))
      .collect(Collectors.toList());
  }

  /**
   * Open a job description by provided link's text.
   *
   * @param text in link
   * @return description of job
   */
  public JobDescription openJobByLinkText(String text) {
    wait.until(elementToBeClickable(linkText(text))).click();
    return new JobDescription(driver);
  }

  /**
   * Open job description of first matched title.
   *
   * @param text job title
   * @return description of job
   */
  public JobDescription openFirstJobByTitle(String text) {
    return openJobByTitle(text, FIRST_EL);
  }

  /**
   * Open job description of matched title by provided index of matched result.
   *
   * @param text  job title
   * @param index of matched job
   * @return description of job
   */
  public JobDescription openJobByTitle(String text, int index) {
    WebElement linkEl = findAllJobElsOnPage().stream()
      .map(el -> el.findElement(cssSelector("a")))
      .filter(el -> el.getText().trim().toLowerCase().contains(text.toLowerCase()))
      .collect(Collectors.toList()).get(index);
    wait.until(elementToBeClickable(linkEl)).click();
    return new JobDescription(driver);
  }
}
