package com.infoshare.web.analyzer.analysis.comparison;

import com.infoshare.web.analyzer.analysis.model.PersistedIndicatorCriteria;

import com.infoshare.web.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class AnalysisComparisonContainer {

    @Id
    @GeneratedValue
    private long Id;

    @ManyToOne
    private User user;

    @OneToMany (fetch=FetchType.EAGER, cascade = {CascadeType.ALL})
    private List<PersistedIndicatorCriteria> criteriaList = new ArrayList<>();

    private boolean isFavouriteChecked;
    private String  userCustomName;

    public List<PersistedIndicatorCriteria> getCriteriaList() {
        return criteriaList;
    }

    public void setCriteriaList(List<PersistedIndicatorCriteria> criteriaList) {
        this.criteriaList = criteriaList;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public boolean isFavouriteChecked() {
        return isFavouriteChecked;
    }

    public void setFavouriteChecked(boolean favouriteChecked) {
        isFavouriteChecked = favouriteChecked;
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

    public AnalysisComparisonContainer() {
    }

    public AnalysisComparisonContainer(boolean isFavouriteChecked, String userCustomName, List<PersistedIndicatorCriteria> criteriaList) {
        this.isFavouriteChecked = isFavouriteChecked;
        this.userCustomName = userCustomName;
        this.criteriaList = criteriaList;
    }
}
