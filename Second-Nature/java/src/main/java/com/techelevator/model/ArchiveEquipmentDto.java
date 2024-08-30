package com.techelevator.model;

import javax.validation.constraints.NotEmpty;

public class ArchiveEquipmentDto {
    @NotEmpty
    private int equipmentId;
    @NotEmpty
    private boolean isArchived;
    private String archivedNotes;

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public boolean isArchived() {
        return isArchived;
    }

    public void setArchived(boolean archived) {
        isArchived = archived;
    }

    public String getArchivedNotes() {
        return archivedNotes;
    }

    public void setArchivedNotes(String archivedNotes) {
        this.archivedNotes = archivedNotes;
    }
}
