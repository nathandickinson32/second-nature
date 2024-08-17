package com.techelevator.dao;

import com.techelevator.model.CreateMaintenanceTicketDTO;
import com.techelevator.model.MaintenanceTicket;
import com.techelevator.model.MaintenanceTicketDto;

import java.util.List;

public interface MaintenanceDao {
    // Create
    public MaintenanceTicket createMaintenanceTicket(CreateMaintenanceTicketDTO createMaintenanceTicketDTO);
    // Read
    public List<MaintenanceTicket> getAllMaintenanceTickets();
    public MaintenanceTicket getMaintenanceTicketById(int id);
    // Update
    public MaintenanceTicket completeMaintenanceTicket(MaintenanceTicketDto maintenanceTicketDto);
    // Delete / Archive

}
