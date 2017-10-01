package com.infoshare.mfinance.cli.model.arguments;

public class IVRArgs extends ApplicationArguments {


    private String capital;
    private String investmentName;
    private String startDate;
    private String endDate;

    public IVRArgs(String[] args) {
        this.setStrategy(args[0]);
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
