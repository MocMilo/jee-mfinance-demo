package com.infoshareacademy.mfinance.core.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateUtil {
    private static final String csvDateFormat = "yyyyMMdd";
    private static final String formDateFormat = "yyyy-MM-dd";
    private static DateTimeFormatter formatter;
    private static LocalDate localDate;

    public static LocalDate parseCSV(String dateValue) {
        formatter = DateTimeFormatter.ofPattern(csvDateFormat);
        localDate = LocalDate.parse(dateValue, formatter);
        return localDate;
    }

    public static LocalDate parseForm(String dateValue) {
        formatter = DateTimeFormatter.ofPattern(formDateFormat);
        localDate = LocalDate.parse(dateValue, formatter);
        return localDate;
    }
}



