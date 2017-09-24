package com.infoshare.model.arguments;

public class INDArgs {

    private static final int COMMAND_ARGS_NUMBER = 2;
    public static final String ANALYSIS_COMMAND_STRING = "IND";

    private String investmentName;

    public String getInvestmentName() {
        return investmentName;
    }

    public INDArgs(String[] args) {
        this.investmentName = args[1];
    }
}
