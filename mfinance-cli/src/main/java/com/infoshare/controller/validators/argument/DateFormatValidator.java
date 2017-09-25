package com.infoshare.controller.validators.argument;

import com.infoshare.model.validationResults.ArgValidationResult;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateFormatValidator implements ArgumentValidator {


    private static final String ERR_MESSAGE = "Value should be Date, formated: yyyy-MM-dd";
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    private String MESSAGE ="ok";

    @Override
    public ArgValidationResult doValidate(String evaluetedValue) {

        boolean isValid = isValidDate(evaluetedValue);
        if(!isValid){MESSAGE=ERR_MESSAGE;}
        return new ArgValidationResult(isValid, evaluetedValue , MESSAGE);
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
