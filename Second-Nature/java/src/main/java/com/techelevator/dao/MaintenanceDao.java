package com.techelevator.dao;

import com.techelevator.model.*;

import java.util.List;

public interface MaintenanceDao {
    // Create
    public MaintenanceTicket createMaintenanceTicket(CreateMaintenanceTicketDto createMaintenanceTicketDTO, int userId);
    // Read
    public List<MaintenanceTicket> getAllMaintenanceTickets();
    public MaintenanceTicket getMaintenanceTicketById(int id);
    // public List<MaintenancePerformed> getMaintenancePerformedByTicket(int id);
    // public MaintenancePerformed getMaintenancePerformed(int maintenancePerformedId);
    // Update
    public MaintenanceTicket updateMaintenanceTicket(UpdateMaintenanceTicketDto updateMaintenanceTicketDto);
    public MaintenanceTicket completeMaintenanceTicket(CompleteMaintenanceTicketDto completeMaintenanceTicketDto);
    // Delete / Archive
    public void deleteMaintenanceTicket(int ticketId);
    public void archiveMaintenanceTicket(int ticketId);
}
