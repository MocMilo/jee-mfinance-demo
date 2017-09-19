package com.infoshare.core.models.analyses.criteria;

public class AnalysisCriteria {

    protected String investmentName;
    protected Boolean isFavourite = Boolean.valueOf(false);
    protected Boolean isModifiedBySuggester = Boolean.valueOf(false);

    public AnalysisCriteria() {
    }

    public String getInvestmentName() {
        return this.investmentName;
    }

    public void setInvestmentName(String investmentName) {
        this.investmentName = investmentName;
    }

    public Boolean getFavourite() {
        return this.isFavourite;
    }

    public void setFavourite(Boolean favourite) {
        this.isFavourite = favourite;
    }

    public Boolean getModifiedBySuggester() {
        return this.isModifiedBySuggester;
    }

    public void setModifiedBySuggester(Boolean modifiedBySuggester) {
        this.isModifiedBySuggester = modifiedBySuggester;
    }

    public boolean equals(Object o) {
        if(this == o) {
            return true;
        } else if(o != null && this.getClass() == o.getClass()) {
            AnalysisCriteria that = (AnalysisCriteria)o;
            return !this.investmentName.equals(that.investmentName)?false:(!this.isFavourite.equals(that.isFavourite)?false:this.isModifiedBySuggester.equals(that.isModifiedBySuggester));
        } else {
            return false;
        }
    }

    public int hashCode() {
        int result = this.investmentName.hashCode();
        result = 31 * result + this.isFavourite.hashCode();
        result = 31 * result + this.isModifiedBySuggester.hashCode();
        return result;
    }
}

