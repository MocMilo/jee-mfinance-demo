package com.infoshareacademy.web.model.analyzer.criterias;

import com.infoshareacademy.mfinance.core.utils.BigDecimalUtil;
import com.infoshareacademy.mfinance.core.utils.LocalDateUtil;
import com.infoshareacademy.web.model.user.User;
import com.infoshareacademy.web.model.validation.forms.IVRCriteriaForm;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class WebInvestmentRevenueCriteria extends WebAnalysisCriteria {
    private static final String STRATEGY = "IVR";
    @Id
    @GeneratedValue
    private long id;
    private String investmentName;
    private BigDecimal investedCapital;
    private LocalDate buyDate;
    private LocalDate sellDate;
    @ManyToOne
    private User user;
    private String userCustomName;
    private LocalDateTime creationDateTime;
    private LocalDateTime lastUpdateDateTime;
    private boolean isFavourite;

    public WebInvestmentRevenueCriteria(IVRCriteriaForm form, String userCustomName, boolean isFavourite) {
        this.strategy = STRATEGY;
        this.investmentName = form.getInvestmentName();
        this.investedCapital = BigDecimalUtil.parseFormMoney(form.getCapital());
        this.buyDate = LocalDateUtil.parseForm(form.getBuyDate());
        this.sellDate = LocalDateUtil.parseForm(form.getSellDate());
        this.userCustomName = userCustomName;
        this.isFavourite = isFavourite;
    }

    @PrePersist
    private void onCreate() {
        creationDateTime = LocalDateTime.now();
    }

    @PreUpdate
    private void onUpdate() {
        lastUpdateDateTime = LocalDateTime.now();
    }

    public String getInvestmentName() {
        return investmentName;
    }

    public void setInvestmentName(String investmentName) {
        this.investmentName = investmentName;
    }

    public BigDecimal getInvestedCapital() {
        return investedCapital;
    }

    public void setInvestedCapital(BigDecimal investedCapital) {
        this.investedCapital = investedCapital;
    }

    public LocalDate getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(LocalDate buyDate) {
        this.buyDate = buyDate;
    }

    public LocalDate getSellDate() {
        return sellDate;
    }

    public void setSellDate(LocalDate sellDate) {
        this.sellDate = sellDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserCustomName() {
        return userCustomName;
    }

    public void setUserCustomName(String userCustomName) {
        this.userCustomName = userCustomName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public LocalDateTime getLastUpdateDateTime() {
        return lastUpdateDateTime;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public WebInvestmentRevenueCriteria() {
        this.strategy = STRATEGY;
    }


}
