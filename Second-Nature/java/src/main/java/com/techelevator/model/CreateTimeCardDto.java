package com.techelevator.model;

import java.security.Timestamp;

public class CreateTimeCardDto {

    private Integer userId;
    private String hourType;
    private Timestamp dateTime;

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setHourType(String hourType) {
        this.hourType = hourType;
    }

    public String getHourType() {
        return hourType;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

}
