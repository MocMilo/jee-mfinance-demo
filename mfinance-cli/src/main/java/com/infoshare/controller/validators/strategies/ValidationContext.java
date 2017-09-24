package com.infoshare.controller.validators.strategies;


import com.infoshare.model.arguments.INDArgs;
import com.infoshare.model.arguments.IVRArgs;
import com.infoshare.model.validationResults.AnalysisValidationResult;

import java.util.HashMap;
import java.util.Map;

public class ValidationContext {

    private static Map<String, AnalysisValidationStrategy> validationStrategies = new HashMap<>();

    static {

        //TODO put all analysis validation mappings here
        validationStrategies.put(IVRArgs.ANALYSIS_COMMAND_STRING, new IVRValidationStrategy());
        validationStrategies.put(INDArgs.ANALYSIS_COMMAND_STRING, new INDValidationStrategy());
    }

    public static AnalysisValidationResult doValidate(String[] args) {

        String analysisCommandName = args[0];

        if (validationStrategies.containsKey(analysisCommandName)) {
            return validationStrategies.get(analysisCommandName).doValidationAlgorithm(args);
        } else {
            throw new IllegalArgumentException("bad argument: analysis_name not found!");
        }
    }
}
