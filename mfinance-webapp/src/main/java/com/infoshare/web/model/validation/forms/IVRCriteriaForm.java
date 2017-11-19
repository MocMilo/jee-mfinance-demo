package com.infoshare.web.model.validation.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class IVRCriteriaForm extends CriteriaForm {

    private String investmentName;
    private String capital;
    private String buyDate;
    private String sellDate;

    @NotNull(message = "Investment name cannot be empty")
    @NotBlank(message = "Investment name cannot be blank")
    @Size(min = 3, max = 8, message = "Name needs to be at least 3 characters long but not longer than 8")
    public String getInvestmentName() {
        return investmentName;
    }

    public void setInvestmentName(String investmentName) {
        this.investmentName = investmentName;
    }

    @NotNull(message = "Investment capital cannot be empty")
    @NotBlank(message = "Investment capital cannot be blank")
    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    @NotNull(message = "Buy date cannot be empty")
    @NotBlank(message = "Buy date cannot be blank")  //TODO date pattern
    public String getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(String buyDate) {
        this.buyDate = buyDate;
    }

    @NotNull(message = "Sell date cannot be empty")
    @NotBlank(message = "Sell date cannot be blank") //TODO date pattern
    public String getSellDate() {
        return sellDate;
    }

    public void setSellDate(String sellDate) {
        this.sellDate = sellDate;
    }


}
