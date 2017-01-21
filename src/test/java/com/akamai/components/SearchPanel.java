package com.akamai.components;

import org.openqa.selenium.WebDriver;

import com.akamai.pages.Page;

import static org.openqa.selenium.By.cssSelector;

/**
 * @author baranov.r.p
 */
public class SearchPanel extends Page {

  private static final String SEARCH_BANNER = "#job_search_banner";

  private static final String KEYWORD_INPUT = SEARCH_BANNER + " .search_input";

  private static final String LOC_INPUT = SEARCH_BANNER + " #jLocInputHldr";
  private static final String LOC_FIELD = SEARCH_BANNER + " .search-field input";
  private static final String LOC_HIGHLIGHTED_EL = LOC_INPUT + " li.highlighted";

  private static final String SEARCH_BTN = SEARCH_BANNER + " .search_btn";


  public SearchPanel(WebDriver webDriver) {
    super(webDriver);
  }

  public SearchResults searchFor(String keyword, String location) {
    return insertKeyword(keyword).insertLocation(location).doSearch();
  }

  public SearchPanel insertKeyword(String text) {
    driver.findElement(cssSelector(KEYWORD_INPUT)).clear();
    driver.findElement(cssSelector(KEYWORD_INPUT)).sendKeys(text);
    return this;
  }

  public SearchPanel insertLocation(String text) {
    driver.findElement(cssSelector(LOC_INPUT)).click();
    driver.findElement(cssSelector(LOC_FIELD)).sendKeys(text);
    driver.findElement(cssSelector(LOC_HIGHLIGHTED_EL)).click();

    return this;
  }

  public SearchResults doSearch() {
    driver.findElement(cssSelector(SEARCH_BTN)).click();
    return new SearchResults(driver);
  }
}
