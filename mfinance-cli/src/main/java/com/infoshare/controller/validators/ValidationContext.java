package com.infoshare.controller.validators;

import com.infoshare.core.models.exceptions.NoDataForCriteria;
import com.infoshare.model.arguments.IVRArgs;
import com.infoshare.model.validationResults.AnalysisValidationResult;
import com.infoshare.controller.validators.analysis.AnalysisValidationStrategy;
import com.infoshare.controller.validators.analysis.IVRValidationStrategy;

import java.util.HashMap;
import java.util.Map;

public class ValidationContext {

    private static Map<String, AnalysisValidationStrategy> validationStratiegies = new HashMap<>();

    static {

        //TODO put all analysis mappings here

        validationStratiegies.put(IVRArgs.ANALYSIS_COMMAND_STRING, new IVRValidationStrategy());
    }

    public static AnalysisValidationResult doValidate(String[] args) {

        String analysisCommandName = args[0];

        if (validationStratiegies.containsKey(analysisCommandName)) {
            return validationStratiegies.get(analysisCommandName).doValidationAlgorithm(args);
        } else {
            throw new IllegalArgumentException("bad argument: analysis_name not found!");
        }
    }
}
