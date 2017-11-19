package com.infoshare.web.services.reports.activity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

//import LocalDateTimeDeserializer;
//import LocalDateTimeSerializer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class UserActivity implements Serializable {

    @Id
    @GeneratedValue
    private long Id;
    private String login;
    private String activityName;
    private String sessionId;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime activityDateTime;


    public UserActivity() {
    }

    @PrePersist
    void onCreate(){
        activityDateTime = LocalDateTime.now();
    }

    public UserActivity(String login, String activityName, String sessionId) {
        this.login = login;
        this.activityName = activityName;
        this.sessionId = sessionId;
    }

    public long getId() {
        return Id;
    }

    public String getLogin() {
        return login;
    }

    public String getActivityName() {
        return activityName;
    }

    public String getSessionId() {
        return sessionId;
    }

    public LocalDateTime getActivityDateTime() {
        return activityDateTime;
    }

    public void setId(long id) {
        Id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public void setActivityDateTime(LocalDateTime activityDateTime) {
        this.activityDateTime = activityDateTime;
    }
}
