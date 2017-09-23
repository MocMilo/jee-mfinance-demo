package com.infoshare.controller.validators.analysis;


import com.infoshare.model.validationResults.AnalysisValidationResult;


public interface AnalysisValidationStrategy {
   AnalysisValidationResult doValidationAlgorithm(String[] args);
}
