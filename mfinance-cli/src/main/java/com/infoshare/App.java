package com.infoshare;

import com.infoshare.model.validationResults.AnalysisValidatorResult;
import com.infoshare.validators.analysis.IVRArgsValidator;
import com.infoshare.view.ConsoleMessage;

public class App {
    public static void main(String[] args) {



        // TODO analysis console commands processing

        if (args != null && args.length > 0) {
            IVRArgsValidator validator = new IVRArgsValidator();
            AnalysisValidatorResult result = validator.doAnalysisValidation(args);

            System.out.print(result.getErrMessage());

            // TODO analysis results processing and messages
        } else {
            System.out.print(ConsoleMessage.WELCOME_MESSAGE
                    + ConsoleMessage.COMMAND_HELP_MESSAGE
                    + ConsoleMessage.ANALYSIS_OPTIONS
                    + ConsoleMessage.APP_OPTIONS);
            System.out.println("\nno arguments!");
        }

    }
}
