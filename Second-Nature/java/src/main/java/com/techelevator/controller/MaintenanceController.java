package com.techelevator.controller;

import com.techelevator.dao.MaintenanceDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "/maintenance-tickets")
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class MaintenanceController {
    @Autowired
    private MaintenanceDao maintenanceDao;

    @Autowired
    private UserDao userDao;

    // Create
    // Need to update path to include ticketId
//    @PostMapping(path = "/add-maintenance-performed")
//    public MaintenanceTicket addMaintenancePerformed(@RequestBody CreateMaintenancePerformedDto createMaintenancePerformedDto, Principal principal) {
//        System.out.println(LocalDateTime.now() + " User: " + principal.getName() + " added performed maintenance.");
//        return maintenanceDao.createMaintenancePerformed(createMaintenancePerformedDto);
//    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/create")
    public MaintenanceTicket createMaintenanceTicket(@RequestBody CreateMaintenanceTicketDto createMaintenanceTicketDto, Principal principal) {
        System.out.println(LocalDateTime.now() + " User: " + principal.getName() + " created a new maintenance ticket.");
        return maintenanceDao.createMaintenanceTicket(createMaintenanceTicketDto, userDao.getUserIdByUsername(principal.getName()));
    }

    // Read
    @GetMapping(path = "/all")
    public List<MaintenanceTicket> getAllMaintenanceTickets(Principal principal){
        System.out.println(LocalDateTime.now() + " User: " + principal.getName() + " accessed a list of all maintenance tickets.");
        return maintenanceDao.getAllMaintenanceTickets();
    }

    @GetMapping(path = "/{ticketId}")
    public MaintenanceTicket getTicketById(@PathVariable int ticketId, Principal principal) {
        System.out.println(LocalDateTime.now() + " User: " + principal.getName() + " accessed maintenance ticket " + ticketId);
        return maintenanceDao.getMaintenanceTicketById(ticketId);
    }

    // Update
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping(path = "/update")
    public MaintenanceTicket updateMaintenanceTicket(@RequestBody UpdateMaintenanceTicketDto updateMaintenanceTicketDto, Principal principal){
        System.out.println(LocalDateTime.now() + " User: " + principal.getName() + " is updating maintenance ticket ID: " + updateMaintenanceTicketDto.getTicketId());
        int userId = userDao.getUserIdByUsername(principal.getName());
        return maintenanceDao.updateMaintenanceTicket(updateMaintenanceTicketDto, userId);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping(path = "/complete")
    public MaintenanceTicket completeMaintenanceTicket(@RequestBody CompleteMaintenanceTicketDto completeMaintenanceTicketDto, Principal principal) {
        System.out.println(LocalDateTime.now() + " User: " + principal.getName() + " completed maintenance ticket " + completeMaintenanceTicketDto.getTicketId());
        int userId = userDao.getUserIdByUsername(principal.getName());
        return maintenanceDao.completeMaintenanceTicket(completeMaintenanceTicketDto, userId);
    }

    // Delete
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(path = "/delete/{ticketId}")
    public void deleteMaintenanceTicket(@PathVariable int ticketId, Principal principal){
        System.out.println(LocalDateTime.now() + " User: " + principal.getName() + " is deleting maintenance ticket ID: " + ticketId);
        maintenanceDao.deleteMaintenanceTicket(ticketId);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping(path = "/archive")
    public MaintenanceTicket archiveMaintenanceTicket(@RequestBody ArchiveMaintenanceTicketDto archiveMaintenanceTicketDto, Principal principal){
        System.out.println(LocalDateTime.now() + " User: " + principal.getName() + " is archiving maintenance ticket ID: " + archiveMaintenanceTicketDto.getTicketId());
        int userId = userDao.getUserIdByUsername(principal.getName());
        return maintenanceDao.archiveMaintenanceTicket(archiveMaintenanceTicketDto, userId);
    }
}
