package com.techelevator.model.Maintenance;

import java.util.Date;

public class MaintenancePerformed {
    private int maintenancePerformedId;
    private int equipmentId;
    private int ticketId;
    private String description;
    private String performedBy;
    private Date performedOnDate;
    private String notes;
    private int hours;

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

    public Date getPerformedOnDate() {
        return performedOnDate;
    }

    public void setPerformedOnDate(Date performedOnDate) {
        this.performedOnDate = performedOnDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
}
