package com.techelevator.model.EquipmentFolder;

import javax.validation.constraints.NotEmpty;

public class UpdateEquipmentActivityDto {
    @NotEmpty
    private int equipmentId;
    @NotEmpty
    private boolean isActive;
    private String activeNotes;

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
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
