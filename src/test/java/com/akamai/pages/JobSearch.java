package com.akamai.pages;

import org.openqa.selenium.WebDriver;

import com.akamai.components.SearchPanel;
import com.akamai.components.SearchResults;

/**
 * Home page
 *
 * @author baranov.r.p
 */
public class JobSearch extends Page {

  private final SearchPanel searchPanel;
  private final SearchResults searchResults;


  public JobSearch(WebDriver webDriver) {
    super(webDriver);
    this.searchPanel = new SearchPanel(webDriver);
    this.searchResults = new SearchResults(webDriver);
  }

  public SearchPanel searchPanel() {
    return searchPanel;
  }

  public SearchResults searchResults() {
    return searchResults;
  }

  public JobSearch open(String baseUrl) {
    driver.get(baseUrl);
    return this;
  }
}
