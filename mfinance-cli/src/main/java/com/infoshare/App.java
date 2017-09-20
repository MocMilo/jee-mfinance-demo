package com.infoshare;

import com.infoshare.view.ConsoleMessage;

public class App {
    public static void main(String[] args) {

        System.out.print(ConsoleMessage.WELCOME_MESSAGE
                + ConsoleMessage.COMMAND_HELP_MESSAGE
                + ConsoleMessage.ANALYSIS_OPTIONS
                + ConsoleMessage.APP_OPTIONS);

        // TODO input processing
    }
}
