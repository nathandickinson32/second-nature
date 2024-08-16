package com.techelevator.dao;

import com.techelevator.model.CreateMaintenanceTicketDTO;
import com.techelevator.model.MaintenanceTicket;

import java.util.List;

public interface MaintenanceDao {
    // Create
    public MaintenanceTicket createMaintenanceTicket(CreateMaintenanceTicketDTO createMaintenanceTicketDTO);
    // Read
    public List<MaintenanceTicket> getAllMaintenanceTickets();
    // Update
    // Delete / Archive

}
