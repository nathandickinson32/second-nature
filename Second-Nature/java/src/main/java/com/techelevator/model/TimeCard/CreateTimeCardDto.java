package com.techelevator.model.TimeCard;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CreateTimeCardDto {

    private Integer userId;
    private Timestamp dateTime;
    private Timestamp roundedDateTime;

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }


    public void setDateTime(String dateTimeStr) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime localDateTime = LocalDateTime.parse(dateTimeStr, formatter);
            this.dateTime = Timestamp.valueOf(localDateTime);


    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public Timestamp getRoundedDateTime() {
        return roundedDateTime;
    }

    public void setRoundedDateTime(Timestamp roundedDateTime) {
        this.roundedDateTime = roundedDateTime;
    }
}
