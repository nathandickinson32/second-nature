package com.techelevator.model.Maintenance;

import java.util.List;

public class UpdateMaintenanceTicketDto {
    private int ticketId;
    private String notes;
    private boolean isComplete;
    private List<CreateMaintenancePerformedDto> createMaintenancePerformedDto;

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
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
