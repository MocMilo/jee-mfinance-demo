package com.infoshare.validators.analysis;


import com.infoshare.model.validationResults.AnalysisValidatorResult;


public interface AnalysisArgsValidator {
   AnalysisValidatorResult doAnalysisValidation(String[] args);
}
