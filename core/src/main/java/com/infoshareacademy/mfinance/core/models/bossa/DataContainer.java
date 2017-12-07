package com.infoshareacademy.mfinance.core.models.bossa;

import java.util.ArrayList;
import java.util.List;

public class DataContainer {
    private int fundsCount = 0;
    private int currenciesCount = 0;
    private List<Investment> investments = new ArrayList<>();

    public DataContainer(int fundsCount, int currenciesCount, List<Investment> investments) {
        this.fundsCount = fundsCount;
        this.currenciesCount = currenciesCount;
        this.investments = investments;
    }
    public int getFundsCount() {
        return fundsCount;
    }

    public int getCurrenciesCount() {
        return currenciesCount;
    }
    
    public List<Investment> getInvestments() {
        return investments;
    }

}
