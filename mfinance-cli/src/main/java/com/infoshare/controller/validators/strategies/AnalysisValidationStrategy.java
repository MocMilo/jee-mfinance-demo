package com.infoshare.controller.validators.strategies;


import com.infoshare.model.validationResults.AnalysisValidationResult;


public interface AnalysisValidationStrategy {
   AnalysisValidationResult doValidationAlgorithm(String[] args);
}
