package com.akamai;

import org.testng.annotations.DataProvider;

/**
 * @author baranov.r.p
 */
public class DataProviderSource {
  @DataProvider
  public static Object[][] jobSearch() {
    return new Object[][]{
      {"test", "Krakow", 11, "Software Development Engineer in Test",
        4, "Senior Software Development Engineer in Test - LUNA", "Dec 06, 2016"}
    };
  }
}
