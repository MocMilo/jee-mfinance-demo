package com.infoshareacademy.mfinance.cli.services.analyzer;

import com.infoshareacademy.mfinance.cli.model.results.AnalysisResult;
import com.infoshareacademy.mfinance.cli.services.analyzer.strategies.AnalysisStrategy;
import com.infoshareacademy.mfinance.cli.services.analyzer.strategies.INDAnalysisStrategy;
import com.infoshareacademy.mfinance.cli.services.analyzer.strategies.IVRAnalysisStrategy;
import com.infoshareacademy.mfinance.cli.model.ParserResult;
import com.infoshareacademy.mfinance.cli.services.parser.analysisNames;
import com.infoshareacademy.mfinance.core.builders.DataContainerBuilder;
import com.infoshareacademy.mfinance.core.models.bossa.DataContainer;

import java.util.HashMap;
import java.util.Map;


public class CoreAnalyzer {

    private static Map<String, AnalysisStrategy> analysisStrategies = new HashMap<>();

    static {
        analysisStrategies.put(analysisNames.IND.toString(), new INDAnalysisStrategy());
        analysisStrategies.put(analysisNames.IVR.toString(), new IVRAnalysisStrategy());
    }

    public AnalysisResult getResult(ParserResult result) {

        DataContainer container = new DataContainerBuilder().getDataContainer();
        String analysisCommandName = result.getArguments().getStrategy();

        return analysisStrategies.get(analysisCommandName)
                .getResult(result, container);
    }
}
