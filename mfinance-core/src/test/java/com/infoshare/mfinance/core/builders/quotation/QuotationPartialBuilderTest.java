package com.infoshare.mfinance.core.builders.quotation;

import com.infoshare.mfinance.core.models.bossa.Quotation;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;

public class QuotationPartialBuilderTest {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final File resourcesDirectory = new File("src/test/resources/csv/currencies/EUR-test.txt");
    private final int NUMBER_OF_ROWS = 4622;
    private QuotationPartialBuilder quotationPartialBuilder = new QuotationPartialBuilder();
    ;

    @Before
    public void loadQuotations() {

        String filePath = resourcesDirectory.getAbsolutePath();
        this.quotationPartialBuilder.parseQuotationsFromFile(filePath);
    }

    @Test
    public void shouldReturnNumberOfQuotations() throws Exception {

        int loadedNumberOfQuotations = quotationPartialBuilder.getNumberOfQuotations();
        assertThat(loadedNumberOfQuotations, is(equalTo(NUMBER_OF_ROWS)));
    }

    @Test
    public void shouldReturnQuotation() {

        Quotation quotation = quotationPartialBuilder.getQuotation(0);
        LocalDate expectedDate = LocalDate.parse("1999-01-01", formatter);
        String expectedName = "EUR";
        BigDecimal expectedValue = new BigDecimal("4.0925");

        assertThat(quotation, not(equalTo(nullValue())));
        assertThat(quotation.getName(), not(equalTo(nullValue())));
        assertThat(quotation.getClose(), not(equalTo(nullValue())));

        assertThat(quotation.getName(), is(equalTo(expectedName)));
        assertThat(quotation.getDate(), is(equalTo(expectedDate)));
        assertThat(quotation.getClose(), is(equalTo(expectedValue)));
    }

    @Test(expected = Exception.class)
    public void shouldFailWhenFileNotFound() {
        String notExistingFilePath = "/notexistingfilepath";
        new QuotationPartialBuilder().parseQuotationsFromFile(notExistingFilePath);
    }
}