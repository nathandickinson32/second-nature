package com.techelevator.model;

import javax.validation.constraints.NotEmpty;

public class ArchiveEquipmentDto {
    @NotEmpty
    private int equipmentId;
    @NotEmpty
    private int updatedByUserId;
    @NotEmpty
    private boolean isArchived;

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public int getUpdatedByUserId() {
        return updatedByUserId;
    }

    public void setUpdatedByUserId(int updatedByUserId) {
        this.updatedByUserId = updatedByUserId;
    }

    public boolean isArchived() {
        return isArchived;
    }

    public void setArchived(boolean archived) {
        isArchived = archived;
    }
}
