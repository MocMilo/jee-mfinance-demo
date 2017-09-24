package com.infoshare.controller.validators.strategies;

import com.infoshare.controller.validators.argument.StringLengthValidator;
import com.infoshare.model.arguments.INDArgs;
import com.infoshare.model.arguments.IVRArgs;
import com.infoshare.model.validationResults.AnalysisValidationResult;
import com.infoshare.model.validationResults.ArgValidationResult;
import com.infoshare.view.composers.AnalysisValidationMessageComposer;

import java.util.ArrayList;
import java.util.List;

public class INDValidationStrategy implements AnalysisValidationStrategy {


    private INDArgs indArgs;
    private StringLengthValidator stringLengthValidator = new StringLengthValidator();
    private AnalysisValidationMessageComposer messageComposer = new AnalysisValidationMessageComposer();

    @Override
    public AnalysisValidationResult doValidationAlgorithm(String[] args) {

        indArgs = new INDArgs(args);
        List<ArgValidationResult> results = new ArrayList<>();

        results.add(stringLengthValidator.doValidate(IVRArgs.ANALYSIS_COMMAND_STRING));

        return new AnalysisValidationResult(
                this.isValid(results),
                messageComposer.composeErrorMessage(results),
                args);
    }

    private boolean isValid(List<ArgValidationResult> results) {

        boolean isResultsListValid=false;

        for (ArgValidationResult item : results) {
            if (!item.isValid()) {
                isResultsListValid = false;
                break;
            } else {
                isResultsListValid = true;
            }
        }
        return isResultsListValid;
    }
}
