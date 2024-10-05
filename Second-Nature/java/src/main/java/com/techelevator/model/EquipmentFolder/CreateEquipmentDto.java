package com.techelevator.model.EquipmentFolder;

import javax.validation.constraints.NotEmpty;

public class CreateEquipmentDto {
    @NotEmpty(message = "Please enter a serial number.")
    private String serialNumber;
    @NotEmpty(message = "Please enter a model.")
    private String model;
    private String name;
    private int typeId;
    @NotEmpty(message = "Please enter the machines starting hours.")
    private int startingHours;
    private String notes;
    @NotEmpty
    private boolean isActive = true;
    private String activeNotes;
    @NotEmpty
    private boolean isArchived = false;

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

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getStartingHours() {
        return startingHours;
    }

    public void setStartingHours(int startingHours) {
        this.startingHours = startingHours;
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

    @NotEmpty
    public boolean isArchived() {
        return isArchived;
    }

    public void setArchived(@NotEmpty boolean archived) {
        isArchived = archived;
    }
}
