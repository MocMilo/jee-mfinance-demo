package com.infoshare.controller;

import com.infoshare.controller.analyzer.Analyzer;
import com.infoshare.controller.validators.ValidationContext;
import com.infoshare.core.models.exceptions.NoDataForCriteria;
import com.infoshare.model.validationResults.AnalysisValidationResult;
import com.infoshare.view.WelcomeMessageComposer;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;


public class MainController {

    private AnalysisValidationResult result;
    private WelcomeMessageComposer composer = new WelcomeMessageComposer();

    public MainController() {
    }

    public void initialize(String[] args) throws NoDataForCriteria {

        // load main container (m-finance core)
        composer.printWelcomeMessage(args);
        doExecute(args);
        // print result message
    }

    private void doExecute(String[] args) throws NoDataForCriteria {
        if (args.length > 0) {
            result = ValidationContext.doValidate(args);
            if(!result.isValid()) {
                System.out.print(result.getErrMessage());
            }
            else {
                new Analyzer().doAnalyze(args);
            }
        }
    }

}
