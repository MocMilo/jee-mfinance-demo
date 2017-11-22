package com.infoshare.web.services.validators;

import com.infoshare.web.model.validation.ValidationResult;

import javax.servlet.http.HttpServletRequest;


public interface ValidationStrategy {

   ValidationResult getValidationResult(HttpServletRequest req);
}

