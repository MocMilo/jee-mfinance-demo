package com.infoshare.mfinance.cli.view;


import com.infoshare.mfinance.cli.model.results.AnalysisResult;
import com.infoshare.mfinance.cli.services.parser.ParserResult;

public class MessageComposer {

    public void printValidationMessage(ParserResult parserResult) {
        if (!parserResult.isValid()) {
            System.out.print(ConstantMessagesProvider.WELCOME_MESSAGE
                    + ConstantMessagesProvider.COMMAND_HELP_MESSAGE
                    + ConstantMessagesProvider.ANALYSIS_OPTIONS_MESSAGE);

            System.out.println(parserResult.getErrorMessage());
        }
    }

    public void printResultMessage(AnalysisResult analysisResult) {
        if (analysisResult != null) {
            System.out.println(analysisResult.toString());
        }
    }


}
