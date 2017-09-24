package com.infoshare.model.analysisResults;


public class INDResult {

    private String name;

    private CLIQuotation firstCLIQuotation;
    private CLIQuotation lastCLIQuotation;
    private CLIQuotation maxValueCLIQuotation;
    private CLIQuotation minValueCLIQuotation;
    private CLIQuotation maxDeltaPlus;
    private CLIQuotation maxDeltaMinus;


    public CLIQuotation getFirstCLIQuotation() {
        return firstCLIQuotation;
    }

    public void setFirstCLIQuotation(CLIQuotation firstCLIQuotation) {
        this.firstCLIQuotation = firstCLIQuotation;
    }

    public CLIQuotation getLastCLIQuotation() {
        return lastCLIQuotation;
    }

    public void setLastCLIQuotation(CLIQuotation lastCLIQuotation) {
        this.lastCLIQuotation = lastCLIQuotation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CLIQuotation getMaxValueCLIQuotation() {
        return maxValueCLIQuotation;
    }

    public void setMaxValueCLIQuotation(CLIQuotation maxValueCLIQuotation) {
        this.maxValueCLIQuotation = maxValueCLIQuotation;
    }

    public CLIQuotation getMaxDeltaMinus() {
        return maxDeltaMinus;
    }

    public void setMaxDeltaMinus(CLIQuotation maxDeltaMinus) {
        this.maxDeltaMinus = maxDeltaMinus;
    }

    public CLIQuotation getMaxDeltaPlus() {
        return maxDeltaPlus;
    }

    public void setMaxDeltaPlus(CLIQuotation maxDeltaPlus) {
        this.maxDeltaPlus = maxDeltaPlus;
    }


    public CLIQuotation getMinValueCLIQuotation() {
        return minValueCLIQuotation;
    }

    public void setMinValueCLIQuotation(CLIQuotation minValueCLIQuotation) {
        this.minValueCLIQuotation = minValueCLIQuotation;
    }


    public INDResult(String name,
                     CLIQuotation firstCLIQuotation,
                     CLIQuotation lastCLIQuotation,
                     CLIQuotation maxValueCLIQuotation,
                     CLIQuotation minValueCLIQuotation,
                     CLIQuotation maxDeltaPlus,
                     CLIQuotation maxDeltaMinus
                    ) {
        this.name = name;
        this.firstCLIQuotation = firstCLIQuotation;
        this.lastCLIQuotation = lastCLIQuotation;
        this.maxValueCLIQuotation = maxValueCLIQuotation;
        this.minValueCLIQuotation = minValueCLIQuotation;
        this.maxDeltaPlus = maxDeltaPlus;
        this.maxDeltaMinus = maxDeltaMinus;

    }

}
