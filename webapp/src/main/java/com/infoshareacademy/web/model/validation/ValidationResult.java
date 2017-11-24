package com.infoshareacademy.web.model.validation;

import com.infoshareacademy.web.model.analyzer.criterias.WebAnalysisCriteria;
import com.infoshareacademy.web.model.validation.forms.CriteriaForm;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import java.util.Set;

public class ValidationResult {

    private Set<ConstraintViolation<CriteriaForm>> violations;
    private HttpServletRequest req;
    private WebAnalysisCriteria criteria;


    public Set<ConstraintViolation<CriteriaForm>> getViolations() {
        return violations;
    }

    public HttpServletRequest getReq() {
        return req;
    }

    public WebAnalysisCriteria getCriteria() {
        return criteria;
    }

    public ValidationResult(Set<ConstraintViolation<CriteriaForm>> violations,
                            HttpServletRequest req, WebAnalysisCriteria criteria) {
        this.violations = violations;
        this.criteria = criteria;
        this.req = req;
    }
}
