package com.infoshareacademy.mfinance.cli.services.analyzer;

import com.infoshareacademy.mfinance.cli.model.ParserResult;
import com.infoshareacademy.mfinance.cli.model.arguments.INDArgs;
import com.infoshareacademy.mfinance.cli.model.results.AnalysisResult;
import com.infoshareacademy.mfinance.core.models.bossa.DataContainer;
import com.infoshareacademy.mfinance.core.models.bossa.Investment;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class AnalyzerLogicTest {
    private AnalyzerLogic analyzerLogic;

    @Before
    public void init() {
        List<Investment> investmentList = new ArrayList<>();
        DataContainer container;
        container = new DataContainer(1, 1, investmentList);
        analyzerLogic = new AnalyzerLogic(container);
    }

    @Test
    public void shouldReturnsAnalysisResult() throws IOException {
        String[] args = {"IND", "USD"};
        INDArgs indArgs = new INDArgs(args);
        ParserResult parserResult = new ParserResult(true, "", indArgs);
        AnalysisResult result = analyzerLogic.getResult(parserResult);
        assertThat(result, not(equalTo(nullValue())));
    }
}