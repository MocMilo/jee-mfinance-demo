package com.infoshare.core.analyzer.analyses.revenue;

import com.infoshare.core.configuration.ConfigurationProvider;
import com.infoshare.core.loader.MainContainerLoader;
import com.infoshare.core.models.analyses.criteria.InvestmentRevenueCriteria;
import com.infoshare.core.models.analyses.results.InvestmentRevenueResult;
import com.infoshare.core.models.bossa.MainContainer;
import com.infoshare.core.models.configuration.Configuration;
import com.infoshare.core.models.exceptions.NoDataForCriteria;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class InvestmentRevenueTest  {

    private final   BigDecimal capital =  new BigDecimal(10000.00);
    private final   DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
    private final   LocalDate BUY_DATE = LocalDate.parse("20090910", formatter);
    private final   LocalDate SELL_DATE = LocalDate.parse("20170330", formatter);
    private final   String InvestmentName = "CHF";

    private final Configuration configuration = new ConfigurationProvider().getConfiguration();
    private final MainContainerLoader mainContainerLoader = new MainContainerLoader(configuration);

    private MainContainer getMainContainerWithLoadedData(){
        mainContainerLoader.loadFunds();
        mainContainerLoader.loadCurrencies();
        return mainContainerLoader.getMainContainer();
    }

    @Test
    public void getInvestmentsFfomMainContainer(){

        int investments =  getMainContainerWithLoadedData().getInvestments().size();
        System.out.println("number of investments: "+investments);

    }

    @Test(expected = NoDataForCriteria.class)
    public void testGetResultWhenUserInputOutOfRange() throws Exception {
        LocalDate SELL_DATE = LocalDate.parse("20140104", formatter);

        MainContainer mc = this.getMainContainerWithLoadedData();

        // example analyses usage
        InvestmentRevenueCriteria input = new InvestmentRevenueCriteria(capital, BUY_DATE, SELL_DATE, InvestmentName);
        InvestmentRevenueResult ir = new InvestmentRevenue(mc, input).getResult();

        //analyses input
        System.out.println("\ninputValues(buy date, sell date)");
        System.out.println(input.getBuyDate());
        System.out.println(input.getSellDate());


        // anlysis results
        System.out.println("\nresultValues(buy date, sell date)");

        System.out.println(ir.getCapitalRevenueValue());
        System.out.println(ir.getCapitalRevenueDeltaPrecentValue());
    }

    @Test(expected = NoDataForCriteria.class)
    public void testGetResultWhenMissingInvestments() throws Exception {

        //  ESSENTIAL creating empty MainContainer (no loaded data)
        MainContainer mc = mainContainerLoader.getMainContainer();

        InvestmentRevenueCriteria input = new InvestmentRevenueCriteria(capital, BUY_DATE, SELL_DATE, InvestmentName);

        // should throw exception
        InvestmentRevenueResult ir = new InvestmentRevenue(mc, input).getResult();
    }

    @Test(expected = NoDataForCriteria.class)
    public void testGetResultWhenMissingQuotations() throws Exception {
        LocalDate SELL_DATE = LocalDate.parse("20170330", formatter);

        MainContainer mc = this.getMainContainerWithLoadedData();

        // ESSENTIAL: removing quotations
        mc.getInvestments().forEach(x -> x.setQuotations(null));

        // example analyses usage
        InvestmentRevenueCriteria input = new InvestmentRevenueCriteria(capital, BUY_DATE, SELL_DATE, InvestmentName);
        InvestmentRevenueResult ir = new InvestmentRevenue(mc, input).getResult();
    }



}