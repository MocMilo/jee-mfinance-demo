package com.infoshareacademy.mfinance.core.builders.quotation;

import com.infoshareacademy.mfinance.core.models.bossa.Quotation;
import com.infoshareacademy.mfinance.core.serialization.QuotationListCSVParser;
import com.infoshareacademy.mfinance.core.utils.LocalDateUtil;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;

public class QuotationListCSVParserTest {

    private final File resourcesDirectory = new File("src/test/resources/csv/currencies/EUR-test.txt");
    private final int NUMBER_OF_ROWS = 4622;
    private QuotationListCSVParser quotationListCSVParser;

    @Before
    public void loadQuotations() {
        String filePath = resourcesDirectory.getAbsolutePath();
        quotationListCSVParser = new QuotationListCSVParser(filePath);
        quotationListCSVParser.parseQuotationsFromFile();
    }

    @Test
    public void shouldReturnNumberOfQuotations() throws Exception {
        int loadedNumberOfQuotations = quotationListCSVParser.getNumberOfQuotations();
        assertThat(loadedNumberOfQuotations, is(equalTo(NUMBER_OF_ROWS)));
    }

    @Test
    public void shouldReturnQuotation() {
        Quotation quotation = quotationListCSVParser.getQuotation(0);
        LocalDate expectedDate = LocalDateUtil.parseForm("1999-01-01");
        String expectedName = "EUR";
        BigDecimal expectedValue = new BigDecimal("4.0925");
        assertThat(quotation, not(equalTo(nullValue())));
        assertThat(quotation.getName(), not(equalTo(nullValue())));
        assertThat(quotation.getClose(), not(equalTo(nullValue())));

        assertThat(quotation.getName(), is(equalTo(expectedName)));
        assertThat(quotation.getDate(), is(equalTo(expectedDate)));
        assertThat(quotation.getClose(), is(equalTo(expectedValue)));
    }
}