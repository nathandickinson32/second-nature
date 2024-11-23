package com.techelevator.model.TimeCard;

import java.sql.Date;
import java.sql.Timestamp;

public class ManagerUpdateCardDto {
    private int timeCardId;
    private Timestamp dateTimeIn;
    private Timestamp dateTimeOut;


    public int getTimeCardId() {
        return timeCardId;
    }

    public void setTimeCardId(int timeCardId) {
        this.timeCardId = timeCardId;
    }

    public Timestamp getDateTimeIn() {
        return dateTimeIn;
    }

    public void setDateTimeIn(Timestamp dateTimeIn) {
        this.dateTimeIn = dateTimeIn;
    }

    public Timestamp getDateTimeOut() {
        return dateTimeOut;
    }

    public void setDateTimeOut(Timestamp dateTimeOut) {
        this.dateTimeOut = dateTimeOut;
    }


}
