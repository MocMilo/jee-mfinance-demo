package com.infoshare.model.arguments;

public class IVRArgs {

    private static final int COMMAND_ARGS_NUMBER = 5;
    public static final String ANALYSIS_COMMAND_STRING = "IVR";
    private String capital;
    private String investmentName;
    private String startDate;
    private String endDate;

    public IVRArgs(String[] args) {
        this.investmentName = args[1];
        this.capital = args[2];
        this.startDate = args[3];
        this.endDate = args[4];
    }

    public String getCapital() {
        return capital;
    }

    public String getInvestmentName() {
        return investmentName;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }
}
