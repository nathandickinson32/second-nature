package com.techelevator.dao;

import com.techelevator.model.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class JdbcEquipmentDao implements EquipmentDao {

    private JdbcTemplate template;

    public JdbcEquipmentDao(DataSource ds) {
        template = new JdbcTemplate(ds);
    }

    @Override
    public Equipment createEquipment(CreateEquipmentDto createEquipmentDto, int userId) {
        int equipmentId = -1;
        String sql = "INSERT INTO equipment (serial_number, model, name, type_id, starting_hours, entered_by_user_id, entered_on_date, notes, is_active, active_notes, is_archived) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING equipment_id;";

        try {
            equipmentId = template.queryForObject(
                    sql,
                    int.class,
                    createEquipmentDto.getSerialNumber(),
                    createEquipmentDto.getModel(),
                    createEquipmentDto.getName(),
                    createEquipmentDto.getTypeId(),
                    createEquipmentDto.getStartingHours(),
                    userId,
                    new Date(),
                    createEquipmentDto.getNotes(),
                    createEquipmentDto.isActive(),
                    createEquipmentDto.getActiveNotes(),
                    createEquipmentDto.isArchived()
            );
        } catch(CannotGetJdbcConnectionException e) {
            throw new CannotGetJdbcConnectionException("[JDBC Equipment DAO] Problem connecting to the database.");
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("[JDBC Equipment DAO] Error creating new equipment.");
        }

        return getEquipmentById(equipmentId);
    }

    @Override
    public List<Equipment> getListOfAllEquipment() {
        List<Equipment> equipmentList = new ArrayList<>();
        String sql = "SELECT * FROM equipment;";

        try {
            SqlRowSet results = template.queryForRowSet(sql);
            while (results.next()){
                equipmentList.add(mapRowToEquipment(results));
            }
        } catch(CannotGetJdbcConnectionException e) {
            throw new CannotGetJdbcConnectionException("[JDBC Equipment DAO] Problem connecting to the database.");
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("[JDBC Equipment DAO] Cannot get a list of all equipment");
        }

        return equipmentList;
    }

    @Override
    public Equipment getEquipmentById(int equipmentId) {
        Equipment equipment = new Equipment();
        String sql = "SELECT * FROM equipment WHERE equipment_id = ?;";

        try {
            SqlRowSet results = template.queryForRowSet(sql, equipmentId);
            if (results.next()){
                equipment = mapRowToEquipment(results);
            }
        } catch(CannotGetJdbcConnectionException e) {
            throw new CannotGetJdbcConnectionException("[JDBC Equipment DAO] Problem connecting to the database.");
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("[JDBC Equipment DAO] Cannot get equipment with ID: " + equipmentId);
        }

        return equipment;
    }

    @Override
    public List<EquipmentIdentityDto> getEquipmentIdentityList() {
        List<EquipmentIdentityDto> equipmentIdentityDtoList = new ArrayList<>();
        String sql = "SELECT equipment_id, serial_number, model, name FROM equipment;";

        try {
            SqlRowSet results = template.queryForRowSet(sql);
            while (results.next()){
                EquipmentIdentityDto equipmentIdentityDto = new EquipmentIdentityDto();
                equipmentIdentityDto.setEquipmentId(results.getInt("equipment_id"));
                equipmentIdentityDto.setSerialNumber(results.getString("serial_number"));
                equipmentIdentityDto.setModel(results.getString("model"));
                equipmentIdentityDto.setName(results.getString("name"));
                equipmentIdentityDtoList.add(equipmentIdentityDto);
            }
        } catch(CannotGetJdbcConnectionException e) {
            throw new CannotGetJdbcConnectionException("[JDBC Equipment DAO] Problem connecting to the database.");
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("[JDBC Equipment DAO] Cannot get a list of equipment identities.");
        }

        return equipmentIdentityDtoList;
    }

    @Override
    public Equipment updateEquipment(UpdateEquipmentDto updateEquipmentDto, int userId) {
        String sql = "UPDATE equipment SET serial_number = ?, model = ?, name = ?, type_id = ?, notes = ?, is_active = ?, active_notes = ?, updated_by_user_id = ?, updated_on_date = ?, is_archived = ? WHERE equipment_id = ?;";

        try {
            template.update(
                    sql,
                    updateEquipmentDto.getSerialNumber(),
                    updateEquipmentDto.getModel(),
                    updateEquipmentDto.getName(),
                    updateEquipmentDto.getTypeId(),
                    updateEquipmentDto.getNotes(),
                    updateEquipmentDto.isActive(),
                    updateEquipmentDto.getActiveNotes(),
                    userId,
                    new Date(),
                    updateEquipmentDto.isArchived(),
                    updateEquipmentDto.getEquipmentId()
            );
        } catch(CannotGetJdbcConnectionException e) {
            throw new CannotGetJdbcConnectionException("[JDBC Equipment DAO] Problem connecting to the database.");
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("[JDBC Equipment DAO] Error updating equipment ID: " + updateEquipmentDto.getEquipmentId());
        }

        return getEquipmentById(updateEquipmentDto.getEquipmentId());
    }

    @Override
    public Equipment updateEquipmentActivity(UpdateEquipmentActivityDto updateEquipmentActivityDto, int userId) {
        String sql = "UPDATE equipment SET is_active = ?, active_notes = ?, updated_by_user_id = ?, updated_on_date = ? WHERE equipment_id = ?;";

        try {
            template.update(
                    sql,
                    updateEquipmentActivityDto.isActive(),
                    updateEquipmentActivityDto.getActiveNotes(),
                    userId,
                    new Date(),
                    updateEquipmentActivityDto.getEquipmentId()
            );
        } catch(CannotGetJdbcConnectionException e) {
            throw new CannotGetJdbcConnectionException("[JDBC Equipment DAO] Problem connecting to the database.");
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("[JDBC Equipment DAO] Error updating equipment ID: " + updateEquipmentActivityDto.getEquipmentId());
        }

        return getEquipmentById(updateEquipmentActivityDto.getEquipmentId());
    }

    @Override
    public void deleteEquipment(int equipmentId) {
        String sql = "DELETE FROM equipment WHERE equipment_id = ?;";

        try {
            template.update(sql, equipmentId);
        } catch(CannotGetJdbcConnectionException e) {
            throw new CannotGetJdbcConnectionException("[JDBC Equipment DAO] Problem connecting to the database.");
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("[JDBC Equipment DAO] Error deleting equipment ID: " + equipmentId);
        }
    }

    @Override
    public Equipment archiveEquipment(ArchiveEquipmentDto archiveEquipmentDto, int userId) {
        String sql = "UPDATE equipment SET updated_by_user_id = ?, updated_on_date = ?, is_archived = ?, archived_notes = ? WHERE equipment_id = ?;";

        try {
            template.update(
                    sql,
                    userId,
                    new Date(),
                    archiveEquipmentDto.isArchived(),
                    archiveEquipmentDto.getArchivedNotes(),
                    archiveEquipmentDto.getEquipmentId()
            );
        } catch(CannotGetJdbcConnectionException e) {
            throw new CannotGetJdbcConnectionException("[JDBC Equipment DAO] Problem connecting to the database.");
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("[JDBC Equipment DAO] Error archiving equipment ID: " + archiveEquipmentDto.getEquipmentId());
        }

        return getEquipmentById(archiveEquipmentDto.getEquipmentId());
    }

    private Equipment mapRowToEquipment(SqlRowSet results) {
        Equipment equipment = new Equipment();
        equipment.setEquipmentId(results.getInt("equipment_id"));
        equipment.setSerialNumber(results.getString("serial_number"));
        equipment.setModel(results.getString("model"));
        equipment.setName(results.getString("name"));
        equipment.setTypeId(results.getInt("type_id"));
        equipment.setStartingHours(results.getInt("starting_hours"));
        equipment.setEnteredByUserId(results.getInt("entered_by_user_id"));
        equipment.setEnteredOnDate(results.getDate("entered_on_date"));
        equipment.setNotes(results.getString("notes"));
        equipment.setActive(results.getBoolean("is_active"));
        equipment.setActiveNotes(results.getString("active_notes"));
        equipment.setUpdatedByUserId(results.getInt("updated_by_user_id"));
        equipment.setUpdatedOnDate(results.getDate("updated_on_date"));
        equipment.setArchived(results.getBoolean("is_archived"));
        equipment.setArchivedNotes(results.getString("archived_notes"));
        return equipment;
    }
}
