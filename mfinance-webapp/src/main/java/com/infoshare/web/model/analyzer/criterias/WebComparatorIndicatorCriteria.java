package com.infoshare.web.model.analyzer.criterias;

import com.infoshare.web.model.user.User;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class WebComparatorIndicatorCriteria {

    @Id
    @GeneratedValue
    private long Id;
    private boolean isFavouriteChecked;
    private String  userCustomName;


    @ManyToOne
    private User user;

    @ElementCollection
    private Set<String> investmentNamesToCompare = new HashSet<>();

    public String getUserCustomName() {
        return userCustomName;
    }

    public void setUserCustomName(String userCustomName) {
        this.userCustomName = userCustomName;
    }

    public boolean isFavouriteChecked() {
        return isFavouriteChecked;
    }

    public void setFavouriteChecked(boolean favouriteChecked) {
        isFavouriteChecked = favouriteChecked;
    }

    public Set<String> getInvestmentNamesToCompare() {
        return investmentNamesToCompare;
    }

    public void setInvestmentNamesToCompare(Set<String> investmentNamesToCompare) {
        this.investmentNamesToCompare = investmentNamesToCompare;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

}
