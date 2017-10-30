package com.infoshare.mfinance.core.builders;

import com.infoshare.mfinance.core.models.bossa.InvestmentFund;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;

public class InvestmentFundBuilderTest {

    private final String TEST_FILE_PATH = Paths.get("src/test/resources/csv/funds/AGI001.txt").toString();
    private final int NUMBER_OF_ROWS = 405;
    private InvestmentFund investmentFund;
    private FundBuilder fundBuilder;

    @Before
    public void init() {
        fundBuilder = new FundBuilder();
        fundBuilder.createFundsFromFile(TEST_FILE_PATH);
        investmentFund = fundBuilder.getInvestmentFunds().get(0);
    }

    @Test
    public void CreateFundFromFile() throws Exception {

        int expected = 1;
        int evaluated = fundBuilder.getNumberOfFunds();

        assertThat(investmentFund, not(equalTo(nullValue())));
        assertThat(evaluated, is(equalTo(expected)));
    }

    @Test
    public void FundContainsLoadedData() throws Exception {

        String expectedName = "AGI001";

        assertThat(investmentFund.getName(), is(equalTo(expectedName)));
        assertThat(investmentFund.getQuotations(), not(equalTo(nullValue())));
    }

    @Test
    public void FundContainsLoadedQuotationsData() throws Exception {

        int numberOfQuotations = fundBuilder.getInvestmentFunds().get(0).getQuotations().size();

        assertThat(NUMBER_OF_ROWS, is(numberOfQuotations));
    }

    // @Test(expected = Exception.class)
    public void shouldFailWhenFileNotFound(){

        //fixme (should throw exception)

        String notExistingFilePath = "/NotExistingFilePath";
        new FundBuilder().createFundsFromFile(notExistingFilePath);
    }
}