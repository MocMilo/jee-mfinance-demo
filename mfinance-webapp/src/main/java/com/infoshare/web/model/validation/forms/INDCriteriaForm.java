package com.infoshare.web.model.validation.forms;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class INDCriteriaForm extends CriteriaForm {


    private String investmentName;



    @NotNull(message = "Investment name cannot be empty")
    @NotBlank(message = "Investment name cannot be blank")
    @Size(min = 3, max = 8, message = "Name needs to be at least 3 characters long but not longer than 8")
    public String getInvestmentName() {
        return investmentName;
    }

    public void setInvestmentName(String investmentName) {
        this.investmentName = investmentName;
    }


}
