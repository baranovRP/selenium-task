package com.akamai;

import java.util.List;

import org.openqa.selenium.support.PageFactory;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.akamai.components.SearchResults;
import com.akamai.pages.JobSearch;

import static java.lang.Integer.parseInt;
import static org.assertj.core.api.Assertions.assertThat;

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
    int numberOfResults = parseInt(searchResults.getNumberOfResults().trim());
    assertThat(numberOfResults).isEqualTo(11);

    List<String> roles =
      searchResults.getRoleTitlesContains("Software Development Engineer in Test");
    assertThat(roles).hasSize(4);
  }
}
