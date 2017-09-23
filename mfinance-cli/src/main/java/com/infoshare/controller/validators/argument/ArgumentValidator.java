package com.infoshare.controller.validators.argument;

import com.infoshare.model.validationResults.ArgValidationResult;

public interface ArgumentValidator {

    ArgValidationResult doValidate(String valueToValidate);
}
