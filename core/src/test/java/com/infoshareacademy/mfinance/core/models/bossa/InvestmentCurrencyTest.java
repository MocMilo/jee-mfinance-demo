package com.infoshareacademy.mfinance.core.models.bossa;

import com.infoshareacademy.mfinance.core.utils.LocalDateUtil;
import org.junit.Before;
import org.junit.Test;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InvestmentCurrencyTest {
    private String name = "USD";
    private LocalDate date = LocalDateUtil.parseCSV("20160103");
    private BigDecimal close = new BigDecimal("100");
    private Quotation quotation;
    private List<Quotation> quotationList = new ArrayList<>();
    private InvestmentCurrency investmentCurrency;

    @Before
    public void init(){
        Quotation quotation = new Quotation(name,date,close );
        quotationList.add(quotation);
    }

    @Test
    public void shouldInstantiateValidInvestmentCurrency(){
        investmentCurrency = new InvestmentCurrency(name, quotationList);
    }
}