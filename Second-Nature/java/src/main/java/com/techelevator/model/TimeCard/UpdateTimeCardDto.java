package com.techelevator.model.TimeCard;

import java.sql.Timestamp;


public class UpdateTimeCardDto {

    private Integer timeCardId;
    private Timestamp clockInTime;


    public void setTimeCardId(Integer timeCardId) {
        this.timeCardId = timeCardId;
    }

    public Integer getTimeCardId() {
        return timeCardId;
    }



    public Timestamp getClockInTime() {
        return clockInTime;
    }

    public void setClockInTime(Timestamp clockInTime) {
        this.clockInTime = clockInTime;
    }


}
