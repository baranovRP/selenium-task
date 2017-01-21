package com.akamai.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author baranov.r.p
 */
public final class Util {

  public static LocalDate parseToDate(String text) {
    return LocalDate.parse(text, DateTimeFormatter.ofPattern("MMM dd, yyyy"));
  }
}
