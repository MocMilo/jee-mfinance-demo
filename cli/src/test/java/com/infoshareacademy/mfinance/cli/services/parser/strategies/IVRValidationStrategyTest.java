package com.infoshareacademy.mfinance.cli.services.parser.strategies;

import com.infoshareacademy.mfinance.cli.model.ParserResult;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class IVRValidationStrategyTest {

    private String[] args = {"IVR", "USD", "1000.25", "2015-09-07", "2015-09-08"};
    private String[] argsWrongNumber = {"IVR", "USD", "1000.25", "2015-09-07"};
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
        result = strategy.validate(argsWrongNumber);
        assertThat(result.isValid(), is(equalTo(false)));
        assertThat(result.getArguments(), is(equalTo(null)));
        assertThat(result.getErrorMessage(), is(equalTo("Wrong number of arguments.")));
    }

    @Test
    public void shouldBeNotValidWrongNumberOfArgsTooMany() {
        String[] tooManyArgs = new String[6];
        result = strategy.validate(tooManyArgs);
        assertThat(result.isValid(), is(equalTo(false)));
        assertThat(result.getArguments(), is(equalTo(null)));
        assertThat(result.getErrorMessage(), is(equalTo("Wrong number of arguments.")));
    }

    @Test
    public void shouldNotBeValidWrongMoneyFormatValue() {
        args[2] = "1000.245";
        result = strategy.validate(args);
        assertThat(result.isValid(), is(equalTo(false)));
        assertThat(result.getArguments(), is(equalTo(null)));
        assertThat(result.getErrorMessage(), is(equalTo("Wrong Investment capital argument: should be decimal of format: 1.00, > 0.00")));
    }

    @Test
    public void shouldNotBeValidWrongMoneyValue() {
        args[2] = "err1000";
        result = strategy.validate(args);
        assertThat(result.isValid(), is(equalTo(false)));
        assertThat(result.getArguments(), is(equalTo(null)));
        assertThat(result.getErrorMessage(), is(equalTo("Wrong Investment capital argument: should be decimal of format: 1.00, > 0.00")));
    }

    @Test
    public void shouldNotBeValidWrongBuyDateValue() {
        args[3] = "err2015-09-07";
        result = strategy.validate(args);
        assertThat(result.isValid(), is(equalTo(false)));
        assertThat(result.getArguments(), is(equalTo(null)));
        assertThat(result.getErrorMessage(), is(equalTo("Wrong buy date format, should be of pattern: YYYY-MM-DD.")));
    }

    @Test
    public void shouldNotBeValidWrongOfSellDateValue() {
        args[4] = "err2015-09-08";
        result = strategy.validate(args);
        assertThat(result.isValid(), is(equalTo(false)));
        assertThat(result.getArguments(), is(equalTo(null)));
        assertThat(result.getErrorMessage(), is(equalTo("Wrong sell date format, should be of pattern: YYYY-MM-DD.")));
    }

    @Test
    public void shouldNotBeValidWrongOrderSellDateBeforeBuyDate() {
        args[3] = "2015-09-08";
        args[4] = "2015-09-07";
        result = strategy.validate(args);
        assertThat(result.isValid(), is(equalTo(false)));
        assertThat(result.getArguments(), is(equalTo(null)));
        assertThat(result.getErrorMessage(), is(equalTo("Wrong dates order: buy date should be before sell date.")));
    }
}