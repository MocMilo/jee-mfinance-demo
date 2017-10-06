package com.infoshare.mfinance.cli.model.arguments;

public class INDArgs extends ApplicationArguments {

    private String investmentName;

    public String getInvestmentName() {
        return investmentName;
    }

    public INDArgs(String[] args) {
        this.strategy = args[0];
        this.investmentName = args[1];
    }

}
