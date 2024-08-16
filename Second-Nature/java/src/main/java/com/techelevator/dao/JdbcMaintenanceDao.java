package com.techelevator.dao;

import com.techelevator.model.CreateMaintenanceTicketDTO;
import com.techelevator.model.MaintenanceTicket;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcMaintenanceDao implements MaintenanceDao {

    private JdbcTemplate template;

    public JdbcMaintenanceDao(DataSource ds){
        template = new JdbcTemplate(ds);
    }

    @Override
    public MaintenanceTicket createMaintenanceTicket(CreateMaintenanceTicketDTO createMaintenanceTicketDTO) {
        return null;
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
        return maintenanceTicket;
    }
}
