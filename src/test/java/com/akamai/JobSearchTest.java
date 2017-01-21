package com.akamai;

import org.openqa.selenium.support.PageFactory;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.akamai.components.SearchResults;
import com.akamai.pages.JobSearch;

public class JobSearchTest extends TestNgTestBase {

  private JobSearch homePage;

  @BeforeMethod
  public void initPageObjects() {
    homePage = PageFactory.initElements(driver, JobSearch.class);
  }

  @Test
  public void testJobSearch() {
    homePage.open(baseUrl);

    SearchResults searchResults =
      homePage.searchPanel().searchFor("test", "Krakow");
  }
}
