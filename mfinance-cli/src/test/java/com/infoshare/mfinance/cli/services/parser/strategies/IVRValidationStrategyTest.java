package com.infoshare.mfinance.cli.services.parser.strategies;

import com.infoshare.mfinance.cli.services.parser.ParserResult;
import org.apache.logging.log4j.core.util.ArrayUtils;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class IVRValidationStrategyTest {

    private String[] args = {"IVR", "USD", "1000", "2015-09-07", "2015-09-08"};
    private IVRValidationStrategy strategy;
    private ParserResult result;

    @Before
    public void init() {

        strategy = new IVRValidationStrategy();
    }

    @Test
    public void shouldBeValidAllArgsCorrect() {

        result = strategy.validate(args);

        assertThat(result.isValid(), is(equalTo(true)));
        assertThat(result.getArguments(), not(equalTo(nullValue())));
        assertThat(result.getErrorMessage(), is(equalTo("")));
    }

    @Test
    public void shouldBeNotValidWrongNumberOfArgsLessThanNedded() {

        result = strategy.validate(ArrayUtils.remove(args, 1));

        assertThat(result.isValid(), is(equalTo(false)));
        assertThat(result.getArguments(), is(equalTo(null)));
        assertThat(result.getErrorMessage(), is(equalTo("\nwrong number of arguments")));
    }

    @Test
    public void shouldBeNotValidWrongNumberOfArgsTooMany() {

        String[] tooManyArgs = new String[6];

        result = strategy.validate(tooManyArgs);

        assertThat(result.isValid(), is(equalTo(false)));
        assertThat(result.getArguments(), is(equalTo(null)));
        assertThat(result.getErrorMessage(), is(equalTo("\nwrong number of arguments")));
    }


    @Test
    public void shouldNotBeValidWrongMoneyValue() {
        args[2] = "err1000";
        result = strategy.validate(args);

        assertThat(result.isValid(), is(equalTo(false)));
        assertThat(result.getArguments(), is(equalTo(null)));
        assertThat(result.getErrorMessage(), is(equalTo("\nwrong capital argument: should be decimal")));
    }

    @Test
    public void shouldNotBeValidWrongBuyDateValue() {
        args[3] = "err2015-09-07";
        result = strategy.validate(args);

        assertThat(result.isValid(), is(equalTo(false)));
        assertThat(result.getArguments(), is(equalTo(null)));
        assertThat(result.getErrorMessage(), is(equalTo("\nwrong buy date: should be in format yyyy-MM-dd")));
    }

    @Test
    public void shouldNotBeValidWrongOfSellDateValue() {
        args[4] = "err2015-09-08";
        result = strategy.validate(args);

        assertThat(result.isValid(), is(equalTo(false)));
        assertThat(result.getArguments(), is(equalTo(null)));
        assertThat(result.getErrorMessage(), is(equalTo("\nwrong sell date: should be in format yyyy-MM-dd")));
    }

    @Test
    public void shouldNotBeValidWrongOrderSellDateBeforeBuyDate() {

        args[3] = "2015-09-08";
        args[4] = "2015-09-07";

        result = strategy.validate(args);

        assertThat(result.isValid(), is(equalTo(false)));
        assertThat(result.getArguments(), is(equalTo(null)));
        assertThat(result.getErrorMessage(), is(equalTo("\nwrong dates order: buy date should be before sell date")));
    }

}