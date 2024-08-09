package com.techelevator.model;

import javax.validation.constraints.NotEmpty;

public class UpdateEquipmentDto {
    @NotEmpty
    private int equipmentId;
    @NotEmpty
    private String serialNumber;
    @NotEmpty
    private String model;
    private String name;
    private String notes;
    @NotEmpty
    private boolean isActive;
    private String activeNotes;
    @NotEmpty
    private int updatedByUserId;
    private boolean isArchived;

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

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

    public int getUpdatedByUserId() {
        return updatedByUserId;
    }

    public void setUpdatedByUserId(int updatedByUserId) {
        this.updatedByUserId = updatedByUserId;
    }

    public boolean isArchived() {
        return isArchived;
    }

    public void setArchived(boolean archived) {
        isArchived = archived;
    }
}
