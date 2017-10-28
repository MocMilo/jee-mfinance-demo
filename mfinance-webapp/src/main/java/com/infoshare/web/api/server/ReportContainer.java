package com.infoshare.web.api.server;

import com.infoshare.web.user.report.UserActivity;

import java.util.List;

public class ReportContainer {

    private List<UserActivity> userActivities;

    public List<UserActivity> getUserActivities() {
        return userActivities;
    }

    public void setUserActivities(List<UserActivity> userActivities) {
        this.userActivities = userActivities;
    }
}
