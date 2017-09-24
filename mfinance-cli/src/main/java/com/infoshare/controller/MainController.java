package com.infoshare.controller;

import com.infoshare.controller.analyzers.strategies.AnalysisContext;
import com.infoshare.controller.validators.strategies.ValidationContext;
import com.infoshare.core.models.exceptions.NoDataForCriteria;
import com.infoshare.model.validationResults.AnalysisValidationResult;
import com.infoshare.view.composers.WelcomeMessageComposer;


public class MainController {

    private AnalysisValidationResult result;
    private WelcomeMessageComposer composer = new WelcomeMessageComposer();

    public void initialize(String[] args) {
        composer.printWelcomeMessage(args);
        doExecute(args);
    }

    private void doExecute(String[] args) {
        try {
            if (args.length > 0) {
                result = ValidationContext.doValidate(args);
                if (!result.isValid()) {
                    System.out.print(result.getErrMessage());
                } else {
                    AnalysisContext.doAnalyze(args);
                }
            }
        } catch (NoDataForCriteria e) {
            System.out.printf(e.getMessage() + "\n");
        } catch (IllegalArgumentException e) {
            System.out.printf(e.getMessage() + "\n");
        }
    }
}
