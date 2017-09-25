package com.infoshare.view.composers.crossargument;

import com.infoshare.model.validationResults.crossargument.DatesOrderValidationResult;

import java.util.List;

public class DatesOrderValidationMessageComposer {

    private static final String ERR_MESSAGE = "\nWrong date args order.";

    public String composeErrorMessage(List<DatesOrderValidationResult> results) {

        StringBuilder sb = new StringBuilder();

        for (DatesOrderValidationResult item : results) {
            if (!item.isIdValidOrder()) {
                sb.append("\n"
                        .concat(ERR_MESSAGE)
                        .concat("\nDate:")
                        .concat(item.getExpectedFirst().toString())
                        .concat(" should be before:"
                        .concat(item.getExpectedAsSecond().toString()
                        .concat("\n"))));
            }
        }
        return sb.toString();
    }
}
