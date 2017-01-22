package com.akamai;

import java.time.LocalDate;
import java.util.List;

import org.openqa.selenium.support.PageFactory;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.akamai.components.SearchResults;
import com.akamai.models.testdata.SearchData;
import com.akamai.models.testdata.JobData;
import com.akamai.pages.JobSearch;
import com.akamai.pages.JobDescription;

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
  public void testJobSearch(SearchData searchData, JobData jobData) {
    openPage(baseUrl);
    searchJob(searchData);
    openJobDescription(jobData);
  }

  /**
   * Step open page.
   *
   * @param url to open
   */
  private void openPage(String url) {
    homePage.open(url);
  }

  /**
   * Step search jobs
   *
   * @param searchData test data
   */
  private void searchJob(SearchData searchData) {
    String keyword = searchData.getKeyword();
    String loc = searchData.getLocation();

    SearchResults results = homePage.searchPanel().searchFor(keyword, loc);
    int totalResults = parseInt(results.findTotalResults().trim());
    List<String> jobTitles = results.findJobTitlesContains(searchData.getTitle());

    assertThat(totalResults).isEqualTo(searchData.getTotalResults());
    assertThat(jobTitles).hasSize(searchData.getMatchedResults());
  }

  /**
   * Step open job description
   *
   * @param jobData test data
   */
  private void openJobDescription(JobData jobData) {
    String title = jobData.getTitle();
    LocalDate date = jobData.getPostDate();

    JobDescription vacancy = homePage.searchResults().openFirstJobByTitle(title);
    LocalDate postDate = parseToDate(vacancy.findPostDate().trim());

    assertThat(postDate).isEqualTo(date);
  }
}
