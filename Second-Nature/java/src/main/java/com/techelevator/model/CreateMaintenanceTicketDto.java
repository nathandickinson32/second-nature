package com.techelevator.model;

import java.util.List;

public class CreateMaintenanceTicketDto {
    private int equipmentId;
    private int hours;
    private String notes;
    private boolean isComplete;
    private List<CreateMaintenancePerformedDto> createMaintenancePerformedDto;

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

    public List<CreateMaintenancePerformedDto> getCreateMaintenancePerformedDto() {
        return createMaintenancePerformedDto;
    }

    public void setCreateMaintenancePerformedDto(List<CreateMaintenancePerformedDto> createMaintenancePerformedDto) {
        this.createMaintenancePerformedDto = createMaintenancePerformedDto;
    }
}
