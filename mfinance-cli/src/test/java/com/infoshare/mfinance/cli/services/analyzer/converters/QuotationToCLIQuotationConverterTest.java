package com.infoshare.mfinance.cli.services.analyzer.converters;

import com.infoshare.core.models.bossa.Quotation;
import com.infoshare.mfinance.cli.model.results.embeded.CLIQuotation;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class QuotationToCLIQuotationConverterTest {

    private Quotation quotation;
    private String name = "USD";
    private LocalDate date = LocalDate.parse("2015-09-08");
    private BigDecimal moneyValue = new BigDecimal("100.00");
    private QuotationToCLIQuotationConverter converter = new QuotationToCLIQuotationConverter();


    @Before
    public void init() {
        quotation = new Quotation(name, date, moneyValue);
    }

    @Test
    public void checkIfCliQuotationHasEqualValuesToQuotation() throws Exception {

        CLIQuotation cliQuotation = converter.convertFrom(quotation);

        assertThat(quotation.getName(), is(equalTo(cliQuotation.getName())));
        assertThat(quotation.getDate(), is(equalTo(cliQuotation.getDate())));
        assertThat(quotation.getClose(), is(equalTo((cliQuotation.getClose()))));
        assertThat(quotation.getDeltaClose(), is(equalTo((cliQuotation.getDeltaClose()))));
    }
}