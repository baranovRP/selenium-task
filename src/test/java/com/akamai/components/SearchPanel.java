package com.akamai.components;

import org.openqa.selenium.WebDriver;

import com.akamai.pages.Page;

import static org.openqa.selenium.By.cssSelector;

/**
 * This class represents a fragment of the page, component <b>Search Panel</b>.
 * Contains selectors for HTML elements, methods to interact
 * with UI controls (e.g. text fields, dropdowns etc.).
 *
 * @author baranov.r.p
 */
public class SearchPanel extends Page {

  /**
   * CSS selectors for UI elements
   */
  private static final String SEARCH_BANNER = "#job_search_banner";

  private static final String KEYWORD_INPUT = SEARCH_BANNER + " .search_input";

  private static final String LOC_INPUT = SEARCH_BANNER + " #jLocInputHldr";
  private static final String LOC_FIELD = SEARCH_BANNER + " .search-field input";
  private static final String LOC_HIGHLIGHTED_EL = LOC_INPUT + " li.highlighted";

  private static final String SEARCH_BTN = SEARCH_BANNER + " .search_btn";


  public SearchPanel(WebDriver webDriver) {
    super(webDriver);
  }

  /**
   * Search by specified conditions.
   *
   * @param keyword  any (e.g. job title, category)
   * @param location place to work (city, country)
   * @return SearchResults component, with results
   */
  public SearchResults searchFor(String keyword, String location) {
    return insertKeyword(keyword).insertLocation(location).doSearch();
  }

  /**
   * Clear input field and insert any keyword.
   *
   * @param text any keyword (e.g. job title, category)
   * @return SearchPanel component
   */
  public SearchPanel insertKeyword(String text) {
    driver.findElement(cssSelector(KEYWORD_INPUT)).clear();
    driver.findElement(cssSelector(KEYWORD_INPUT)).sendKeys(text);
    return this;
  }

  /**
   * Insert location is done by clicking on input, inserting text
   * and clicking on highlighted location according to inserted text.
   *
   * @param text any place to work (city, country)
   * @return SearchPanel component
   */
  public SearchPanel insertLocation(String text) {
    driver.findElement(cssSelector(LOC_INPUT)).click();
    driver.findElement(cssSelector(LOC_FIELD)).sendKeys(text);
    driver.findElement(cssSelector(LOC_HIGHLIGHTED_EL)).click();

    return this;
  }

  /**
   * Do search by clicking on <b>Search</b> button.
   *
   * @return SearchResults component, with results
   */
  public SearchResults doSearch() {
    driver.findElement(cssSelector(SEARCH_BTN)).click();
    return new SearchResults(driver);
  }
}
