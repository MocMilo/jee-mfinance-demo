package com.infoshare.core.factories;

import com.infoshare.core.models.bossa.Quotation;
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

public class QuotationFactoryTest {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final File resourcesDirectory = new File("src/test/resources/csv/currencies/EUR.txt");
    private final int NUMBEROFROWS = 4622;
    private QuotationFactory quotationFactory= new QuotationFactory();;

    @Before
    public void loadQuotations(){

        String filePath = resourcesDirectory.getAbsolutePath();
        this.quotationFactory.loadDataFromFile(filePath);
    }

    @Test
    public void testGetNumberOfQuotations() throws Exception {

        int loadedNumberOfQuotations = quotationFactory.getNumberOfQuotations();
        assertThat(loadedNumberOfQuotations, is(equalTo(NUMBEROFROWS)));
    }

    @Test
    public void testGetFirstQuotation() throws Exception {

        Quotation quotation = quotationFactory.getQuotation(0);
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

}