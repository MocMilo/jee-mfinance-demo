package com.infoshareacademy.mfinance.core.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateUtil {
    private static final String csvDateFormat = "yyyyMMdd";
    private static final String formDateFormat = "yyyy-MM-dd";

    public static LocalDate parseCSV(String dateValue) {
        return LocalDateUtil.parse(csvDateFormat, dateValue);
    }

    public static LocalDate parseForm(String dateValue) {
        return LocalDateUtil.parse(formDateFormat, dateValue);
    }

    private static LocalDate parse(String pattern, String value) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDate.parse(value, formatter);
    }
}






