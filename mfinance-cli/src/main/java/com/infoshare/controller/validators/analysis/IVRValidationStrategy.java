package com.infoshare.controller.validators.analysis;

import com.infoshare.controller.validators.argument.DateTimeValidator;
import com.infoshare.controller.validators.argument.StringLengthValidator;
import com.infoshare.model.arguments.IVRArgs;
import com.infoshare.model.validationResults.AnalysisValidatorResult;
import com.infoshare.model.validationResults.ArgValidatorResult;
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
    public AnalysisValidatorResult doValidationAlgorithm(String[] args) {

        List<ArgValidatorResult> results = new ArrayList<>();

        ArgValidatorResult analysisNameValidation = stringLengthValidator.doValidate(args[0]);
        ArgValidatorResult capitalValidationResult = bigDecimalValidator.doValidate(args[2]);
        ArgValidatorResult startDateValidation = dateTimeValidator.doValidate(args[3]);
        ArgValidatorResult endDateValidation = dateTimeValidator.doValidate(args[4]);

        results.add(analysisNameValidation);
        results.add(capitalValidationResult);
        results.add(startDateValidation);
        results.add(endDateValidation);

        return new AnalysisValidatorResult(this.isValid(results),
                messageComposer.composeErrorMessage(results), args);
    }

    private boolean isValid(List<ArgValidatorResult> results) {

        boolean isResultsListValid = false;

        for (ArgValidatorResult item : results) {
            if (!item.isValid()) {
                break;
            } else {
                isResultsListValid = true;
            }
        }
        return isResultsListValid;
    }
}
