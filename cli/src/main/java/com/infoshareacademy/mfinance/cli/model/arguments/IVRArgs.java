package com.infoshareacademy.mfinance.cli.model.arguments;

import com.infoshareacademy.mfinance.cli.services.validators.FieldsCheck;
import com.infoshareacademy.mfinance.cli.services.validators.classlevel.ValidIVRArgs;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;


@ValidIVRArgs
@GroupSequence({FieldsCheck.class, IVRArgs.class})
public class IVRArgs extends ApplicationArguments implements Serializable {

    @NotNull(message = "Investment capital cannot be empty")
    @NotBlank(message = "Investment capital cannot be blank")
    @Pattern(regexp = "^(0|0?[1-9]\\d*)\\.\\d\\d$", message = "Wrong Investment capital argument: should be decimal of format: 1.00, > 0.00")
    private String capital;

    @NotNull(message = "Wrong investment name, should not be empty")
    @NotBlank(message = "Investment name cannot be blank")
    @Size(min = 3, max = 8, message = "Name needs to be at least 3 characters long but not longer than 8")
    private String investmentName;

    @NotNull(message = "Buy date cannot be empty", groups = FieldsCheck.class)
    @NotBlank(message = "Buy date cannot be blank")
    @Pattern(regexp = "^[0-9]{4}-[0-1][0-9]-[0-3][0-9]$", groups = FieldsCheck.class, message = "Wrong buy date format, should be of pattern: YYYY-MM-DD.")
    private String startDate;

    @NotNull(message = "Sell date cannot be empty", groups = FieldsCheck.class)
    @NotBlank(message = "Sell date cannot be blank")
    @Pattern(regexp = "^[0-9]{4}-[0-1][0-9]-[0-3][0-9]$", groups = FieldsCheck.class, message = "Wrong sell date format, should be of pattern: YYYY-MM-DD.")
    private String endDate;

    public IVRArgs() {
    }

    public IVRArgs(String[] args) {
        this.setStrategy(args[0]);
        this.investmentName = args[1];
        this.capital = args[2];
        this.startDate = args[3];
        this.endDate = args[4];
    }

    public String getCapital() {
        return capital;
    }

    public String getInvestmentName() {
        return investmentName;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public void setInvestmentName(String investmentName) {
        this.investmentName = investmentName;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
