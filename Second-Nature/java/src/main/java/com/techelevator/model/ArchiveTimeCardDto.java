package com.techelevator.model;

public class ArchiveTimeCardDto {

    private Integer timeCardId;
    private String archivedNotes;
    private boolean isArchived;

    public void setTimeCardId(Integer timeCardId) {
        this.timeCardId = timeCardId;
    }

    public Integer getTimeCardId() {
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
