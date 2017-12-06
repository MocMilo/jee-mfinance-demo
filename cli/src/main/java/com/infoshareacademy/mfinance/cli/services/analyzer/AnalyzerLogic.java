package com.infoshareacademy.mfinance.cli.services.analyzer;

import com.infoshareacademy.mfinance.cli.model.results.AnalysisResult;
import com.infoshareacademy.mfinance.cli.services.analyzer.strategies.AnalysisStrategy;
import com.infoshareacademy.mfinance.cli.services.analyzer.strategies.INDAnalysisStrategy;
import com.infoshareacademy.mfinance.cli.services.analyzer.strategies.IVRAnalysisStrategy;
import com.infoshareacademy.mfinance.cli.model.ParserResult;
import com.infoshareacademy.mfinance.cli.services.parser.analysisNames;
import com.infoshareacademy.mfinance.core.models.bossa.DataContainer;

import java.util.HashMap;
import java.util.Map;

public class AnalyzerLogic {
    private DataContainer container;
    private static Map<String, AnalysisStrategy> analysisStrategies = new HashMap<>();

    static {
        analysisStrategies.put(analysisNames.IND.toString(), new INDAnalysisStrategy());
        analysisStrategies.put(analysisNames.IVR.toString(), new IVRAnalysisStrategy());
    }

    public AnalyzerLogic(DataContainer container) {
        this.container = container;
    }

    public AnalysisResult getResult(ParserResult result) {
        String analysisCommandName = result.getArguments().getStrategy();
        return analysisStrategies.get(analysisCommandName)
                .getResult(result, container);
    }
}
