package com.infoshareacademy.web.services.validators;

import com.infoshareacademy.web.model.validation.ValidationResult;

import javax.servlet.http.HttpServletRequest;

public interface ValidationStrategy {

   ValidationResult getValidationResult(HttpServletRequest req);
}

