package com.infoshare.validators.argument;

import com.infoshare.model.validationResults.ArgValidatorResult;

public interface ArgumentValidator {

    ArgValidatorResult doValidate(String valueToValidate);
}
