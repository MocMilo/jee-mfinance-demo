package com.infoshareacademy.mfinance.cli.services.parser.strategies;

import com.infoshareacademy.mfinance.cli.model.arguments.IVRArgs;
import com.infoshareacademy.mfinance.cli.model.ParserResult;
import com.infoshareacademy.mfinance.cli.utils.ValidatorUtil;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

public class IVRValidationStrategy implements ValidationStrategy {

    @Override
    public ParserResult validate(String[] args) {
        if (args.length != 5) {
            return new ParserResult(false, "Wrong number of arguments.", null);
        }
        IVRArgs ivrArgs = new IVRArgs(args);
        Validator validator =  ValidatorUtil.getValidator();
        Set<ConstraintViolation<IVRArgs>> constraintViolations =
                validator.validate(ivrArgs);

        if (!constraintViolations.isEmpty()) {
            String message = "";
            for (ConstraintViolation item : constraintViolations){
               message = message.concat(item.getMessage()) ;
            }
        return new ParserResult(false, message, null);
        }
        return new ParserResult(true, "", ivrArgs);
    }
}
