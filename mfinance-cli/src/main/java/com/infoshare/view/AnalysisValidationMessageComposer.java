package com.infoshare.view;

import com.infoshare.model.validationResults.ArgValidatorResult;

import java.util.List;

public class AnalysisValidationMessageComposer {

    public String composeErrorMessage(List<ArgValidatorResult> results) {

        StringBuilder sb = new StringBuilder();

            for (ArgValidatorResult item : results) {
                sb.append("\narg value:"
                        .concat(item.getEvaluatedValue())
                        .concat(" "
                        .concat(item.getErrMessage()
                        .concat("\n"))));
            }
        return sb.toString();
    }
}
