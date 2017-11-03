package com.infoshare.mfinance.core.models.bossa;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class InvestmentCurrencyTest {

    private static final String DATE_FORMAT = "yyyyMMdd";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

    private String name = "USD";
    private LocalDate date = LocalDate.parse("20160103", FORMATTER);
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

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWhenInvestmentCurrencyNameIsNull(){
        investmentCurrency = new InvestmentCurrency(null, quotationList);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWhenInvestmentCurrrencyNameIsEmpty(){
        investmentCurrency = new InvestmentCurrency("", quotationList);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWhenInvestmentCurrencyQuotationListIsNull(){
        investmentCurrency = new InvestmentCurrency(name, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWhenInvestmentCurrencyQuotationListIsEmpty(){
        quotationList = new ArrayList<>();
        investmentCurrency = new InvestmentCurrency(name, quotationList);
    }

}