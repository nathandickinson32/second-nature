package com.techelevator.model;

public class CompleteMaintenanceTicketDto {
    private int ticketId;
    private boolean isComplete;

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }
}
