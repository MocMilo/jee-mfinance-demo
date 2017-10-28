package com.infoshare.web.analyzer.analysis.model;

import com.infoshare.mfinance.core.models.analyses.criteria.InvestmentRevenueCriteria;
import com.infoshare.web.user.User;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class PersistedInvestmentRevenueCriteria {

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
    private LocalDate testLocalDate;
    private boolean isFavourite;



    @PrePersist
    private void onCreate(){
        creationDateTime=LocalDateTime.now();
    }

    @PreUpdate
    private void onUpdate(){
        lastUpdateDateTime=LocalDateTime.now();
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

    //@Override
    public long getId() {
        return id;
    }
    //@Override
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

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    public boolean isFavourite() {
        return isFavourite;
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

    public LocalDate getTestLocalDate() {
        return testLocalDate;
    }

    public void setTestLocalDate(LocalDate testLocalDate) {
        this.testLocalDate = testLocalDate;
    }



    public PersistedInvestmentRevenueCriteria getCriteria(InvestmentRevenueCriteria criteria, String userCustomName) {
        setInvestedCapital(criteria.getInvestedCapital());
        setBuyDate(criteria.getBuyDate());
        setSellDate(criteria.getSellDate());
        setInvestmentName(criteria.getInvestmentName());
       // setFavourite(criteria.getFavourite());
        setUserCustomName(userCustomName);
        setTestLocalDate(LocalDate.now());
        System.out.println("aktualna data"+LocalDate.now());
        return this;
    }

    public InvestmentRevenueCriteria getEqualEquivalent(PersistedInvestmentRevenueCriteria revenueCriteria){

        InvestmentRevenueCriteria criteria = new InvestmentRevenueCriteria();
        criteria.setInvestedCapital(revenueCriteria.getInvestedCapital());
        criteria.setBuyDate(revenueCriteria.getBuyDate());
        criteria.setSellDate(revenueCriteria.getSellDate());
        criteria.setInvestmentName(revenueCriteria.getInvestmentName());
       // criteria.setFavourite(revenueCriteria.getFavourite());
        return criteria;
    }
}
