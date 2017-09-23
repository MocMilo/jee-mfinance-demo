package com.infoshare;

import com.infoshare.controller.validators.ValidationContext;
import com.infoshare.model.validationResults.AnalysisValidatorResult;
import com.infoshare.view.ConsoleMessage;

public class App {
    public static void main(String[] args) {

        if (args != null && args.length > 0) {

            String analysisCommandName = args[0];
            AnalysisValidatorResult result =  ValidationContext.doValidate(analysisCommandName, args);
            System.out.print(result.getErrMessage());
            // TODO validated analysis processing
            // TODO analysis results presentation


        } else {
            System.out.print(ConsoleMessage.WELCOME_MESSAGE
                    + ConsoleMessage.COMMAND_HELP_MESSAGE
                    + ConsoleMessage.ANALYSIS_OPTIONS);
            System.out.println("\nno arguments!");
        }

    }
}
