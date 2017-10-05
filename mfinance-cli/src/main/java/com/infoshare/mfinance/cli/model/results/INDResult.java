package com.infoshare.mfinance.cli.model.results;


import com.infoshare.mfinance.cli.model.results.embeded.CLIQuotation;

public class INDResult extends AnalysisResult  {

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

    public CLIQuotation getLastCLIQuotation() {
        return lastCLIQuotation;
    }

    public String getName() {
        return name;
    }

    public CLIQuotation getMaxValueCLIQuotation() {
        return maxValueCLIQuotation;
    }

    public CLIQuotation getMaxDeltaMinus() {
        return maxDeltaMinus;
    }

    public CLIQuotation getMaxDeltaPlus() {
        return maxDeltaPlus;
    }

    public CLIQuotation getMinValueCLIQuotation() {
        return minValueCLIQuotation;
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

    @Override
    public String toString() {
        return "INDResult{" +
                "name='" + name + '\'' +
                ", firstCLIQuotation=" + firstCLIQuotation +
                ", lastCLIQuotation=" + lastCLIQuotation +
                ", maxValueCLIQuotation=" + maxValueCLIQuotation +
                ", minValueCLIQuotation=" + minValueCLIQuotation +
                ", maxDeltaPlus=" + maxDeltaPlus +
                ", maxDeltaMinus=" + maxDeltaMinus +
                '}';
    }
}
