package com.infoshareacademy.mfinance.cli.model.results;

import com.infoshareacademy.mfinance.cli.model.results.embeded.CLIQuotation;

public class INDResult extends AnalysisResult {
    private String name;
    private CLIQuotation firstQuotation;
    private CLIQuotation lastQuotation;
    private CLIQuotation maxValueQuotation;
    private CLIQuotation minValueQuotation;
    private CLIQuotation maxDeltaPlus;
    private CLIQuotation maxDeltaMinus;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CLIQuotation getFirstQuotation() {
        return firstQuotation;
    }

    public void setFirstQuotation(CLIQuotation firstQuotation) {
        this.firstQuotation = firstQuotation;
    }

    public CLIQuotation getLastQuotation() {
        return lastQuotation;
    }

    public void setLastQuotation(CLIQuotation lastQuotation) {
        this.lastQuotation = lastQuotation;
    }

    public CLIQuotation getMaxValueQuotation() {
        return maxValueQuotation;
    }

    public void setMaxValueQuotation(CLIQuotation maxValueQuotation) {
        this.maxValueQuotation = maxValueQuotation;
    }

    public CLIQuotation getMinValueQuotation() {
        return minValueQuotation;
    }

    public void setMinValueQuotation(CLIQuotation minValueQuotation) {
        this.minValueQuotation = minValueQuotation;
    }

    public CLIQuotation getMaxDeltaPlus() {
        return maxDeltaPlus;
    }

    public void setMaxDeltaPlus(CLIQuotation maxDeltaPlus) {
        this.maxDeltaPlus = maxDeltaPlus;
    }

    public CLIQuotation getMaxDeltaMinus() {
        return maxDeltaMinus;
    }

    public void setMaxDeltaMinus(CLIQuotation maxDeltaMinus) {
        this.maxDeltaMinus = maxDeltaMinus;
    }

    @Override
    public String toString() {
        return "INDResult{" +
                "\ninvestmentName='" + name + '\'' +
                ", \nfirstQuotationDate=" + firstQuotation.getDate() +
                ", \nfirstQuotationValue=" + firstQuotation.getClose() +
                ", \nfirstQuotationDeltaValue=" + firstQuotation.getDeltaClose() +

                ", \nlastQuotationDate=" + lastQuotation.getDate() +
                ", \nlastQuotationValue=" + lastQuotation.getClose() +
                ", \nlastQuotationDeltaValue=" + lastQuotation.getDeltaClose() +

                ", \nmaxValueQuotationDate=" + maxValueQuotation.getDate() +
                ", \nmaxValueQuotationValue=" + maxValueQuotation.getClose() +
                ", \nmaxValueDeltaQuotationDeltaValue=" + maxValueQuotation.getDeltaClose() +

                ", \nminValueQuotationDate=" + minValueQuotation.getDate() +
                ", \nminValueQuotationValue=" + minValueQuotation.getClose() +
                ", \nminValueQuotationDeltaValue=" + minValueQuotation.getDeltaClose() +

                ", \nmaxDeltaPlusDate=" + maxDeltaPlus.getDate() +
                ", \nmaxDeltaPlusValue=" + maxDeltaPlus.getClose() +
                ", \nmaxDeltaPlusDeltaValue=" + maxDeltaPlus.getDeltaClose() +

                ", \nmaxDeltaMinusDate=" + maxDeltaMinus.getDate() +
                ", \nmaxDeltaMinusValue=" + maxDeltaMinus.getClose() +
                ", \nmaxDeltaMinusDeltaValue=" + maxDeltaMinus.getDeltaClose() +
                '}';
    }
}
