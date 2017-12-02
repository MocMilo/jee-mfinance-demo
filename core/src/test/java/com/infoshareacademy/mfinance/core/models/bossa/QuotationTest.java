package com.infoshareacademy.mfinance.core.models.bossa;

import com.infoshareacademy.mfinance.core.utils.LocalDateUtil;
import org.junit.Test;
import java.math.BigDecimal;
import java.time.LocalDate;

public class QuotationTest {
    private String name = "USD";
    private LocalDate date = LocalDateUtil.parseCSV("20160103");
    private BigDecimal close = new BigDecimal("100");
    private Quotation quotation;

    @Test
    public void shouldInstantiateQuotation() {
        quotation = new Quotation(name, date, close);
    }
}