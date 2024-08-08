package com.techelevator.model;

public class MaintenancePerformed {
    private int maintenancePerformedId;
    private int equipmentId;
    private int ticketId;
    private String description;
    private String performedBy;
    private String notes;

    public int getMaintenancePerformedId() {
        return maintenancePerformedId;
    }

    public void setMaintenancePerformedId(int maintenancePerformedId) {
        this.maintenancePerformedId = maintenancePerformedId;
    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPerformedBy() {
        return performedBy;
    }

    public void setPerformedBy(String performedBy) {
        this.performedBy = performedBy;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
