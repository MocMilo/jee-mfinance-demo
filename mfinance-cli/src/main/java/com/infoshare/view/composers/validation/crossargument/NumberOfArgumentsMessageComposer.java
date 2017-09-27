package com.infoshare.view.composers.validation.crossargument;


import com.infoshare.model.analysisResults.NumberOfArgumentsValidationResult;

public class NumberOfArgumentsMessageComposer {

    private static final String ERR_MESSAGE = "\nWrong number of arguments: ";

    public String composeErrorMessage(NumberOfArgumentsValidationResult result) {

        StringBuilder sb = new StringBuilder();

        if (!result.isValid()) {
            sb.append("\n"
                    .concat(ERR_MESSAGE)
                    .concat(result.getEvaluatedValue())
                    .concat("\nThis analysis should have: ")
                    .concat(result.getExpectedValue())
                    .concat(" arugments.\n"));
        }
        return sb.toString();
    }
}
