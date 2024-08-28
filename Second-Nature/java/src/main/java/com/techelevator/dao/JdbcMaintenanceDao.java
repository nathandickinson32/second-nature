package com.techelevator.dao;

import com.sun.tools.javac.Main;
import com.techelevator.model.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class JdbcMaintenanceDao implements MaintenanceDao {

    private JdbcTemplate template;

    public JdbcMaintenanceDao(DataSource ds){
        template = new JdbcTemplate(ds);
    }

    // Create
    @Override
    public MaintenanceTicket createMaintenanceTicket(CreateMaintenanceTicketDto createMaintenanceTicketDTO, int userId) {

        String sql = "INSERT INTO maintenance_tickets (equipment_id, hours, entered_by_user_id, date, notes, is_complete, is_archived) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING ticket_id;";

        int ticketId = -1;

        try {
            ticketId = template.queryForObject(
                    sql,
                    int.class,
                    createMaintenanceTicketDTO.getEquipmentId(),
                    createMaintenanceTicketDTO.getHours(),
                    userId,
                    LocalDateTime.now(),
                    createMaintenanceTicketDTO.getNotes(),
                    false,
                    false
            );
        } catch (CannotGetJdbcConnectionException e){
            throw new CannotGetJdbcConnectionException("[JDBC Maintenance DAO] Unable to connect to the database.");
        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("[JDBC Maintenance DAO] Unable to create a new maintenance ticket.");
        }

        for (CreateMaintenancePerformedDto createMaintenancePerformedDto : createMaintenanceTicketDTO.getCreateMaintenancePerformedDto()){
            createMaintenancePerformed(
                    createMaintenancePerformedDto,
                    ticketId,
                    createMaintenanceTicketDTO.getEquipmentId(),
                    createMaintenanceTicketDTO.getHours()
            );
        }

        return getMaintenanceTicketById(ticketId);
    }

    private MaintenancePerformed createMaintenancePerformed(CreateMaintenancePerformedDto createMaintenancePerformedDto, int ticketId, int equipmentId, int hours){
        int maintenancePerformedId = -1;

        String sql = "INSERT INTO maintenance_performed (equipment_id, ticket_id, description, performed_by, performed_on_date, notes, hours) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING maintenance_performed_id;";

        try {
            maintenancePerformedId = template.queryForObject(
                    sql,
                    int.class,
                    equipmentId,
                    ticketId,
                    createMaintenancePerformedDto.getDescription(),
                    createMaintenancePerformedDto.getPerformedBy(),
                    LocalDateTime.now(), // Performed on date
                    createMaintenancePerformedDto.getNotes(),
                    hours // Current machine hours
            );
        } catch (CannotGetJdbcConnectionException e){
            throw new CannotGetJdbcConnectionException("[JDBC Maintenance DAO] Unable to connect to the database.");
        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("[JDBC Maintenance DAO] Unable to create a new maintenance performed for ticket ID: " + ticketId);
        }

        return getMaintenancePerformed(maintenancePerformedId);
    }

    // Read
    @Override
    public List<MaintenanceTicket> getAllMaintenanceTickets() {
        List<MaintenanceTicket> maintenanceTickets = new ArrayList<>();
        String sql = "SELECT * FROM maintenance_tickets;";

        try {
            SqlRowSet results = template.queryForRowSet(sql);
            while (results.next()) {
                maintenanceTickets.add(mapRowToMaintenanceTicket(results));
            }
        } catch(CannotGetJdbcConnectionException e) {
            throw new CannotGetJdbcConnectionException("[JDBC Maintenance DAO] Problem connecting to the database.");
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("[JDBC Maintenance DAO] Error getting all maintenance tickets.");
        }

        return maintenanceTickets;
    }

    @Override
    public MaintenanceTicket getMaintenanceTicketById(int id) {
        MaintenanceTicket ticket = null;
        String sql = "SELECT * FROM maintenance_tickets WHERE ticket_id = ?;";

        try {
            SqlRowSet results = template.queryForRowSet(sql, id);
            if (results.next()) {
                ticket = mapRowToMaintenanceTicket(results);
            }
            //ticket.setMaintenancePerformedList(getMaintenancePerformedByTicket(id));
        } catch (CannotGetJdbcConnectionException e){
            throw new CannotGetJdbcConnectionException("[JDBC MaintenanceTicket DAO] Unable to connect to the database.");
        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("[JDBC MaintenanceTicket DAO] Unable to get ticket: " + id);
        }

        return ticket;
    }

    private List<MaintenancePerformed> getMaintenancePerformedByTicket(int id) {
        List<MaintenancePerformed> performed = new ArrayList<>();
        String sql = "SELECT * FROM maintenance_performed WHERE ticket_id = ?;";

        try {
            SqlRowSet results = template.queryForRowSet(sql, id);
            while (results.next()) {
                performed.add(mapRowToMaintenancePerformed(results));
            }
        } catch(CannotGetJdbcConnectionException e) {
            throw new CannotGetJdbcConnectionException("[JDBC Maintenance DAO] Problem connecting to the database.");
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("[JDBC Maintenance DAO] Error getting maintenance performed for ticket " + id);
        }

        return performed;
    }

    private MaintenancePerformed getMaintenancePerformed(int maintenancePerformedId){
        MaintenancePerformed maintenancePerformed = null;
        String sql = "SELECT * FROM maintenance_performed WHERE maintenance_performed_id = ?;";

        try {
            SqlRowSet results = template.queryForRowSet(sql, maintenancePerformedId);
            if(results.next()){
                maintenancePerformed = mapRowToMaintenancePerformed(results);
            }
        } catch(CannotGetJdbcConnectionException e) {
            throw new CannotGetJdbcConnectionException("[JDBC Maintenance DAO] Problem connecting to the database.");
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("[JDBC Maintenance DAO] Error getting maintenance performed id: " + maintenancePerformedId);
        }

        return maintenancePerformed;
    }

    private int getEquipmentIdForMaintenanceTicketId(int ticketId){
        int equipmentId = -1;
        String sql = "SELECT equipment_id FROM maintenance_tickets WHERE ticket_id = ?;";

        try {
            SqlRowSet results = template.queryForRowSet(sql, ticketId);
            if (results.next()){
                equipmentId = results.getInt("equipment_id");
            }
        } catch(CannotGetJdbcConnectionException e) {
            throw new CannotGetJdbcConnectionException("[JDBC Maintenance DAO] Problem connecting to the database.");
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("[JDBC Maintenance DAO] Error getting equipment hours for maintenance ticket id: " + ticketId);
        }

        return equipmentId;
    }

    private int getEquipmentHoursForMaintenanceTicketId(int ticketId){
        int hours = -1;
        String sql = "SELECT hours FROM maintenance_tickets WHERE ticket_id = ?;";

        try {
            SqlRowSet results = template.queryForRowSet(sql, ticketId);
            if (results.next()){
                hours = results.getInt("hours");
            }
        } catch(CannotGetJdbcConnectionException e) {
            throw new CannotGetJdbcConnectionException("[JDBC Maintenance DAO] Problem connecting to the database.");
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("[JDBC Maintenance DAO] Error getting equipment hours for maintenance ticket id: " + ticketId);
        }

        return hours;
    }

    @Override
    public List<MaintenancePerformed> getMaintenancePerformedByEquipmentId(int equipmentId){
        List<MaintenancePerformed> maintenancePerformed = new ArrayList<>();
        String sql = "SELECT * FROM maintenance_performed WHERE equipment_id = ? ORDER BY maintenance_performed_id DESC LIMIT 10;";

        try {
            SqlRowSet results = template.queryForRowSet(sql, equipmentId);
            while (results.next()){
                maintenancePerformed.add(mapRowToMaintenancePerformed(results));
            }
        } catch(CannotGetJdbcConnectionException e) {
            throw new CannotGetJdbcConnectionException("[JDBC Equipment DAO] Problem connecting to the database.");
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("[JDBC Equipment DAO] Cannot get a list of maintenance performed by equipment ID: " + equipmentId);
        }

        return maintenancePerformed;
    }

    // Update
    @Override
    public MaintenanceTicket updateMaintenanceTicket(UpdateMaintenanceTicketDto updateMaintenanceTicketDto, int userId){
        String sql = "UPDATE maintenance_tickets SET notes = ?, is_complete = ?, updated_by_user_id = ?, updated_on_date = ? WHERE ticket_id = ?;";

        try {
            template.update(
                    sql,
                    updateMaintenanceTicketDto.getNotes(),
                    updateMaintenanceTicketDto.isComplete(),
                    userId,
                    new Date(),
                    updateMaintenanceTicketDto.getTicketId()
            );

            for (CreateMaintenancePerformedDto createMaintenancePerformedDto : updateMaintenanceTicketDto.getCreateMaintenancePerformedDto()){
                createMaintenancePerformed(
                        createMaintenancePerformedDto,
                        updateMaintenanceTicketDto.getTicketId(),
                        getEquipmentIdForMaintenanceTicketId(updateMaintenanceTicketDto.getTicketId()), // Get the equipment ID.
                        getEquipmentHoursForMaintenanceTicketId(updateMaintenanceTicketDto.getTicketId()) // Get equipment hours from the maintenance ticket.
                );
            }
        } catch(CannotGetJdbcConnectionException e) {
            throw new CannotGetJdbcConnectionException("[JDBC Maintenance DAO] Problem connecting to the database.");
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("[JDBC Maintenance DAO] Error updating maintenance ticket ID: " + updateMaintenanceTicketDto.getTicketId());
        }

        return getMaintenanceTicketById(updateMaintenanceTicketDto.getTicketId());
    }

    @Override
    public MaintenanceTicket completeMaintenanceTicket(CompleteMaintenanceTicketDto completeMaintenanceTicketDto, int userId) {
        String sql = "UPDATE maintenance_tickets SET is_complete = ?, updated_by_user_id = ?, updated_on_date = ? WHERE ticket_id = ?;";

        try {
            template.update(
                    sql,
                    completeMaintenanceTicketDto.isComplete(),
                    userId,
                    new Date(),
                    completeMaintenanceTicketDto.getTicketId()
            );
        } catch (CannotGetJdbcConnectionException e){
            throw new CannotGetJdbcConnectionException("[JDBC MaintenanceTicket DAO] Unable to connect to the database.");
        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("[JDBC MaintenanceTicket DAO] Unable to update ticket: " + completeMaintenanceTicketDto.getTicketId());
        }

        return getMaintenanceTicketById(completeMaintenanceTicketDto.getTicketId());
    }

    // Delete
    public void deleteMaintenanceTicket(int ticketId){
        String sql = "DELETE FROM maintenance_tickets WHERE ticket_id = ?;";

        try {
            deleteMaintenancePerformed(ticketId);
            template.update(sql, ticketId);
        } catch(CannotGetJdbcConnectionException e) {
            throw new CannotGetJdbcConnectionException("[JDBC MaintenanceTicket DAO] Problem connecting to the database.");
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("[JDBC MaintenanceTicket DAO] Error deleting maintenance ticket ID: " + ticketId);
        }
    }

    private void deleteMaintenancePerformed(int ticketId){
        String sql = "DELETE FROM maintenance_performed WHERE ticket_id = ?;";

        try {
            template.update(sql, ticketId);
        } catch(CannotGetJdbcConnectionException e) {
            throw new CannotGetJdbcConnectionException("[JDBC MaintenanceTicket DAO] Problem connecting to the database.");
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("[JDBC MaintenanceTicket DAO] Error deleting maintenance performed for maintenance ticket ID: " + ticketId);
        }
    }

    @Override
    public MaintenanceTicket archiveMaintenanceTicket(ArchiveMaintenanceTicketDto archiveMaintenanceTicketDto, int userId){
        String sql = "UPDATE maintenance_tickets SET is_archived = ?, updated_by_user_id = ?, updated_on_date = ?, archived_notes = ? WHERE ticket_id = ?;";

        try {
            template.update(
                    sql,
                    archiveMaintenanceTicketDto.isArchived(),
                    userId,
                    new Date(),
                    archiveMaintenanceTicketDto.getArchivedNotes(),
                    archiveMaintenanceTicketDto.getTicketId()
            );
        } catch(CannotGetJdbcConnectionException e) {
            throw new CannotGetJdbcConnectionException("[JDBC MaintenanceTicket DAO] Problem connecting to the database.");
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("[JDBC MaintenanceTicket DAO] Error archiving maintenance ticket ID: " + archiveMaintenanceTicketDto.getTicketId());
        }

        return getMaintenanceTicketById(archiveMaintenanceTicketDto.getTicketId());
    }

    private MaintenanceTicket mapRowToMaintenanceTicket(SqlRowSet results){
        MaintenanceTicket maintenanceTicket = new MaintenanceTicket();
        maintenanceTicket.setTicketId(results.getInt("ticket_id"));
        maintenanceTicket.setEquipmentId(results.getInt("equipment_id"));
        maintenanceTicket.setHours(results.getInt("hours"));
        maintenanceTicket.setEnteredByUserId(results.getInt("entered_by_user_id"));
        maintenanceTicket.setDate(results.getDate("date"));
        maintenanceTicket.setNotes(results.getString("notes"));
        maintenanceTicket.setComplete(results.getBoolean("is_complete"));
        maintenanceTicket.setUpdatedByUserId(results.getInt("updated_by_user_id"));
        maintenanceTicket.setUpdatedOnDate(results.getDate("updated_on_date"));
        maintenanceTicket.setArchived(results.getBoolean("is_archived"));
        maintenanceTicket.setArchivedNotes(results.getString("archived_notes"));
        maintenanceTicket.setMaintenancePerformedList(getMaintenancePerformedByTicket(results.getInt("ticket_id")));
        return maintenanceTicket;
    }

    private MaintenancePerformed mapRowToMaintenancePerformed(SqlRowSet results) {
        MaintenancePerformed performed = new MaintenancePerformed();
        performed.setMaintenancePerformedId(results.getInt("maintenance_performed_id"));
        performed.setEquipmentId(results.getInt("equipment_id"));
        performed.setTicketId(results.getInt("ticket_id"));
        performed.setDescription(results.getString("description"));
        performed.setPerformedBy(results.getString("performed_by"));
        performed.setPerformedOnDate((results.getDate("performed_on_date")));
        performed.setNotes(results.getString("notes"));
        performed.setHours(results.getInt("hours"));
        return performed;
    }
}
