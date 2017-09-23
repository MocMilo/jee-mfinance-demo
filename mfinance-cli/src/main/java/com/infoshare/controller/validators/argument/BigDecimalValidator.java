package com.infoshare.controller.validators.argument;

import com.infoshare.model.validationResults.ArgValidationResult;

import java.util.Scanner;

public class BigDecimalValidator implements ArgumentValidator {

    private static final String ERR_MESSAGE = "Value should be BigDecimal.";
    private String MESSAGE ="ok";

    @Override
    public ArgValidationResult doValidate(String evaluetedValue) {

        boolean isValid = new Scanner(evaluetedValue).hasNextBigDecimal();
        if(!isValid){MESSAGE=ERR_MESSAGE;}
        return new ArgValidationResult(isValid, evaluetedValue , MESSAGE);
    }
}
