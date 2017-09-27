package com.infoshare.controller.validators.argument.crossargumentvalidators;

import com.infoshare.model.validationResults.ArgValidationResult;
import com.infoshare.model.validationResults.crossargument.DatesOrderValidationResult;
import com.infoshare.view.composers.validation.crossargument.DatesOrderValidationMessageComposer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DatesOrderValidator {

    private List<LocalDate> expectedOrderOfDateArgValues = new LinkedList<>();
    private List<DatesOrderValidationResult> validationResults = new ArrayList<>();
    private ArgValidationResult argValidationResult;
    private String arg = "";
    DatesOrderValidationMessageComposer composer = new DatesOrderValidationMessageComposer();

    public DatesOrderValidator(List<LocalDate> expectedOrderOfDateArgValues) {
        this.expectedOrderOfDateArgValues = expectedOrderOfDateArgValues;
    }

    public ArgValidationResult doValidate() {

        boolean isValid = this.isValid(getOrderValidationResults());
        String message = composer.composeErrorMessage(validationResults);
        return argValidationResult = new ArgValidationResult(isValid, arg,message);
    }

    private boolean isValid(List<DatesOrderValidationResult> validationResults) {
        boolean isValid = true;
        for (DatesOrderValidationResult item : validationResults) {
            if (!item.isIdValidOrder()) {
                isValid = false;
                break;
            }
        }
        return isValid;
    }

    private List<DatesOrderValidationResult> getOrderValidationResults() {
        for (int i = 0; i < expectedOrderOfDateArgValues.size(); i++) {

            arg.concat(expectedOrderOfDateArgValues.get(i).toString());

            if (i >= 1) {
                LocalDate expectedAsFirst = expectedOrderOfDateArgValues.get(i - 1);
                LocalDate expectedAsSecond = expectedOrderOfDateArgValues.get(i);

                DatesOrderValidationResult result = new DatesOrderValidationResult(
                        expectedAsFirst,
                        expectedAsSecond,
                        expectedAsFirst.isBefore(expectedAsSecond));

                validationResults.add(result);
            }
        }
        return validationResults;
    }
}
