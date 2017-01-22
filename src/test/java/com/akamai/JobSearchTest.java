package com.akamai;

import java.time.LocalDate;
import java.util.List;

import org.openqa.selenium.support.PageFactory;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.akamai.components.SearchResults;
import com.akamai.models.testdata.SearchData;
import com.akamai.models.testdata.VacancyData;
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
  public void testJobSearch(SearchData searchData, VacancyData vacancyData) {
    openPage(baseUrl);
    searchJob(searchData);
    openVacancy(vacancyData);
  }

  private void openPage(String url) {
    homePage.open(url);
  }

  private void searchJob(SearchData searchData) {
    SearchResults results = homePage.searchPanel()
      .searchFor(searchData.getKeyword(), searchData.getLocation());
    int totalResults = parseInt(results.findTotalResults().trim());
    List<String> jobTitles = results.findJobTitlesContains(searchData.getTitle());

    assertThat(totalResults).isEqualTo(searchData.getTotalResults());
    assertThat(jobTitles).hasSize(searchData.getMatchedResults());
  }

  private void openVacancy(VacancyData vacancyData) {
    VacancyContent vacancy = homePage.searchResults()
      .openFirstJobByTitle(vacancyData.getTitle());
    LocalDate postDate = parseToDate(vacancy.getPostDate().trim());
    assertThat(postDate).isEqualTo(vacancyData.getPostDate());
  }
}
