package com.infoshare.controller.validators.analysis;


import com.infoshare.model.validationResults.AnalysisValidatorResult;


public interface AnalysisValidationStrategy {
   AnalysisValidatorResult doValidationAlgorithm(String[] args);
}
