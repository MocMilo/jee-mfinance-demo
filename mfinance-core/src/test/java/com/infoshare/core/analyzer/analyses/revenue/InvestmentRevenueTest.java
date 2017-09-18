package com.infoshare.core.analyzer.analyses.revenue;

import com.infoshare.core.models.analyses.criteria.InvestmentRevenueCriteria;
import com.infoshare.core.models.analyses.results.InvestmentRevenueResult;
import com.infoshare.core.models.configuration.Configuration;
import com.infoshare.core.models.exceptions.NoDataForCriteria;
import com.infoshare.core.configuration.ConfigurationProvider;
import com.infoshare.core.loader.MainContainerLoader;
import com.infoshare.core.models.bossa.Investment;
import com.infoshare.core.models.bossa.MainContainer;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class InvestmentRevenueTest  {

    // Test values (just as from .jsp form)
    private final   BigDecimal capital =  new BigDecimal(10000.00);
    private final   DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
    private final   LocalDate BUY_DATE = LocalDate.parse("20090910", formatter);
    private final   LocalDate SELL_DATE = LocalDate.parse("20170330", formatter);
    private final   String InvestmentName = "CHF";
    // application initialization
    private final Configuration configuration = new ConfigurationProvider().getConfiguration();
    private final MainContainerLoader mainContainerLoader = new MainContainerLoader(configuration);

    private MainContainer getMainContainerWithLoadeData(){
        // loading data
        mainContainerLoader.loadFunds();
        mainContainerLoader.loadCurrencies();
        return mainContainerLoader.getMainContainer();
    }

    @Test
    public void testGetResultWhenUserInputOutOfRange() throws Exception {
        /*LocalDate SELL_DATE = LocalDate.parse("20180104", formatter);

        MainContainer mc = this.getMainContainerWithLoadeData();

        // example analyses usage
        InvestmentRevenueCriteria input = new InvestmentRevenueCriteria(capital, BUY_DATE, SELL_DATE, InvestmentName, false);
        InvestmentRevenueResult ir = new InvestmentRevenue(mc, input).getResult();

        //analyses input
        System.out.println("\ninputValues(buy date, sell date)");
        System.out.println(input.getStartDate());
        System.out.println(input.getEndDate());
        System.out.println(input.getModifiedBySuggester());
        System.out.println(input.getFavourite());

        // anlysis results
        System.out.println("\nresultValues(buy date, sell date)");
        InvestmentRevenueCriteria finallyEvaluatedInput =(InvestmentRevenueCriteria)ir.getFinallyEvaluatedInput();

        System.out.println(finallyEvaluatedInput.getStartDate());
        System.out.println(finallyEvaluatedInput.getEndDate());
        System.out.println(finallyEvaluatedInput.getModifiedBySuggester());
        System.out.println(input.getFavourite());
        System.out.println(ir.getCapitalRevenueValue());
        System.out.println(ir.getCapitalRevenueDeltaPrecentValue());*/
    }

    @Test(expected = NoDataForCriteria.class)
    public void testGetResultWhenMissingInvestments() throws Exception {

        //  ESSENTIAL creating empty MainContainer (no loaded data)
        MainContainer mc = mainContainerLoader.getMainContainer();

        InvestmentRevenueCriteria input = new InvestmentRevenueCriteria(capital, BUY_DATE, SELL_DATE, InvestmentName, false);

        // should throw exception
        InvestmentRevenueResult ir = new InvestmentRevenue(mc, input).getResult();
    }

    @Test(expected = NoDataForCriteria.class)
    public void testGetResultWhenMissingQuotations() throws Exception {
        LocalDate SELL_DATE = LocalDate.parse("20170330", formatter);

        MainContainer mc = this.getMainContainerWithLoadeData();

        // ESSENTIAL: removing quotations
        mc.getInvestments().forEach(x -> x.setQuotations(null));

        // example analyses usage
        InvestmentRevenueCriteria input = new InvestmentRevenueCriteria(capital, BUY_DATE, SELL_DATE, InvestmentName, false);
        InvestmentRevenueResult ir = new InvestmentRevenue(mc, input).getResult();
    }

    @Test(expected = NoDataForCriteria.class)
    public void testGetResultSuggesterFailureByDateOutOfBounds() throws Exception {

        // ESSENTIAL: Suggester (for dates) scans for available dates on left side of timeline
        // target date out of left bound will always couse Suggester fialure
        LocalDate SELL_DATE = LocalDate.parse("19850315", formatter);

        MainContainer mc = this.getMainContainerWithLoadeData();

        // extracting investments
        List<Investment> investments = mc.getInvestments();

        // example analyses usage
        InvestmentRevenueCriteria input = new InvestmentRevenueCriteria(capital, BUY_DATE, SELL_DATE, InvestmentName, false);
        InvestmentRevenueResult ir = new InvestmentRevenue(mc, input).getResult();
    }

}