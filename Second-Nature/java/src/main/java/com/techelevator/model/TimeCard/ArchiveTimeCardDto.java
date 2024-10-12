package com.techelevator.model.TimeCard;

public class ArchiveTimeCardDto {

    private int timeCardId;
    private boolean isArchived;
    private String archivedNotes;


    public void setTimeCardId(int timeCardId) {
        this.timeCardId = timeCardId;
    }

    public int getTimeCardId() {
        return timeCardId;
    }

    public void setArchivedNotes(String archivedNotes) {
        this.archivedNotes = archivedNotes;
    }

    public String getArchivedNotes() {
        return archivedNotes;
    }

    public void setIsArchived(boolean isArchived) {
        this.isArchived = isArchived;
    }

    public boolean getIsArchived() {
        return isArchived;
    }
}
