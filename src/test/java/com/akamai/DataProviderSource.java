package com.akamai;

import org.testng.annotations.DataProvider;

import com.akamai.models.testdata.SearchData;
import com.akamai.models.testdata.VacancyData;

import static com.akamai.util.DateTimeUtils.parseToDate;

/**
 * @author baranov.r.p
 */
public class DataProviderSource {
  @DataProvider
  public static Object[][] jobSearch() {
    return new Object[][]{
      {new SearchData("test", "Krakow", 11,
        "Software Development Engineer in Test",
        4),
        new VacancyData("Senior Software Development Engineer in Test - LUNA",
          parseToDate("Dec 06, 2016"))
      }
    };
  }
}
