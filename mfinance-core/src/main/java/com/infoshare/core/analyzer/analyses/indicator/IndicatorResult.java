package com.infoshare.core.analyzer.analyses.indicator;

import com.infoshare.core.analyzer.analyses.AnalysisResult;
import com.infoshare.core.model.Quotation;

public class IndicatorResult extends AnalysisResult {


    private String name;
    private Quotation firstQuotation;
    private Quotation lastQuotation;
    private Quotation maxValueQuotation;
    private Quotation minValueQuotation;
    private Quotation maxDeltaPlus;
    private Quotation maxDeltaMinus;
    private Quotation actualValue;


    public Quotation getFirstQuotation() {
        return firstQuotation;
    }

    public void setFirstQuotation(Quotation firstQuotation) {
        this.firstQuotation = firstQuotation;
    }

    public Quotation getLastQuotation() {
        return lastQuotation;
    }

    public void setLastQuotation(Quotation lastQuotation) {
        this.lastQuotation = lastQuotation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Quotation getActualValue() {
        return actualValue;
    }

    public void setActualValue(Quotation actualValue) {
        this.actualValue = actualValue;
    }

    public Quotation getMaxValueQuotation() {
        return maxValueQuotation;
    }

    public void setMaxValueQuotation(Quotation maxValueQuotation) {
        this.maxValueQuotation = maxValueQuotation;
    }

    public Quotation getMaxDeltaMinus() {
        return maxDeltaMinus;
    }

    public void setMaxDeltaMinus(Quotation maxDeltaMinus) {
        this.maxDeltaMinus = maxDeltaMinus;
    }

    public Quotation getMaxDeltaPlus() {
        return maxDeltaPlus;
    }

    public void setMaxDeltaPlus(Quotation maxDeltaPlus) {
        this.maxDeltaPlus = maxDeltaPlus;
    }


    public Quotation getMinValueQuotation() {
        return minValueQuotation;
    }

    public void setMinValueQuotation(Quotation minValueQuotation) {
        this.minValueQuotation = minValueQuotation;
    }


    @Override
    public String toString() {
        return "IndicatorResult{" +
                "name='" + name + '\'' +
                ", \nfirstQuotation=" + firstQuotation +
                ", \nlastQuotation=" + lastQuotation +
                ", \nmaxValueQuotation=" + maxValueQuotation +
                ", \nminValueQuotation=" + minValueQuotation +
                ", \nmaxDeltaPlus=" + maxDeltaPlus +
                ", \nmaxDeltaMinus=" + maxDeltaMinus +
                ", \nactualValue=" + actualValue +
                '}';
    }
}

