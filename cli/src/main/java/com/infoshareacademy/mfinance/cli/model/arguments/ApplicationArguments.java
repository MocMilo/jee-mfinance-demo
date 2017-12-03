package com.infoshareacademy.mfinance.cli.model.arguments;

public abstract class ApplicationArguments {
    protected String strategy;

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }
}
