package com.infoshare.view;

public class ConsoleMessage {

    public static final String WELCOME_MESSAGE = "\n*** WELCOME TO MFINANCE-CLI ***\n";
    public static final String ANALYSIS_OPTIONS ="\nAvailable analyses:"
            +"\n  Investment Revenue: IVR <investment_name> <capital> <startDate> <endDate>"
            +"\n  Indicators Comparator: INC <investment_name1> <investment_name2>\n";

    public static final String COMMAND_HELP_MESSAGE ="\nTo get analysis result type command:\n"
            +"\n  -analysis_name <arg1> <arg2> <arg3>...\n";

    public static final String APP_OPTIONS ="\n-q to quit application\n";
}
