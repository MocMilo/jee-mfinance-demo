package com.infoshare.core.models.analyses.criteria;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
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

    public InvestmentRevenueCriteria(BigDecimal investedCapital, LocalDate buyDate, LocalDate sellDate, String investmentName, Boolean isFavourite) {
        this.investmentName = investmentName;
        this.investedCapital = investedCapital;
        this.buyDate = buyDate;
        this.sellDate = sellDate;
        this.isFavourite = isFavourite;
    }

    public InvestmentRevenueCriteria(InvestmentRevenueCriteria itemToCopy) {
        this.investmentName = itemToCopy.investmentName;
        this.investedCapital = itemToCopy.investedCapital;
        this.buyDate = itemToCopy.buyDate;
        this.sellDate = itemToCopy.sellDate;
        this.isModifiedBySuggester = itemToCopy.isModifiedBySuggester;
        this.isFavourite = itemToCopy.isFavourite;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            if (!super.equals(o)) {
                return false;
            } else {
                InvestmentRevenueCriteria that = (InvestmentRevenueCriteria) o;
                return !this.investedCapital.equals(that.investedCapital) ? false : (!this.buyDate.equals(that.buyDate) ? false : this.sellDate.equals(that.sellDate));
            }
        } else {
            return false;
        }
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + this.investedCapital.hashCode();
        result = 31 * result + this.buyDate.hashCode();
        result = 31 * result + this.sellDate.hashCode();
        return result;
    }
}


