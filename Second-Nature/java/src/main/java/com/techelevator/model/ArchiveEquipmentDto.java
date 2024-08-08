package com.techelevator.model;

public class ArchiveEquipmentDto {
    private int equipmentId;
    private int updatedByUserId;
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
