package com.infoshare.controller.validators.strategies;

import com.infoshare.controller.validators.argument.BigDecimalValidator;
import com.infoshare.controller.validators.argument.DateFormatValidator;
import com.infoshare.controller.validators.argument.StringLengthValidator;
import com.infoshare.controller.validators.argument.crossargumentvalidators.DatesOrderValidator;
import com.infoshare.model.arguments.IVRArgs;
import com.infoshare.model.validationResults.AnalysisValidationResult;
import com.infoshare.model.validationResults.ArgValidationResult;
import com.infoshare.view.composers.AnalysisValidationMessageComposer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class IVRValidationStrategy implements AnalysisValidationStrategy {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private BigDecimalValidator bigDecimalValidator = new BigDecimalValidator();
    private StringLengthValidator stringLengthValidator = new StringLengthValidator();
    private DateFormatValidator dateFormatValidator = new DateFormatValidator();
    private IVRArgs ivrArgs;

    private AnalysisValidationMessageComposer messageComposer = new AnalysisValidationMessageComposer();

    @Override
    public AnalysisValidationResult doValidationAlgorithm(String[] args) {

        ivrArgs = new IVRArgs(args);
        List<ArgValidationResult> finalValidationResults = new ArrayList<>();

        finalValidationResults.addAll(doArgValidation(ivrArgs));
        if (isValid(finalValidationResults)) {
            finalValidationResults.add(doCrossArgValidation(ivrArgs));
        }

        return new AnalysisValidationResult(
                this.isValid(finalValidationResults),
                messageComposer.composeErrorMessage(finalValidationResults),
                args);
    }

    private List<ArgValidationResult> doArgValidation(IVRArgs args) {
        List<ArgValidationResult> results = new ArrayList<>();
        results.add(stringLengthValidator.doValidate(IVRArgs.ANALYSIS_COMMAND_STRING));
        results.add(bigDecimalValidator.doValidate(args.getCapital()));
        results.add(dateFormatValidator.doValidate(args.getStartDate()));
        results.add(dateFormatValidator.doValidate(args.getEndDate()));
        return results;
    }

    private ArgValidationResult doCrossArgValidation(IVRArgs args) {
        List<LocalDate> expectedOrderOfDateArgValues = new LinkedList<>();
        expectedOrderOfDateArgValues.add(LocalDate.parse(args.getStartDate(), FORMATTER));
        expectedOrderOfDateArgValues.add(LocalDate.parse(args.getEndDate(), FORMATTER));

        return new DatesOrderValidator(expectedOrderOfDateArgValues).doValidate();
    }

    private boolean isValid(List<ArgValidationResult> results) {

        boolean isResultsListValid = false;

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
