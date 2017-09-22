package com.infoshare.validators.argument;

import com.infoshare.model.validationResults.ArgValidatorResult;

import java.util.Scanner;

public class BigDecimalValidator implements ArgumentValidator {

    private static final String ERR_MESSAGE = "Value should be BigDecimal.";
    private String MESSAGE ="";

    @Override
    public ArgValidatorResult doValidate(String evaluetedValue) {

        boolean isValid = new Scanner(evaluetedValue).hasNextBigDecimal();
        if(!isValid){MESSAGE=ERR_MESSAGE;}
        return new ArgValidatorResult(isValid, evaluetedValue , MESSAGE);
    }
}
