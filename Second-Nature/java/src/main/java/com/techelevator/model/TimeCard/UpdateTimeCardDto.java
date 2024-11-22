package com.techelevator.model.TimeCard;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class UpdateTimeCardDto {

    private int timeCardId;
    private Timestamp clockInTime;

    public UpdateTimeCardDto(int timeCardId, Timestamp clockInTime) {
        this.timeCardId = timeCardId;
        this.clockInTime = clockInTime;
    }

    public void setTimeCardId(int timeCardId) {
        this.timeCardId = timeCardId;
    }

    public int getTimeCardId() {
        return timeCardId;
    }



    public Timestamp getClockInTime() {
        return clockInTime;
    }

    public void setClockInTime(Timestamp clockInTime) {
        this.clockInTime = clockInTime;
    }


}
