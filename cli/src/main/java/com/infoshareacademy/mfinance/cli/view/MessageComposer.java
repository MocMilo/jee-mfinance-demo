package com.infoshareacademy.mfinance.cli.view;

import com.infoshareacademy.mfinance.cli.model.results.AnalysisResult;
import com.infoshareacademy.mfinance.cli.model.ParserResult;

public class MessageComposer {
    public void printValidationMessage(ParserResult parserResult) {
        if (!parserResult.isValid()) {
            System.out.print(ConstantMessagesProvider.WELCOME_MESSAGE
                    + ConstantMessagesProvider.ANALYSIS_OPTIONS_MESSAGE
                    + ConstantMessagesProvider.COMMAND_HELP_MESSAGE);

            System.out.println(parserResult.getErrorMessage());
        }
    }

    public void printResultMessage(AnalysisResult analysisResult) {
        if (analysisResult != null) {
            System.out.println(analysisResult.toString());
        }
    }
}
