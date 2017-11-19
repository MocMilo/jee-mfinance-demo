package com.infoshare.web.services.validator.strategies;

import com.infoshare.web.model.validation.ValidationResult;

import javax.servlet.http.HttpServletRequest;


public interface ValidationStrategy {

   ValidationResult getValidationResult(HttpServletRequest req);
}

