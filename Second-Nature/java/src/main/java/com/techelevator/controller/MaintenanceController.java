package com.techelevator.controller;

import com.techelevator.dao.MaintenanceDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.MaintenanceTicket;
import com.techelevator.model.MaintenanceTicketDto;
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
    @PostMapping(path = "/complete")
    public MaintenanceTicket completeMaintenanceTicket(@RequestBody MaintenanceTicketDto maintenanceTicketDto, Principal principal) {
        System.out.println(LocalDateTime.now() + " User: " + principal.getName() + " completed maintenance ticket " + maintenanceTicketDto.getTicketId());
        return maintenanceDao.completeMaintenanceTicket(maintenanceTicketDto);
    }

    // Delete
}
