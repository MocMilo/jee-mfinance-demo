package com.infoshare.controller.validators.argument;

import com.infoshare.model.validationResults.ArgValidatorResult;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.regex.Pattern;

public class DateTimeValidator implements ArgumentValidator {


    private static final String ERR_MESSAGE = "Value should be a date formated as: yyy-MM-dd";
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    private String MESSAGE ="ok";

    @Override
    public ArgValidatorResult doValidate(String evaluetedValue) {

        boolean isValid = isValidDate(evaluetedValue);
        if(!isValid){MESSAGE=ERR_MESSAGE;}
        return new ArgValidatorResult(isValid, evaluetedValue , MESSAGE);
    }

    private boolean isValidDate(String input) {
        try {
            format.setLenient(false);
            format.parse(input);
            return true;
        }
        catch(ParseException e){
            return false;
        }
    }
}
