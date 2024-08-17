package com.techelevator.model;

import java.util.List;

public class UpdateMaintenanceTicketDto {
    private String notes;
    private boolean isComplete;
    private List<MaintenancePerformed> maintenancePerformedList;

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

    public List<MaintenancePerformed> getMaintenancePerformedList() {
        return maintenancePerformedList;
    }

    public void setMaintenancePerformedList(List<MaintenancePerformed> maintenancePerformedList) {
        this.maintenancePerformedList = maintenancePerformedList;
    }
}
