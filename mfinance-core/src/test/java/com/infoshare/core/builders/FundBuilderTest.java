package com.infoshare.core.builders;

import com.infoshare.core.models.bossa.Fund;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;

public class FundBuilderTest {

    private final String TESTFILEPATH = Paths.get("src/test/resources/csv/funds/AGI001.txt").toString();
    private final int NUMBEROFROWS = 405;
    private Fund fund;
    private FundBuilder fundBuilder;

    @Before
    public void init() {
        fundBuilder = new FundBuilder();
        fundBuilder.createFundsFromFile(TESTFILEPATH);
        fund = fundBuilder.getFunds().get(0);
    }

    @Test
    public void CreateFundFromFile() throws Exception {

        int expected = 1;
        int evaluated = fundBuilder.getNumberOfFunds();

        assertThat(fund, not(equalTo(nullValue())));
        assertThat(evaluated, is(equalTo(expected)));
    }

    @Test
    public void FundContainsLoadedData() throws Exception {

        String expectedName = "AGI001";

        assertThat(fund.getName(), is(equalTo(expectedName)));
        assertThat(fund.getQuotations(), not(equalTo(nullValue())));
    }

    @Test
    public void FundContainsLoadedQuotationsData() throws Exception {

        int numberOfQuotations = fundBuilder.getFunds().get(0).getQuotations().size();

        assertThat(NUMBEROFROWS, is(numberOfQuotations));
    }
}