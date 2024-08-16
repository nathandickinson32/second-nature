package com.techelevator.model;

import java.util.Date;

public class MaintenanceTicket {
    private int ticketId;
    private int equipmentId;
    private int hours;
    private int enteredByUserId;
    private Date date;
    private String notes;
    private boolean isComplete;
    private int updatedByUserId;
    private Date updatedOnDate;
    private boolean isArchived;

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getEnteredByUserId() {
        return enteredByUserId;
    }

    public void setEnteredByUserId(int enteredByUserId) {
        this.enteredByUserId = enteredByUserId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
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

    public boolean isArchived() {
        return isArchived;
    }

    public void setArchived(boolean archived) {
        isArchived = archived;
    }
}
