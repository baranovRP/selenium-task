package com.akamai.model.testdata;

import java.util.Objects;

/**
 * @author baranov.r.p
 */
public class SearchData {
  private String keyword;
  private String location;
  private int totalResults;
  private String title;
  private int matchedResults;

  public SearchData(String keyword, String location,
                    int totalResults, String title, int matchedResults) {
    this.keyword = keyword;
    this.location = location;
    this.totalResults = totalResults;
    this.title = title;
    this.matchedResults = matchedResults;
  }

  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public int getTotalResults() {
    return totalResults;
  }

  public void setTotalResults(int totalResults) {
    this.totalResults = totalResults;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getMatchedResults() {
    return matchedResults;
  }

  public void setMatchedResults(int matchedResults) {
    this.matchedResults = matchedResults;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof SearchData)) return false;
    SearchData that = (SearchData) o;
    return getTotalResults() == that.getTotalResults() &&
      getMatchedResults() == that.getMatchedResults() &&
      Objects.equals(getKeyword(), that.getKeyword()) &&
      Objects.equals(getLocation(), that.getLocation()) &&
      Objects.equals(getTitle(), that.getTitle());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getKeyword(), getLocation(),
      getTotalResults(), getTitle(), getMatchedResults());
  }
}
