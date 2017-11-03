package com.infoshare.mfinance.core.models.bossa;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class InvestmentFundTest {

    private static final String DATE_FORMAT = "yyyyMMdd";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);

    private String name = "USD";
    private LocalDate date = LocalDate.parse("20160103", FORMATTER);
    private BigDecimal close = new BigDecimal("100");


    private List<Quotation> quotationList = new ArrayList<>();
    private InvestmentFund investmentFund;

    @Before
    public void init(){
        Quotation quotation = new Quotation(name,date,close );
        quotationList.add(quotation);
    }

    @Test
    public void shouldInstantiateValidInvestmentFund(){
        investmentFund = new InvestmentFund(name, quotationList);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWhenInvestmentFundNameIsNull(){
        investmentFund = new InvestmentFund(null, quotationList);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWhenInvestmentFundNameIsEmpty(){
        investmentFund = new InvestmentFund("", quotationList);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWhenInvestmentFundQuotationListIsNull(){
        investmentFund = new InvestmentFund(name, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldFailWhenInvestmentFundQuotationListIsEmpty(){
        quotationList = new ArrayList<>();
        investmentFund = new InvestmentFund(name, quotationList);
    }

}