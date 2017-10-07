package com.infoshare.mfinance.cli.view;


import com.infoshare.mfinance.cli.services.parser.ParserResult;

public class MessageComposer {

    public void printMessage(ParserResult parserResult) {
        if (!parserResult.isValid()) {
            System.out.print(ConstantMessagesProvider.WELCOME_MESSAGE
                    + ConstantMessagesProvider.COMMAND_HELP_MESSAGE
                    + ConstantMessagesProvider.ANALYSIS_OPTIONS_MESSAGE);

            System.out.println(parserResult.getErrorMessage());
        }
    }
}
