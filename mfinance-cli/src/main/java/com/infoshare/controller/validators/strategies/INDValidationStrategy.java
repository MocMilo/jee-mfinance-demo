package com.infoshare.controller.validators.strategies;

import com.infoshare.controller.validators.argument.StringLengthValidator;
import com.infoshare.controller.validators.argument.crossargumentvalidators.NumberOfArgumentsValidator;
import com.infoshare.model.arguments.INDArgs;
import com.infoshare.model.arguments.IVRArgs;
import com.infoshare.model.validationResults.AnalysisValidationResult;
import com.infoshare.model.validationResults.ArgValidationResult;
import com.infoshare.view.composers.validation.AnalysisValidationMessageComposer;

import java.util.ArrayList;
import java.util.List;

public class INDValidationStrategy implements AnalysisValidationStrategy {


    private INDArgs indArgs;
    private NumberOfArgumentsValidator argsNumberValidator;
    private StringLengthValidator stringLengthValidator = new StringLengthValidator();
    private AnalysisValidationMessageComposer messageComposer = new AnalysisValidationMessageComposer();
    private List<ArgValidationResult> finalValidationResults = new ArrayList<>();

    @Override
    public AnalysisValidationResult doValidationAlgorithm(String[] args) {

        finalValidationResults.add(doArgsNumberValidation(args));

        if (isValid(finalValidationResults)) {
            indArgs = new INDArgs(args);
            finalValidationResults.add(stringLengthValidator.doValidate(IVRArgs.ANALYSIS_COMMAND_STRING));
        }
        return new AnalysisValidationResult(
                this.isValid(finalValidationResults),
                messageComposer.composeErrorMessage(finalValidationResults),
                args);
    }

    private ArgValidationResult doArgsNumberValidation(String[] args) {

        argsNumberValidator = new NumberOfArgumentsValidator(INDArgs.COMMAND_ARGS_NUMBER, args);
        ArgValidationResult result = argsNumberValidator.doValidate();
        return new ArgValidationResult(result.isValid(), result.getEvaluatedValue(),result.getErrMessage());

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
