package com.akamai;

import java.time.LocalDate;
import java.util.List;

import org.openqa.selenium.support.PageFactory;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.akamai.components.SearchResults;
import com.akamai.pages.JobSearch;
import com.akamai.pages.VacancyContent;

import static com.akamai.util.DateTimeUtils.parseToDate;
import static java.lang.Integer.parseInt;
import static org.assertj.core.api.Assertions.assertThat;

public class JobSearchTest extends TestNgTestBase {

  private JobSearch homePage;

  @BeforeMethod
  public void initPageObjects() {
    homePage = PageFactory.initElements(driver, JobSearch.class);
  }

  @Test(dataProvider = "jobSearch", dataProviderClass = DataProviderSource.class)
  public void testJobSearch(String keyword, String loc,
                            int total, String title,
                            int matched, String vacancy, String date) {
    openPage(baseUrl);
    searchJob(keyword, loc, total, title, matched);
    openVacancy(vacancy, date);
  }

  private void openPage(String url) {
    homePage.open(url);
  }

  private void searchJob(String keyword, String location,
                         int total, String jobTitle, int matched) {
    SearchResults searchResults = homePage.searchPanel().searchFor(keyword, location);
    int numberOfResults = parseInt(searchResults.getNumberOfResults().trim());
    List<String> roles = searchResults.getRoleTitlesContains(jobTitle);

    assertThat(numberOfResults).isEqualTo(total);
    assertThat(roles).hasSize(matched);
  }

  private void openVacancy(String jobTitle, String date) {
    VacancyContent vacancy = homePage.searchResults().openFirstVacancyByTitle(jobTitle);
    LocalDate postDate = parseToDate(vacancy.getPostDate().trim());
    assertThat(postDate).isEqualTo(parseToDate(date));
  }

}
