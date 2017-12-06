package com.infoshareacademy.mfinance.core.models.bossa;

import com.infoshareacademy.mfinance.core.utils.LocalDateUtil;
import org.junit.Before;
import org.junit.Test;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class InvestmentCurrencyTest {
    private final String name = "USD";
    private final LocalDate date = LocalDateUtil.parseCSV("20160103");
    private final BigDecimal close = new BigDecimal("100");
    private List<Quotation> quotationList = new ArrayList<>();

    @Before
    public void init(){
        Quotation quotation = new Quotation(name,date,close );
        quotationList.add(quotation);
    }

    @Test
    public void shouldInstantiateValidInvestmentCurrency(){
        InvestmentCurrency investmentCurrency = new InvestmentCurrency(name, quotationList);
        assertThat(investmentCurrency, not(equalTo(nullValue())));
    }
}