package com.infoshare.core.models.analyses.criteria;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class IndicatorCriteria extends AnalysisCriteria {
    private String name;

    public String getName() {
        return name;
    }

    public IndicatorCriteria(String name) {
        this.name = name;
    }

    public IndicatorCriteria() {
    }
}
