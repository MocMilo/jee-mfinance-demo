package com.infoshare.controller.validators;

import com.infoshare.model.arguments.IVRArgs;
import com.infoshare.model.validationResults.AnalysisValidatorResult;
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

    public static AnalysisValidatorResult doValidate(String name, String[] args) {

        return validationStratiegies.get(name).doValidationAlgorithm(args);
    }
}
