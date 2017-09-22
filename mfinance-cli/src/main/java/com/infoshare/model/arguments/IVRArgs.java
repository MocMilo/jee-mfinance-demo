package com.infoshare.model.arguments;

public class IVRArgs {

    private static final int COMMAND_ARGS_NUMBER = 5;
    private static final String ANALYSIS_COMMAND_STRING = "IVR";
    private String capital;
    private String investmentName;
    private String startDate;
    private String endDate;

    public IVRArgs(String[] args) {
        this.capital = args[1];
        this.investmentName = args[2];
        this.startDate = args[3];
        this.endDate = args[4];
    }
}
