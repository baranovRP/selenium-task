package com.akamai.components;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.akamai.pages.Page;
import com.akamai.pages.VacancyContent;

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
   * CSS selectors for UI elements
   */
  private static final String CONTENT = "#portal_content";
  private static final String NUMBER_OF_RESULTS = CONTENT + " .total_results";
  private static final String ALL_ELS = CONTENT + " .full_content .job_list_row";


  public SearchResults(WebDriver webDriver) {
    super(webDriver);
  }

  public String findTotalResults() {
    return wait.until(visibilityOfElementLocated(cssSelector(NUMBER_OF_RESULTS))).getText();
  }

  public List<WebElement> findAllJobElsOnPage() {
    return wait.until(visibilityOfAllElementsLocatedBy(cssSelector(ALL_ELS)));
  }

  public List<String> findJobTitlesContains(String text) {
    return findAllJobElsOnPage().stream()
      .map(el -> el.findElement(cssSelector("a")).getText().trim())
      .filter(el -> el.toLowerCase().contains(text.toLowerCase()))
      .collect(Collectors.toList());
  }

  public VacancyContent openJobByLinkText(String text) {
    wait.until(elementToBeClickable(linkText(text))).click();
    return new VacancyContent(driver);
  }

  public VacancyContent openFirstJobByTitle(String text) {
    return openJobByTitle(text, FIRST_EL);
  }

  public VacancyContent openJobByTitle(String text, int order) {
    WebElement linkEl = findAllJobElsOnPage().stream()
      .map(el -> el.findElement(cssSelector("a")))
      .filter(el -> el.getText().trim().toLowerCase().contains(text.toLowerCase()))
      .collect(Collectors.toList()).get(order);
    wait.until(elementToBeClickable(linkEl)).click();
    return new VacancyContent(driver);
  }
}
