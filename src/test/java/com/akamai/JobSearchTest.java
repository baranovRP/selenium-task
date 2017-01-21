package com.akamai;

import java.time.LocalDate;
import java.util.List;

import org.openqa.selenium.support.PageFactory;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.akamai.components.SearchResults;
import com.akamai.pages.JobSearch;
import com.akamai.pages.VacancyContent;

import static com.akamai.util.Util.parseToDate;
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

    VacancyContent vacancy =
      searchResults.openFirstVacancyByTitle("Senior Software Development Engineer in Test - LUNA");
    LocalDate postDate = parseToDate(vacancy.getPostDate().trim());
    assertThat(postDate).isEqualTo(parseToDate("Dec 06, 2016"));
  }
}
