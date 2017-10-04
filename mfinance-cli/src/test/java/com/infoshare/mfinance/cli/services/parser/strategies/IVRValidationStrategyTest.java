package com.infoshare.mfinance.cli.services.parser.strategies;

import com.infoshare.mfinance.cli.services.parser.ParserResult;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class IVRValidationStrategyTest {

    //TODO rest of testCases

    private String[] args = {"IVR", "USD", "1000", "2015-09-07", "2015-09-08"};
    private IVRValidationStrategy strategy;
    private ParserResult result;


    @Before
    public void init() {

        strategy = new IVRValidationStrategy();
    }

    @Test
    public void shouldBeValid() {

        result = strategy.validate(args);

        assertThat(result.isValid(), is(equalTo(true)));
        assertThat(result.getArguments(), not(equalTo(nullValue())));
        assertThat(result.getErrorMessage(), is(equalTo("")));
    }


    @Test
    public void shouldNotBeValidBecauseOfMoneyValue() {
        args[2] = "err1000";
        result = strategy.validate(args);

        assertThat(result.isValid(), is(equalTo(false)));
        assertThat(result.getArguments(), is(equalTo(null)));
        assertThat(result.getErrorMessage(), is(equalTo("\nwrong capital argument: should be decimal")));
    }

    @Test
    public void shouldNotBeValidBecauseOfBuyDateValue() {
        args[3] = "err2015-09-07";
        result = strategy.validate(args);

        assertThat(result.isValid(), is(equalTo(false)));
        assertThat(result.getArguments(), is(equalTo(null)));
        assertThat(result.getErrorMessage(), is(equalTo("\nwrong buy date: should be in format yyyy-MM-dd")));
    }

    @Test
    public void shouldNotBeValidBecauseOfSellDateValue() {
        args[4] = "err2015-09-08";
        result = strategy.validate(args);

        assertThat(result.isValid(), is(equalTo(false)));
        assertThat(result.getArguments(), is(equalTo(null)));
        assertThat(result.getErrorMessage(), is(equalTo("\nwrong sell date: should be in format yyyy-MM-dd")));
    }
}