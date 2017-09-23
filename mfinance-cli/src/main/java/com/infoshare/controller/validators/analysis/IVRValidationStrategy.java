package com.infoshare.controller.validators.analysis;

import com.infoshare.controller.validators.argument.DateTimeValidator;
import com.infoshare.controller.validators.argument.StringLengthValidator;
import com.infoshare.model.arguments.IVRArgs;
import com.infoshare.model.validationResults.AnalysisValidationResult;
import com.infoshare.model.validationResults.ArgValidationResult;
import com.infoshare.controller.validators.argument.BigDecimalValidator;
import com.infoshare.view.AnalysisValidationMessageComposer;

import java.util.ArrayList;
import java.util.List;

public class IVRValidationStrategy implements AnalysisValidationStrategy {

    private BigDecimalValidator bigDecimalValidator = new BigDecimalValidator();
    private StringLengthValidator stringLengthValidator = new StringLengthValidator();
    private DateTimeValidator dateTimeValidator = new DateTimeValidator();
    private IVRArgs ivrArgs;

    private AnalysisValidationMessageComposer messageComposer = new AnalysisValidationMessageComposer();



    @Override
    public AnalysisValidationResult doValidationAlgorithm(String[] args) {

        ivrArgs = new IVRArgs(args);
        List<ArgValidationResult> results = new ArrayList<>();

        results.add(stringLengthValidator.doValidate(IVRArgs.ANALYSIS_COMMAND_STRING));
        results.add(bigDecimalValidator.doValidate(ivrArgs.getCapital()));
        results.add(dateTimeValidator.doValidate(ivrArgs.getStartDate()));
        results.add(dateTimeValidator.doValidate(ivrArgs.getEndDate()));

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
