package com.techelevator.dao;

import com.techelevator.model.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcMaintenanceDao implements MaintenanceDao {

    private JdbcTemplate template;

    public JdbcMaintenanceDao(DataSource ds){
        template = new JdbcTemplate(ds);
    }

    @Override
    public MaintenanceTicket createMaintenanceTicket(CreateMaintenanceTicketDTO createMaintenanceTicketDTO, int userId) {

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

        for (MaintenancePerformed maintenancePerformed : createMaintenanceTicketDTO.getMaintenancePerformedList()){
            // Add maintenance performed to the database.
            createMaintenancePerformed(maintenancePerformed);
        }

        return getMaintenanceTicketById(ticketId);
    }

    private MaintenancePerformed createMaintenancePerformed(MaintenancePerformed maintenancePerformed){
        int maintenancePerformedId = -1;

        String sql = "INSERT INTO maintenance_performed (equipment_id, ticket_id, description, performed_by, notes "
                + "VALUES (?, ?, ?, ?, ?) RETURNING maintenance_performed_id;";

        try {
            maintenancePerformedId = template.queryForObject(
                    sql,
                    int.class,
                    maintenancePerformed.getEquipmentId(),
                    maintenancePerformed.getTicketId(),
                    maintenancePerformed.getDescription(),
                    maintenancePerformed.getPerformedBy(),
                    maintenancePerformed.getNotes()
            );
        } catch (CannotGetJdbcConnectionException e){
            throw new CannotGetJdbcConnectionException("[JDBC Maintenance DAO] Unable to connect to the database.");
        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("[JDBC Maintenance DAO] Unable to create a new maintenance performed for ticket ID: " + maintenancePerformed.getTicketId());
        }

        return getMaintenancePerformed(maintenancePerformedId);
    }

    @Override
    public MaintenanceTicket addMaintenancePerformed(CreateMaintenancePerformedDto mxpDto) {
        String sql = "INSERT INTO maintenance_performed (equipment_id, ticket_id, description, performed_by, notes) " +
                "VALUES (?, ?, ?, ?, ?) " +
                "RETURNING maintenance_performed_id;";
        int newMPId = -1;

        try {
            newMPId = template.queryForObject(
                    sql,
                    int.class,
                    mxpDto.getEquipmentId(),
                    mxpDto.getTicketId(),
                    mxpDto.getDescription(),
                    mxpDto.getPerformedBy(),
                    mxpDto.getNotes()
            );
        } catch (CannotGetJdbcConnectionException e){
            throw new CannotGetJdbcConnectionException("[JDBC Maintenance DAO] Unable to connect to the database.");
        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("[JDBC Maintenance DAO] Unable to add maintenance performed.");
        }

        return getMaintenanceTicketById(mxpDto.getTicketId());
    }

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

    @Override
    public List<MaintenancePerformed> getMaintenancePerformedByTicket(int id) {
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

    public MaintenancePerformed getMaintenancePerformed(int maintenancePerformedId){
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

    @Override
    public MaintenanceTicket completeMaintenanceTicket(CompleteMaintenanceTicketDto completeMaintenanceTicketDto) {
        String sql = "UPDATE maintenance_tickets SET is_complete = ? WHERE ticket_id = ?;";

        try {
            template.update(sql, completeMaintenanceTicketDto.isComplete(), completeMaintenanceTicketDto.getTicketId());
        } catch (CannotGetJdbcConnectionException e){
            throw new CannotGetJdbcConnectionException("[JDBC MaintenanceTicket DAO] Unable to connect to the database.");
        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("[JDBC MaintenanceTicket DAO] Unable to update ticket: " + completeMaintenanceTicketDto.getTicketId());
        }

        return getMaintenanceTicketById(completeMaintenanceTicketDto.getTicketId());
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
        performed.setNotes(results.getString("notes"));
        return performed;
    }
}
