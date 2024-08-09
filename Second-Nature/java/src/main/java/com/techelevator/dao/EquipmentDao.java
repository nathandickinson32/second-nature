package com.techelevator.dao;

import com.techelevator.model.*;

import java.util.List;

public interface EquipmentDao {
    // Create
    public Equipment createEquipment(CreateEquipmentDto createEquipmentDto);

    // Read
    public List<Equipment> getListOfAllEquipment();
    public Equipment getEquipmentById(int equipmentId);
    public List<EquipmentIdentityDto> getEquipmentIdentityList();

    // Update
    public Equipment updateEquipment(UpdateEquipmentDto updateEquipmentDto);
    public Equipment updateEquipmentActivity(UpdateEquipmentActivityDto updateEquipmentActivityDto);

    // Delete
    public void deleteEquipment(int equipmentId);
    public Equipment archiveEquipment(ArchiveEquipmentDto archiveEquipmentDto);
}
