package com.infoshare.view;

public class WelcomeMessageComposer {

    public void printWelcomeMessage(String[] args) {
        if (args.length == 0) {
            System.out.print(ConstantMessagesProvider.WELCOME_MESSAGE
                    + ConstantMessagesProvider.COMMAND_HELP_MESSAGE
                    + ConstantMessagesProvider.ANALYSIS_OPTIONS_MESSAGE);
            System.out.println(ConstantMessagesProvider.NO_ARGS_MESSAGE);
        }
    }
}
