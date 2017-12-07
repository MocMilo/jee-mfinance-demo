package com.infoshareacademy.mfinance.cli.model.arguments;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class INDArgs extends ApplicationArguments implements Serializable {

    @NotNull(message = "Wrong investment name, should not be empty")
    @NotBlank(message = "Investment name cannot be blank")
    @Size(min = 3, max = 8, message = "Name needs to be at least 3 characters long but not longer than 8")
    private String investmentName;

    public INDArgs(String[] args) {
        this.strategy = args[0];
        this.investmentName = args[1];
    }

    public String getInvestmentName() {
        return investmentName;
    }
}
