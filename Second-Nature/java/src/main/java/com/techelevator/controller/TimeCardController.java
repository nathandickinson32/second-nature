package com.techelevator.controller;


import com.techelevator.dao.TimeCardsDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.TimeCard.*;
import com.techelevator.model.UserFolder.UserIdDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/time-card")
@CrossOrigin

public class TimeCardController {
    @Autowired
    private TimeCardsDao timeCardsDao;

   

    @Autowired
    private UserDao userDao;


    @GetMapping(path = "/{id}")
    public TimeCards getTimeCardById(@PathVariable int id, Principal principal){
        System.out.println(LocalDateTime.now() + " User: " + principal.getName() + " accessed Time Card ID: " + id);
        return timeCardsDao.getTimeCardById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/register")
    public TimeCards createTimeCard(Principal principal){
        int userId = userDao.getUserIdByUsername(principal.getName());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(LocalDateTime.now() + " User: " + principal.getName() + " is creating a new time card.");
        return timeCardsDao.handleLoggedInTimeCardPunch(userId);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/employee-login")
    public void createEmployeeTimeCard(@RequestBody UserIdDto userIdDto){
        int userId = userIdDto.getId();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(LocalDateTime.now() + " User with id: " + userId + " is creating a new time card.");
         timeCardsDao.handleLoggedOutTimeCardPunch(userId);
    }
    @GetMapping(path = "/{id}/time-cards")
    public List <TimeCards> getTimeCardByUserId(Principal principal){
        int userId = userDao.getUserIdByUsername(principal.getName());
        System.out.println(LocalDateTime.now() + " User: " + principal.getName() + " accessed Time Card ID: " + userId);
        return timeCardsDao.getTimeCardsByUserId(userId);
    }
    //changed to RequestParam annotation for GET request
    //added principal so method only retrieves timecard for authenticated user
    @GetMapping(path = "/date")
    public List <TimeCards> getTimeCardsByDateAndUser(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date, Principal principal){
        //extracting username from principal to get authenticated userId
        String username = principal.getName();
        int userId = userDao.getUserIdByUsername(username);
        System.out.println(LocalDateTime.now() + " User: " + username + "accessed Time Cards by date: " + date);
        return timeCardsDao.getTimeCardsByDateAndUser(date, userId);
    }
    @GetMapping(path = "/{id}/time-sheet")
    public List<TimeCards> getTimeCardsForCurrentPayPeriod(Principal principal){
        int userId = userDao.getUserIdByUsername(principal.getName());
        return timeCardsDao.getTimeCardsForCurrentPayPeriod(userId);
    }
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping(path = "/update")
    public TimeCards updateTimeCard(@RequestBody UpdateTimeCardDto updateTimeCardDto, Principal principal){
        int userId = userDao.getUserIdByUsername(principal.getName());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(LocalDateTime.now() + " User: " + principal.getName() + " is updating time card ID: " + updateTimeCardDto.getTimeCardId());
        return timeCardsDao.updateLoggedInTimeCard(updateTimeCardDto, userId, timestamp);
    }
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping(path = "/manager-update")
    public TimeCards managerUpdateTimeCard(@RequestBody ManagerUpdateCardDto managerUpdateCardDto, Principal principal){
        int userId = userDao.getUserIdByUsername(principal.getName());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(LocalDateTime.now() + " User: " + principal.getName() + " is updating time card ID: " + managerUpdateCardDto.getTimeCardId());
        return timeCardsDao.managerUpdateTimeCard(managerUpdateCardDto, userId, timestamp);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping(path = "/archive")
    public TimeCards archiveTimeCard(@RequestBody ArchiveTimeCardDto archiveTimeCardDto, Principal principal) {
        int userId = userDao.getUserIdByUsername(principal.getName());
        System.out.println(LocalDateTime.now() + " User: " + principal.getName() + " is archiving time card ID ID: " + archiveTimeCardDto.getTimeCardId());
        return timeCardsDao.archiveTimeCard(archiveTimeCardDto, userId);
    }

}
