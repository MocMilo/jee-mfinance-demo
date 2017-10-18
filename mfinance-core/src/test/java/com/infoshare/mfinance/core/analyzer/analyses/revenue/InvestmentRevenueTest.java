package com.infoshare.mfinance.core.analyzer.analyses.revenue;

import com.infoshare.mfinance.core.builders.DataContainerBuilder;
import com.infoshare.mfinance.core.models.analyses.criteria.InvestmentRevenueCriteria;
import com.infoshare.mfinance.core.models.analyses.results.InvestmentRevenueResult;
import com.infoshare.mfinance.core.models.bossa.DataContainer;
import com.infoshare.mfinance.core.models.exceptions.NoDataForCriteria;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;


public class InvestmentRevenueTest {

    private static final DataContainerBuilder MAIN_CONTAINER_BUILDER = new DataContainerBuilder();
    private static DataContainer container;
    private final BigDecimal capital = new BigDecimal(10000.00);
    private final DateTimeFormatter formatter = DateTimeFormatter.BASIC_ISO_DATE;
    private final LocalDate BUY_DATE = LocalDate.parse("20090910", formatter);
    private final String InvestmentName = "CHF";

    @BeforeClass
    public static void init() {
        container = MAIN_CONTAINER_BUILDER.getDataContainer();
    }

    @Test
    public void getInvestmentsFromMainContainer() {


        int investments = container.getInvestments().size();

        assertThat(investments, not(equalTo(nullValue())));

        System.out.println("number of investments: " + investments);
    }

    @Test(expected = NoDataForCriteria.class)
    public void testGetResultWhenUserInputOutOfRange() throws Exception {
        LocalDate SELL_DATE = LocalDate.parse("20140104", formatter);

        // example analyses usage
        InvestmentRevenueCriteria input = new InvestmentRevenueCriteria(capital, BUY_DATE, SELL_DATE, InvestmentName);
        InvestmentRevenueResult ir = new InvestmentRevenue(container, input).getResult();

        // analyses input
        assertThat(input.getBuyDate(), not(equalTo(nullValue())));
        assertThat(input.getSellDate(), not(equalTo(nullValue())));

        // analysis results
        assertThat(ir.getCapitalRevenueValue(), is(equalTo(nullValue())));
        assertThat(ir.getCapitalRevenueDeltaPercentValue(), is(equalTo(nullValue())));
    }

    @Test(expected = NoDataForCriteria.class)
    public void testGetResultWhenMissingQuotations() throws Exception {
        LocalDate SELL_DATE = LocalDate.parse("20170330", formatter);

        // ESSENTIAL: removing quotations
        container.getInvestments().forEach(x -> x.setQuotations(null));

        // example analyses usage
        InvestmentRevenueCriteria input = new InvestmentRevenueCriteria(capital, BUY_DATE, SELL_DATE, InvestmentName);
        InvestmentRevenueResult ir = new InvestmentRevenue(container, input).getResult();
    }
}