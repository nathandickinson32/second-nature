package com.techelevator.model;

import java.util.Date;

public class MaintenanceTicket {
    private int ticketId;
    private int equipmentId;
    private int hours;
    private String enteredByUserId;
    private Date date;
    private String notes;
    private boolean isComplete;

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

    public String getEnteredByUserId() {
        return enteredByUserId;
    }

    public void setEnteredByUserId(String enteredByUserId) {
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
}
