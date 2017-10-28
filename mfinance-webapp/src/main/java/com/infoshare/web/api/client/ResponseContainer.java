package com.infoshare.web.api.client;

import com.infoshare.web.user.report.UserActivity;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ResponseContainer {

    @JsonProperty("userActivities")
    private List<UserActivity> userActivities;

    public List<UserActivity> getUserActivities() {
        return userActivities;
    }

    public void setUserActivities(List<UserActivity> userActivities) {
        this.userActivities = userActivities;
    }
}