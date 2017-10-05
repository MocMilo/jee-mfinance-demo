package com.infoshare.mfinance.cli.view;

public class ConstantMessagesProvider {

    public static final String WELCOME_MESSAGE = "\n*** WELCOME TO MFINANCE-CLI ***\n";
    public static final String ANALYSIS_OPTIONS_MESSAGE ="\nAvailable analyses:"
            +"\n  Investment Revenue: IVR <investment_name> <capital> <startDate> <endDate>"
            +"\n  Investment Indicators: IND <investment_name>\n";

    public static final String COMMAND_HELP_MESSAGE ="\nTo get analysis result type command:\n"
            +"\n  -analysis_name <arg1> <arg2> <arg3>...\n";
}
