package com.infoshareacademy.mfinance.core.models.bossa;

import com.infoshareacademy.mfinance.core.utils.LocalDateUtil;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class QuotationTest {



    private String name = "USD";
    private LocalDate date = LocalDateUtil.parseCSV("20160103");
    private BigDecimal close = new BigDecimal("100");
    private Quotation quotation;

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