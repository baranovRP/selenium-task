package com.akamai.models.testdata;

import java.time.LocalDate;
import java.util.Objects;

import static com.akamai.util.DateTimeUtils.parseToDate;

/**
 * @author baranov.r.p
 */
public class VacancyData {
  private String title;
  private LocalDate postDate;

  public VacancyData(String title, LocalDate postDate) {
    this.title = title;
    this.postDate = postDate;
  }

  public VacancyData(String title, String date) {
    this.title = title;
    setPostDate(date);
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public LocalDate getPostDate() {
    return postDate;
  }

  public void setPostDate(LocalDate postDate) {
    this.postDate = postDate;
  }

  public void setPostDate(String date) {
    this.postDate = parseToDate(date);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof VacancyData)) return false;
    VacancyData that = (VacancyData) o;
    return Objects.equals(getTitle(), that.getTitle()) &&
      Objects.equals(getPostDate(), that.getPostDate());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getTitle(), getPostDate());
  }
}
