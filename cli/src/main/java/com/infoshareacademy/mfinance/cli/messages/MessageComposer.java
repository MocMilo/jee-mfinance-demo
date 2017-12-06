package com.infoshareacademy.mfinance.cli.messages;

import com.infoshareacademy.mfinance.cli.model.results.AnalysisResult;
import com.infoshareacademy.mfinance.cli.model.ParserResult;
import static com.infoshareacademy.mfinance.cli.messages.ConstantMessagesProvider.*;

public class MessageComposer {
    public void printValidationMessage(ParserResult parserResult) {
        if (!parserResult.isValid()) {
            System.out.print(WELCOME_MESSAGE
                    + ANALYSIS_OPTIONS_MESSAGE
                    + COMMAND_HELP_MESSAGE);
            System.out.println(HORIZONTAL_LINE);
            System.out.println(parserResult.getErrorMessage().concat("\n"));
        }
    }

    public void printResultMessage(AnalysisResult analysisResult) {
        if (analysisResult != null) {
            System.out.println(analysisResult.toString());
        }
    }
}
