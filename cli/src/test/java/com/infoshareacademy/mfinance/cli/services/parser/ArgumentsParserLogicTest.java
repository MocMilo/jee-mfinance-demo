package com.infoshareacademy.mfinance.cli.services.parser;

import com.infoshareacademy.mfinance.cli.model.ParserResult;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class ArgumentsParserLogicTest {
    private ArgumentsParserLogic parser = new ArgumentsParserLogic();

    @Test
    public void shouldReturnNotValidResultWhenNoArguments() {
        final String[] args = {};
        ParserResult result = parser.parse(args);
        assertThat(result.isValid(), is(equalTo(false)));
    }

    @Test
    public void shouldReturnNodValidResultWhenNoAnalysisIVRArguments() {
        final String[] args = {"IVR"};
        ParserResult result = parser.parse(args);
        assertThat(result.isValid(), is(equalTo(false)));
    }

    @Test
    public void shouldReturnNotValidResultWhenNoAnalysisINDArguments() {
        final String[] args = {"IND"};
        ParserResult result = parser.parse(args);
        assertThat(result.isValid(), is(equalTo(false)));
    }
}