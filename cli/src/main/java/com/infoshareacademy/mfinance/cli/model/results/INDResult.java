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


    public INDResult() {
    }

    @Override
    public String toString() {
        return "INDResult{" +
                "\nname='" + name + '\'' +
                ", \nfirstQuotation=" + firstQuotation.getClose() +
                ", \nfirstQuotation=" + firstQuotation.getDate() +
                ", \nfirstQuotation=" + firstQuotation.getDeltaClose() +

                ", \nlastQuotation=" + lastQuotation.getClose() +
                ", \nlastQuotation=" + lastQuotation.getDate() +
                ", \nlastQuotation=" + lastQuotation.getDeltaClose() +

                ", \nmaxValueQuotation=" + maxValueQuotation.getClose() +
                ", \nmaxValueQuotation=" + maxValueQuotation.getDate() +
                ", \nmaxValueQuotation=" + maxValueQuotation.getDeltaClose() +

                ", \nminValueQuotation=" + minValueQuotation.getClose() +
                ", \nminValueQuotation=" + minValueQuotation.getDate() +
                ", \nminValueQuotation=" + minValueQuotation.getDeltaClose() +

                ", \nmaxDeltaPlus=" + maxDeltaPlus.getClose() +
                ", \nmaxDeltaPlus=" + maxDeltaPlus.getDate() +
                ", \nmaxDeltaPlus=" + maxDeltaPlus.getDeltaClose() +

                ", \nmaxDeltaMinus=" + maxDeltaMinus.getClose() +
                ", \nmaxDeltaMinus=" + maxDeltaMinus.getDate() +
                ", \nmaxDeltaMinus=" + maxDeltaMinus.getDeltaClose() +
                '}';
    }
}
