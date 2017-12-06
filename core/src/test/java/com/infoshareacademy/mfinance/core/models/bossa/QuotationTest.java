package com.infoshareacademy.mfinance.core.models.bossa;

import com.infoshareacademy.mfinance.core.utils.LocalDateUtil;
import org.junit.Test;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class QuotationTest {
    private final LocalDate date = LocalDateUtil.parseCSV("20160103");
    private final BigDecimal close = new BigDecimal("100");

    @Test
    public void shouldInstantiateQuotation() {
        Quotation quotation = new Quotation("USD", date, close);
        assertThat(quotation, not(equalTo(nullValue())));
    }
}