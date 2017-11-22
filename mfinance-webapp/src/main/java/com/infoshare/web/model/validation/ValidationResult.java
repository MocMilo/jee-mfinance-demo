package com.infoshare.web.model.validation;

import com.infoshare.web.model.analyzer.criterias.WebAnalysisCriteria;
import com.infoshare.web.model.validation.forms.CriteriaForm;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import java.util.Set;

public class ValidationResult {

    private WebAnalysisCriteria criteria;
    private Set<ConstraintViolation<CriteriaForm>> violations;
    private HttpServletRequest req;

    public WebAnalysisCriteria  getCriteria() {
        return criteria;
    }

    public Set<ConstraintViolation<CriteriaForm>> getViolations() {
        return violations;
    }

    public HttpServletRequest getReq() {
        return req;
    }

    public ValidationResult(Set<ConstraintViolation<CriteriaForm>> violations,
                            HttpServletRequest req, WebAnalysisCriteria criteria) {
        this.violations = violations;
        this.criteria = criteria;
        this.req = req;
    }
}
