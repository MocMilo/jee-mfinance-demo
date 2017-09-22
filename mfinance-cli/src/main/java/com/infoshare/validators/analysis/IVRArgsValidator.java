package com.infoshare.validators.analysis;

import com.infoshare.model.validationResults.AnalysisValidatorResult;
import com.infoshare.model.validationResults.ArgValidatorResult;
import com.infoshare.validators.argument.BigDecimalValidator;

import java.util.ArrayList;
import java.util.List;

public class IVRArgsValidator implements AnalysisArgsValidator {

    BigDecimalValidator bigDecimalValidator = new BigDecimalValidator();


    @Override
    public AnalysisValidatorResult doAnalysisValidation(String[] args) {

        List<ArgValidatorResult> results = new ArrayList<>();
        ArgValidatorResult capitalValidationResult = bigDecimalValidator.doValidate(args[2]);
        results.add(capitalValidationResult);

        return new AnalysisValidatorResult(this.isValid(results), composeErrorMessage(results), args);
    }

    private String composeErrorMessage(List<ArgValidatorResult> results) {

        StringBuilder sb = new StringBuilder();
        if (!isValid(results)) {
            for (ArgValidatorResult item : results) {

                sb.append("\nERROR: arg value:"
                        .concat(item.getEvaluatedValue())
                        .concat(" violation:"
                                .concat(item.getErrMessage()
                                        .concat("\n"))));
            }
        }
        else{
            sb.append("INFO:Arguments are valid.\n");
        }
        return sb.toString();
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
