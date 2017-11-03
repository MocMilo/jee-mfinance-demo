package com.infoshare.mfinance.core.models.bossa;

import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class QuotationTest {

    private static final String DATE_FORMAT = "yyyyMMdd";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

    String name = "USD";
    LocalDate date = LocalDate.parse("20160103", FORMATTER);
    BigDecimal close = new BigDecimal("100");
    Quotation quotation;

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenNameIsNullValue() {
        quotation = new Quotation(null, date, close);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenNameIsEmptyString() {
        quotation = new Quotation("", date, close);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenDateIsNullValue() {
        quotation = new Quotation(name, null, close);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenCloseIsNullValue() {
        quotation = new Quotation(name, date, null);
    }

}