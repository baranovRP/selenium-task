package com.akamai.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Class provides methods for parsing date.
 *
 * @author baranov.r.p
 */
public final class DateTimeUtils {

  /**
   * Parse date. To prevent issues related to different locales,
   * the date parsed with <i>en_GB</i> locale
   *
   * @param text that represent the date
   * @return LocalDate
   */
  public static LocalDate parseToDate(String text) {
    return LocalDate.parse(text,
      DateTimeFormatter.ofPattern("MMM dd, yyyy").withLocale(Locale.UK));
  }
}
