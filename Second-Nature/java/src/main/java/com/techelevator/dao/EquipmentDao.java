package com.techelevator.dao;

import com.techelevator.model.*;

import java.util.List;

public interface EquipmentDao {
    // Create
    public Equipment createEquipment(CreateEquipmentDto createEquipmentDto, int userId);

    // Read
    public List<Equipment> getListOfAllEquipment();
    public Equipment getEquipmentById(int equipmentId);
    public List<EquipmentIdentityDto> getEquipmentIdentityList();

    // Update
    public Equipment updateEquipment(UpdateEquipmentDto updateEquipmentDto, int userId);
    public Equipment updateEquipmentActivity(UpdateEquipmentActivityDto updateEquipmentActivityDto, int userId);

    // Delete
    public void deleteEquipment(int equipmentId);
    public Equipment archiveEquipment(ArchiveEquipmentDto archiveEquipmentDto, int userId);
}
