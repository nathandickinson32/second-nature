package com.techelevator.dao;

import com.techelevator.model.CreateMaintenanceTicketDTO;
import com.techelevator.model.MaintenancePerformed;
import com.techelevator.model.MaintenanceTicket;
import com.techelevator.model.CompleteMaintenanceTicketDto;

import java.util.List;

public interface MaintenanceDao {
    // Create
    public MaintenanceTicket createMaintenanceTicket(CreateMaintenanceTicketDTO createMaintenanceTicketDTO);
    // Read
    public List<MaintenanceTicket> getAllMaintenanceTickets();
    public MaintenanceTicket getMaintenanceTicketById(int id);
    public List<MaintenancePerformed> getMaintenancePerformedByTicket(int id);
    // Update
    public MaintenanceTicket completeMaintenanceTicket(CompleteMaintenanceTicketDto completeMaintenanceTicketDto);
    // Delete / Archive

}
