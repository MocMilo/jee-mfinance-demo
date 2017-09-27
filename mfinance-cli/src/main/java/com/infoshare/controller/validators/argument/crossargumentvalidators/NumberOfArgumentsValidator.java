package com.infoshare.controller.validators.argument.crossargumentvalidators;

import com.infoshare.model.analysisResults.NumberOfArgumentsValidationResult;
import com.infoshare.model.validationResults.ArgValidationResult;
import com.infoshare.view.composers.validation.crossargument.NumberOfArgumentsMessageComposer;


public class NumberOfArgumentsValidator {

    private NumberOfArgumentsMessageComposer composer = new NumberOfArgumentsMessageComposer();
    private int expectedArgsNumber;
    private String[] args;

    public NumberOfArgumentsValidator(int expectedArgsNumber, String[] args) {
        this.expectedArgsNumber = expectedArgsNumber;
        this.args = args;
    }

    public ArgValidationResult doValidate() {

        String message = composer.composeErrorMessage(this.getResult(expectedArgsNumber));
        NumberOfArgumentsValidationResult result = this.getResult(expectedArgsNumber);

        return new ArgValidationResult(result.isValid(),
                result.getEvaluatedValue(), message);
    }

    private NumberOfArgumentsValidationResult getResult(int expectedNumberOfArgs) {

        String expectedValue = Integer.toString(expectedNumberOfArgs);
        String evaluatedValue = Integer.toString(args.length);

        if (expectedNumberOfArgs == args.length) {
            return new NumberOfArgumentsValidationResult(true, expectedValue, evaluatedValue);
        }
        return new NumberOfArgumentsValidationResult(false, expectedValue, evaluatedValue);
    }
}
