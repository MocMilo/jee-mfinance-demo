package com.infoshare.mfinance.cli.services.analyzer;

import com.infoshare.mfinance.cli.model.results.AnalysisResult;
import com.infoshare.mfinance.cli.services.analyzer.strategies.AnalysisStrategy;
import com.infoshare.mfinance.cli.services.analyzer.strategies.INDAnalysisStrategy;
import com.infoshare.mfinance.cli.services.analyzer.strategies.IVRAnalysisStrategy;
import com.infoshare.mfinance.cli.services.parser.ParserResult;
import com.infoshare.mfinance.cli.services.parser.analysisNames;
import com.infoshare.mfinance.core.builders.DataContainerBuilder;
import com.infoshare.mfinance.core.models.bossa.DataContainer;

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
