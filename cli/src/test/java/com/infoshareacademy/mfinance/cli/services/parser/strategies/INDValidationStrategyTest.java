package com.infoshareacademy.mfinance.cli.services.parser.strategies;

import com.infoshareacademy.mfinance.cli.model.ParserResult;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;

public class INDValidationStrategyTest {

    private String[] args = {"IVR", "USD"};
    private String[] argsWrongNumber = {"IVR"};
    private ParserResult result;
    private INDValidationStrategy strategy;

    @Before
    public void init() {
        strategy = new INDValidationStrategy();
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
        String[] tooManyArgs = new String[3];
        result = strategy.validate(tooManyArgs);
        assertThat(result.isValid(), is(equalTo(false)));
        assertThat(result.getArguments(), is(equalTo(null)));
        assertThat(result.getErrorMessage(), is(equalTo("Wrong number of arguments.")));
    }
}