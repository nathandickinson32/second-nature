package com.techelevator.model;

import javax.validation.constraints.NotEmpty;

public class ArchiveEquipmentDto {
    @NotEmpty
    private int equipmentId;
    @NotEmpty
    private boolean isArchived;

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
}
