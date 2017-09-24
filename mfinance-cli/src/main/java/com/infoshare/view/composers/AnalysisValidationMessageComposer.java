package com.infoshare.view.composers;

import com.infoshare.model.validationResults.ArgValidationResult;

import java.util.List;

public class AnalysisValidationMessageComposer {

    public String composeErrorMessage(List<ArgValidationResult> results) {

        StringBuilder sb = new StringBuilder();

            for (ArgValidationResult item : results) {
                sb.append("\narg:"
                        .concat(item.getEvaluatedValue())
                        .concat(" "
                        .concat(item.getErrMessage()
                        .concat("\n"))));
            }
        return sb.toString();
    }
}
