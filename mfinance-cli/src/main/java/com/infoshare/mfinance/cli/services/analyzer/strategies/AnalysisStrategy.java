package com.infoshare.mfinance.cli.services.analyzer.strategies;

import com.infoshare.core.models.bossa.MainContainer;
import com.infoshare.mfinance.cli.model.results.AnalysisResult;
import com.infoshare.mfinance.cli.services.parser.ParserResult;

public interface AnalysisStrategy {

  AnalysisResult getResult(ParserResult result, MainContainer container);

}
