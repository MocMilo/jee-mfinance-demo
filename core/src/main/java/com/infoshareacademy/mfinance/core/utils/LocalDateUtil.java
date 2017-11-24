package com.infoshareacademy.mfinance.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(LocalDateUtil.class);
    private static final String csvDateFormat = "yyyyMMdd";
    private static final String formDateFormat = "yyyy-MM-dd";
    private static DateTimeFormatter formatter;
    private static LocalDate localDate;

    public static LocalDate parseCSV(String dateValue) {
    try {
        formatter = DateTimeFormatter.ofPattern(csvDateFormat);
        localDate = LocalDate.parse(dateValue, formatter);
    }catch (Exception e){
        LOGGER.error("Failed to parse to LocalDate from csv, value:{} {}", dateValue, e.getMessage());
    }
        return localDate;
    }

    public static LocalDate parseForm(String dateValue) {
        try {
            formatter = DateTimeFormatter.ofPattern(formDateFormat);
            localDate = LocalDate.parse(dateValue, formatter);
        }catch (Exception e){
            LOGGER.error("Failed to parse to LocalDate form, value:{} {}", dateValue, e.getMessage());
        }
        return localDate;
    }
}



