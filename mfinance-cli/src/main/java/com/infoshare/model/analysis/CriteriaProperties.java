package com.infoshare.model.analysis;

import com.infoshare.core.models.analyses.criteria.AnalysisCriteria;


public class CriteriaProperties {

    public int argsNumber;
    public String commandAnalysisShortName;
    public AnalysisCriteria analysisCriteria;

    public int getArgsNumber() {
        return argsNumber;
    }

    public void setArgsNumber(int argsNumber) {
        this.argsNumber = argsNumber;
    }

    public String getCommandAnalysisShortName() {
        return commandAnalysisShortName;
    }

    public void setCommandAnalysisShortName(String commandAnalysisShortName) {
        this.commandAnalysisShortName = commandAnalysisShortName;
    }

    public AnalysisCriteria getAnalysisCriteria() {
        return analysisCriteria;
    }

    public void setAnalysisCriteria(AnalysisCriteria analysisCriteria) {
        this.analysisCriteria = analysisCriteria;
    }
}
