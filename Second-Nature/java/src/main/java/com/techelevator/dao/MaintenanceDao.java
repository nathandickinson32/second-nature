package com.techelevator.dao;

import com.techelevator.model.*;

import java.util.List;

public interface MaintenanceDao {
    // Create
    public MaintenanceTicket createMaintenanceTicket(CreateMaintenanceTicketDTO createMaintenanceTicketDTO, int userId);
    public MaintenanceTicket addMaintenancePerformed(CreateMaintenancePerformedDto createMaintenancePerformedDTO);
    // Read
    public List<MaintenanceTicket> getAllMaintenanceTickets();
    public MaintenanceTicket getMaintenanceTicketById(int id);
    public List<MaintenancePerformed> getMaintenancePerformedByTicket(int id);
    // Update
    public MaintenanceTicket completeMaintenanceTicket(CompleteMaintenanceTicketDto completeMaintenanceTicketDto);
    // Delete / Archive

}
