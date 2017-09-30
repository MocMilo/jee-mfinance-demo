package com.infoshare.controller.analyzers.strategies;

import com.infoshare.core.models.exceptions.NoDataForCriteria;
import com.infoshare.model.arguments.INDArgs;
import com.infoshare.model.arguments.IVRArgs;

import java.util.HashMap;
import java.util.Map;

public class AnalysisContext {

    private static Map<String, AnalysisStrategy> analysisStrategies = new HashMap<>();

    static {

        analysisStrategies.put(IVRArgs.ANALYSIS_COMMAND_STRING, new IVRAnalyzerStrategy());
        analysisStrategies.put(INDArgs.ANALYSIS_COMMAND_STRING, new INDAnalyzerStrategy());
    }

    public static void doAnalyze(String[] args) throws NoDataForCriteria {

        String analysisCommandName = args[0];
        if (analysisStrategies.containsKey(analysisCommandName)) {
             analysisStrategies.get(analysisCommandName).doAnalyzeAlgorithm(args);
        } else {
            throw new IllegalArgumentException("bad argument: analysis_name not found!");
        }
    }
}
