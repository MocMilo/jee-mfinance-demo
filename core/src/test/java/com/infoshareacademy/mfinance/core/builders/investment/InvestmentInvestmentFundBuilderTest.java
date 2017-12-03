package com.infoshareacademy.mfinance.core.builders.investment;

import com.infoshareacademy.mfinance.core.models.bossa.InvestmentFund;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;

public class InvestmentInvestmentFundBuilderTest {

    private final String TEST_FILE_PATH = Paths.get("src/test/resources/csv/funds/AGI001.txt").toString();
    private final int NUMBER_OF_ROWS = 405;
    private InvestmentFund investmentFund;
    private InvestmentFundListBuilder investmentFundBuilder;

    @Before
    public void init() throws IOException{
        investmentFundBuilder = new InvestmentFundListBuilder();
        investmentFundBuilder.createFundsFromFile(TEST_FILE_PATH);
        investmentFund = investmentFundBuilder.getInvestmentFunds().get(0);
    }

    @Test
    public void CreateFundFromFile() {
        int expected = 1;
        int evaluated = investmentFundBuilder.getNumberOfFunds();
        assertThat(investmentFund, not(equalTo(nullValue())));
        assertThat(evaluated, is(equalTo(expected)));
    }

    @Test
    public void FundContainsLoadedData() {
        String expectedName = "AGI001";
        assertThat(investmentFund.getName(), is(equalTo(expectedName)));
        assertThat(investmentFund.getQuotations(), not(equalTo(nullValue())));
    }

    @Test
    public void FundContainsLoadedQuotationsData() {
        int numberOfQuotations = investmentFundBuilder.getInvestmentFunds().get(0).getQuotations().size();
        assertThat(NUMBER_OF_ROWS, is(numberOfQuotations));
    }
}