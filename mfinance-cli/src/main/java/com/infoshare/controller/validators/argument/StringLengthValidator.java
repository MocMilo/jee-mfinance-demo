package com.infoshare.controller.validators.argument;

import com.infoshare.model.validationResults.ArgValidatorResult;

import java.util.Scanner;
import java.util.regex.Pattern;

public class StringLengthValidator implements ArgumentValidator {

    private static final String ERR_MESSAGE = "First, analysis_name argument should be 3 chars long.";
    private String MESSAGE ="ok";

    @Override
    public ArgValidatorResult doValidate(String evaluetedValue) {

        boolean isValid = new Scanner(evaluetedValue).hasNext(Pattern.compile("^\\w{3}$"));
        if(!isValid){MESSAGE=ERR_MESSAGE;}
        return new ArgValidatorResult(isValid, evaluetedValue , MESSAGE);
    }
}
