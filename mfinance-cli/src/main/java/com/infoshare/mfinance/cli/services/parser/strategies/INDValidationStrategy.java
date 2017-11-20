package com.infoshare.mfinance.cli.services.parser.strategies;

import com.infoshare.mfinance.cli.model.arguments.INDArgs;
import com.infoshare.mfinance.cli.model.arguments.IVRArgs;
import com.infoshare.mfinance.cli.services.parser.ParserResult;
import com.infoshare.mfinance.cli.services.parser.analysisNames;
import com.infoshare.mfinance.cli.utils.ValidatorUtil;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

public class INDValidationStrategy implements ValidationStrategy {

    @Override
    public ParserResult validate(String[] args) {

        if (args.length != 2) {
            return new ParserResult(false, "Wrong number of arguments.", null);
        }

        INDArgs indArgs = new INDArgs(args);

        Validator validator = ValidatorUtil.getValidator();

        Set<ConstraintViolation<INDArgs>> constraintViolations =
                validator.validate(indArgs);

        if (constraintViolations.size() > 0) {

            String message = "";
            for (ConstraintViolation item : constraintViolations) {
                message = message.concat(item.getMessage());
            }
            return new ParserResult(false, message, null);
        }

        return new ParserResult(true, "", indArgs);
    }
}
