package com.akamai.pages;

import org.openqa.selenium.WebDriver;

import com.akamai.components.SearchPanel;
import com.akamai.components.SearchResults;

/**
 * This class represents a <b>Home Page</b>.
 * Contains components, methods to interact
 * with UI controls (e.g. text fields, dropdowns etc.).
 *
 * @author baranov.r.p
 */
public class JobSearch extends Page {

  private final SearchPanel searchPanel;
  private final SearchResults searchResults;

  /**
   * Constructor registering components on the page.
   *
   * @param webDriver
   */
  public JobSearch(WebDriver webDriver) {
    super(webDriver);
    this.searchPanel = new SearchPanel(webDriver);
    this.searchResults = new SearchResults(webDriver);
  }

  /**
   * Getter of <b>Search Panel</b> component on Home page.
   *
   * @return Search Panel
   */
  public SearchPanel searchPanel() {
    return searchPanel;
  }

  /**
   * Getter of <b>Search Results</b> component on Home page.
   *
   * @return Search Results
   */
  public SearchResults searchResults() {
    return searchResults;
  }

  /**
   * Open page with provided url.
   *
   * @param baseUrl of test page
   * @return Home page
   */
  public JobSearch open(String baseUrl) {
    driver.get(baseUrl);
    return this;
  }
}
