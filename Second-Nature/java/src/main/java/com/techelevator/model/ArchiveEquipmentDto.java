package com.techelevator.model;

import javax.validation.constraints.NotEmpty;

public class ArchiveEquipmentDto {
    @NotEmpty
    private int equipmentId;
    @NotEmpty
    private boolean isArchived;
    private String archived_notes;

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

    public String getArchived_notes() {
        return archived_notes;
    }

    public void setArchived_notes(String archived_notes) {
        this.archived_notes = archived_notes;
    }
}
