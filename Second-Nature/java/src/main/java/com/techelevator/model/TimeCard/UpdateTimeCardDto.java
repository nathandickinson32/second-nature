package com.techelevator.model.TimeCard;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UpdateTimeCardDto {

    private Integer timeCardId;
    private Timestamp dateTime;
    private Date updatedOnDate;
    private int updatedByUserId;

    public void setTimeCardId(Integer timeCardId) {
        this.timeCardId = timeCardId;
    }

    public Integer getTimeCardId() {
        return timeCardId;
    }


    public void setDateTime(String dateTimeStr) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.parse(dateTimeStr, formatter);
        this.dateTime = java.sql.Timestamp.valueOf(localDateTime);


    }
    public Timestamp getDateTime() {
        return dateTime;
    }


    public int getUpdatedByUserId() {
        return updatedByUserId;
    }

    public void setUpdatedByUserId(int updatedByUserId) {
        this.updatedByUserId = updatedByUserId;
    }

    public Date getUpdatedOnDate() {
        return updatedOnDate;
    }

    public void setUpdatedOnDate(Date updatedOnDate) {
        this.updatedOnDate = updatedOnDate;
    }
}
