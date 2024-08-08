package com.techelevator.model;

import java.util.Date;

public class CreateEquipmentDto {
    private String serialNumber;
    private String model;
    private String name;
    private int startingHours;
    private int enteredByUserId;
    private Date date;
    private String notes;
    private boolean isActive;
    private String activeNotes;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStartingHours() {
        return startingHours;
    }

    public void setStartingHours(int startingHours) {
        this.startingHours = startingHours;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getActiveNotes() {
        return activeNotes;
    }

    public void setActiveNotes(String activeNotes) {
        this.activeNotes = activeNotes;
    }
}
