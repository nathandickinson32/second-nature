package com.techelevator.model;

import java.security.Timestamp;

public class UpdateTimeCardDto {

    private Integer timeCardId;
    private String hourType;
    private Timestamp dateTime;

    public Integer getTimeCardId() {
        return timeCardId;
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
