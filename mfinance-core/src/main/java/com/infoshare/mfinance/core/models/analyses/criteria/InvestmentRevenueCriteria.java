package com.infoshare.mfinance.core.models.analyses.criteria;

import java.math.BigDecimal;
import java.time.LocalDate;


public class InvestmentRevenueCriteria extends AnalysisCriteria {
    private BigDecimal investedCapital;
    private LocalDate buyDate;
    private LocalDate sellDate;

    public BigDecimal getInvestedCapital() {
        return this.investedCapital;
    }

    public void setInvestedCapital(BigDecimal investedCapital) {
        this.investedCapital = investedCapital;
    }

    public LocalDate getBuyDate() {
        return this.buyDate;
    }

    public LocalDate getSellDate() {
        return this.sellDate;
    }

    public void setSellDate(LocalDate sellDate) {
        this.sellDate = sellDate;
    }

    public void setBuyDate(LocalDate buyDate) {
        this.buyDate = buyDate;
    }

    public InvestmentRevenueCriteria() {
    }

    public InvestmentRevenueCriteria(BigDecimal investedCapital, LocalDate buyDate, LocalDate sellDate, String investmentName){
        this.investmentName = investmentName;
        this.investedCapital = investedCapital;
        this.buyDate = buyDate;
        this.sellDate = sellDate;
    }
}

