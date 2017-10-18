package com.infoshare.mfinance.core.models.bossa;

import java.util.ArrayList;
import java.util.List;

public class DataContainer {

    private int fundsCount = 0;
    private int currenciesCount = 0;

    private List<Investment> investments = new ArrayList<>();

    public List<Investment> getInvestments() {
        return investments;
    }

    public int getFundsCount() {
        return fundsCount;
    }

    public void setFundsCount(int fundsCount) {
        this.fundsCount = fundsCount;
    }

    public int getCurrenciesCount() {
        return currenciesCount;
    }

    public void setCurrenciesCount(int currenciesCount) {
        this.currenciesCount = currenciesCount;
    }

    public void setInvestments(List<Investment> investments) {
        this.investments = investments;
    }

    @Override
    public String toString() {
        return "DataContainer{" +
                "investments=" + investments +
                '}';
    }
}
