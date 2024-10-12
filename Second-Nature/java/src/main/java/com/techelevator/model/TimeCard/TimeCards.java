package com.techelevator.model.TimeCard;

import java.sql.Date;
import java.sql.Timestamp;

public class TimeCards {

    private int timeCardId;
    private int userId;
    private String hourType;
    private Timestamp dateTime;
    private Date updatedOnDate;
    private int updatedByUserId;
    private boolean isArchived;
    private String archivedNotes;

    public TimeCards() {}

    public TimeCards(int timeCardId, int userId, String hourType, Timestamp dateTime, Date updatedOnDate, int updatedByUserId,
                     boolean isArchived, String archivedNotes) {
        this.timeCardId = timeCardId;
        this.userId = userId;
        this.hourType = hourType;
        this.dateTime = dateTime;
        this.updatedOnDate = updatedOnDate;
        this.updatedByUserId = updatedByUserId;
        this. isArchived = isArchived;
        this.archivedNotes = archivedNotes;
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

    public void setIsArchived(boolean isArchived) {
        this.isArchived = isArchived;
    }

    public boolean getIsArchived() {
        return isArchived;
    }

    public void setArchivedNotes(String archivedNotes) {
        this.archivedNotes = archivedNotes;
    }

    public String getArchivedNotes() {
        return archivedNotes;
    }

}
