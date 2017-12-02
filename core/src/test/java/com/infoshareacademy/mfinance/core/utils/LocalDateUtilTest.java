package com.infoshareacademy.mfinance.core.utils;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

public class LocalDateUtilTest {
    private static final DateTimeFormatter testFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
    private LocalDate expected;

    @Before
    public void init() {
        expected = LocalDate.parse("20090101", testFormatter);
    }

    @Test
    public void shouldParseLocalDateFromCSVFormat() {
        String csvDate = "20090101";
        LocalDate result = LocalDateUtil.parseCSV(csvDate);
        assertThat(result, is(equalTo(expected)));
    }

    @Test
    public void shouldParseLocalDateFromFormFormat() {
        String csvDate = "2009-01-01";
        LocalDate result = LocalDateUtil.parseForm(csvDate);
        assertThat(result, is(equalTo(expected)));
    }

    @Test (expected = DateTimeParseException.class)
    public void shouldFailParseLocalDateFromInvalidString() {
        String csvDate = "2009-01-01formWrongDate";
        LocalDateUtil.parseForm(csvDate);
    }
}