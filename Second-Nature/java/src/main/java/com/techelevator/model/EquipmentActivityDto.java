package com.techelevator.model;

public class EquipmentActivityDto {
    private boolean isActive;
    private String activeNotes;

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
