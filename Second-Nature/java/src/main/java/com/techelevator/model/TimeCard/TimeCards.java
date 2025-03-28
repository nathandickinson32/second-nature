package com.techelevator.model.TimeCard;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class TimeCards {

    private int timeCardId;
    private int userId;
    private Timestamp dateTimeIn;
    private Timestamp dateTimeOut;
    private boolean clockedIn;
    private int totalMinutesWorked;
    private Timestamp clockInTime;
    private Timestamp clockOutTime;
    private Date updatedOnDate;
    private int updatedByUserId;
    private boolean isArchived;
    private String archivedNotes;
    private Date createdOn;

    public TimeCards() {
    }

    public TimeCards(int timeCardId, int userId, Timestamp dateTimeIn, boolean clockedIn, int totalMinutesWorked, Timestamp clockInTime, Timestamp clockOutTime, Date updatedOnDate, int updatedByUserId, boolean isArchived, String archivedNotes, Date createdOn) {
        this.timeCardId = timeCardId;
        this.userId = userId;
        this.dateTimeIn = dateTimeIn;
        this.clockedIn = clockedIn;
        this.totalMinutesWorked = totalMinutesWorked;
        this.clockInTime = clockInTime;
        this.clockOutTime = clockOutTime;
        this.updatedOnDate = updatedOnDate;
        this.updatedByUserId = updatedByUserId;
        this.isArchived = isArchived;
        this.archivedNotes = archivedNotes;
        this.createdOn = createdOn;
    }

    public void setTimeCardId(int timeCardId) {
        this.timeCardId = timeCardId;
    }

    public int getTimeCardId() {
        return timeCardId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public boolean isArchived() {
        return isArchived;
    }

    public void setArchived(boolean archived) {
        isArchived = archived;
    }

    public Timestamp getDateTimeIn() {
        return dateTimeIn;
    }

    public void setDateTimeIn(Timestamp dateTimeIn) {
        this.dateTimeIn = dateTimeIn;
    }

    public void setUpdatedOnDate(Date updatedOnDate) {
        this.updatedOnDate = updatedOnDate;
    }

    public Date getUpdatedOnDate() {
        return updatedOnDate;
    }

    public void setUpdatedByUserId(int updatedByUserId) {
        this.updatedByUserId = updatedByUserId;
    }

    public int getUpdatedByUserId() {
        return updatedByUserId;
    }



    public void setArchivedNotes(String archivedNotes) {
        this.archivedNotes = archivedNotes;
    }

    public String getArchivedNotes() {
        return archivedNotes;
    }

    public Timestamp getDateTimeOut() {
        return dateTimeOut;
    }

    public void setDateTimeOut(Timestamp dateTimeOut) {
        this.dateTimeOut = dateTimeOut;
    }

    public boolean isClockedIn() {
        return clockedIn;
    }

    public void setClockedIn(boolean clockedIn) {
        this.clockedIn = clockedIn;
    }

    public int getTotalMinutesWorked() {
        return totalMinutesWorked;
    }


    public Timestamp getClockInTime() {
        return clockInTime;
    }



    public Timestamp getClockOutTime() {
        return clockOutTime;
    }

    public void setTotalMinutesWorked(int totalMinutesWorked) {
        this.totalMinutesWorked = totalMinutesWorked;
    }

    public void setClockInTime(Timestamp clockInTime) {
        this.clockInTime = clockInTime;
    }

    public void setClockOutTime(Timestamp clockOutTime) {
        this.clockOutTime = clockOutTime;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
}
