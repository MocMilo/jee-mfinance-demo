package com.infoshareacademy.web.model.validation.forms;

import javax.validation.GroupSequence;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.infoshareacademy.web.services.validators.classlevel.FieldsCheck;
import com.infoshareacademy.web.services.validators.classlevel.ValidIVRDates;
import org.hibernate.validator.constraints.NotBlank;

@ValidIVRDates
@GroupSequence({FieldsCheck.class, IVRCriteriaForm.class})
public class IVRCriteriaForm extends CriteriaForm {

    @NotNull(message = "Investment name cannot be empty")
    @NotBlank(message = "Investment name cannot be blank")
    @Size(min = 3, max = 8, message = "Name needs to be at least 3 characters long but not longer than 8")
    private String investmentName;

    @NotNull(message = "Investment capital cannot be empty")
    @NotBlank(message = "Investment capital cannot be blank")
    @Pattern(regexp = "^(0|0?[1-9]\\d*)\\.\\d\\d$", message = "Wrong Investment capital argument: should be decimal of format:\"1.00\"")
    @DecimalMin(value = "0.01", message = "Capital value must be > 0.00\n")
    private String capital;

    @NotNull(message = "Buy date cannot be empty", groups = FieldsCheck.class)
    @NotBlank(message = "Buy date cannot be blank")
    @Pattern(regexp = "^[0-9]{4}-[0-1][0-9]-[0-3][0-9]$", groups = FieldsCheck.class, message = "Wrong buy date format, should be of pattern: YYYY-MM-DD.")
    private String buyDate;

    @NotNull(message = "Sell date cannot be empty", groups = FieldsCheck.class)
    @NotBlank(message = "Sell date cannot be blank")
    @Pattern(regexp = "^[0-9]{4}-[0-1][0-9]-[0-3][0-9]$", groups = FieldsCheck.class, message = "Wrong sell date format, should be of pattern: YYYY-MM-DD.")
    private String sellDate;

    public String getInvestmentName() {
        return investmentName;
    }

    public void setInvestmentName(String investmentName) {
        this.investmentName = investmentName;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(String buyDate) {
        this.buyDate = buyDate;
    }

    public String getSellDate() {
        return sellDate;
    }

    public void setSellDate(String sellDate) {
        this.sellDate = sellDate;
    }
}
