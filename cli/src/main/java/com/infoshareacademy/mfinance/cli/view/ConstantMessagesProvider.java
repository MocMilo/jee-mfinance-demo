package com.infoshareacademy.mfinance.cli.view;

class ConstantMessagesProvider {
    static final String WELCOME_MESSAGE = "\n*** Welcome to mfinance-CLI ***\n";
    static final String ANALYSIS_OPTIONS_MESSAGE = "\nAvailable analyses:"
            + "\n 1. Investment Indicators: IND <investment_name>"
            + "\n 2. Investment Revenue: IVR <investment_name> <capital_PLN> <buyDate> <sellDate>\n";

    static final String COMMAND_HELP_MESSAGE = "\nTo get analysis result type command using pattern:\n"
            + "\n  <analysis_name> <arg1> <arg2> <arg3>...\n"
            + "\n examples of commands: "
            + "\n $ java -jar mfinance-jar-with-dependencies.jar IND USD"
            + "\n $ java -jar mfinance-jar-with-dependencies.jar IND AGI003"
            + "\n $ java -jar mfinance-jar-with-dependencies.jar IVR EUR 2500.00 2015-12-02 2017-12-01"
            + "\n $ java -jar mfinance-jar-with-dependencies.jar IVR ING001 1600.00 2009-12-02 2016-12-01\n"
            + "\n";

    static final String HORIZONTAL_LINE = "---------------------------------------------------";
}
