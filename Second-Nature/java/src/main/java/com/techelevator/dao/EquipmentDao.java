package com.techelevator.dao;

import com.techelevator.model.CreateEquipmentDto;
import com.techelevator.model.Equipment;
import com.techelevator.model.EquipmentActivityDto;

import java.util.List;

public interface EquipmentDao {
    // Create
    public Equipment createEquipment(CreateEquipmentDto createEquipmentDto);

    // Read
    public List<Equipment> getListOfAllEquipment();
    public Equipment getEquipmentById(int equipmentId);

    // Update
    public Equipment updateEquipment(Equipment equipment);
    public Equipment deactivateEquipment(EquipmentActivityDto equipmentActivityDto);
    public Equipment activeEquipment(EquipmentActivityDto equipmentActivityDto);

    // Delete
    public void deleteEquipment(int equipmentId);
    public void archiveEquipment(int equipmentId);
}
