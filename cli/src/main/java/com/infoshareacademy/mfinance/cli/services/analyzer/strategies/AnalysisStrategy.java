package com.infoshareacademy.mfinance.cli.services.analyzer.strategies;

import com.infoshareacademy.mfinance.cli.model.ParserResult;
import com.infoshareacademy.mfinance.core.models.bossa.DataContainer;
import com.infoshareacademy.mfinance.cli.model.results.AnalysisResult;

public interface AnalysisStrategy {
    AnalysisResult getResult(ParserResult result, DataContainer container);
}
