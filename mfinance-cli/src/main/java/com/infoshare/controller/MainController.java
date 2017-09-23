package com.infoshare.controller;

import com.infoshare.controller.validators.ValidationContext;
import com.infoshare.model.validationResults.AnalysisValidationResult;
import com.infoshare.view.WelcomeMessageComposer;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;


public class MainController {

    private AnalysisValidationResult result;
    private WelcomeMessageComposer composer = new WelcomeMessageComposer();

    public MainController() {
    }

    public void initialize(String[] args) {

        // load main container (m-finance core)
        composer.printWelcomeMessage(args);
        doExecute(args);
        // print result message
    }




    private void doExecute(String[] args) {
        if (args.length > 0) {
            result = ValidationContext.doValidate(args);
            if(!result.isValid()) {
                System.out.print(result.getErrMessage());
            }
            else {
               // System.out.print(result.getErrMessage());
                //  doAnalyze(result);
            }
        }

    }

    private void doAnalyze(AnalysisValidationResult result) {

        throw new NotImplementedException();
        // TODO analysis processing
        // TODO analysis results presentation
    }

}
