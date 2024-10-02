package com.techelevator.model;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    public void setDateTime(String dateTimeStr) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime localDateTime = LocalDateTime.parse(dateTimeStr, formatter);
            this.dateTime = Timestamp.valueOf(localDateTime);


    }

    public Timestamp getDateTime() {
        return dateTime;
    }

}
