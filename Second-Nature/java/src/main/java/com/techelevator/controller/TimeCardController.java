package com.techelevator.controller;


import com.techelevator.dao.TimeCardDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.ArchiveEquipmentDto;
import com.techelevator.model.Equipment;
import com.techelevator.model.TimeCard.ArchiveTimeCardDto;
import com.techelevator.model.TimeCard.CreateTimeCardDto;
import com.techelevator.model.TimeCard.TimeCards;
import com.techelevator.model.TimeCard.UpdateTimeCardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(path = "/time-card")
@CrossOrigin
@PreAuthorize("isAuthenticated()")

public class TimeCardController {
    @Autowired
    private TimeCardDao timeCardDao;

    @Autowired
    private UserDao userDao;


    @GetMapping(path = "/{id}")
    public TimeCards getTimeCardById(@PathVariable int id, Principal principal){
        System.out.println(LocalDateTime.now() + " User: " + principal.getName() + " accessed Time Card ID: " + id);
        return timeCardDao.getTimeCardById(id);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/register")
    public TimeCards createTimeCard(@RequestBody CreateTimeCardDto createTimeCardDto, Principal principal){
        int userId = userDao.getUserIdByUsername(principal.getName());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(LocalDateTime.now() + " User: " + principal.getName() + " is creating a new time card.");
        return timeCardDao.createTimeCard(createTimeCardDto, userId,timestamp);
    }
    @GetMapping(path = "/{id}/time-cards")
    public List <TimeCards> getTimeCardByUserId(Principal principal){
        int userId = userDao.getUserIdByUsername(principal.getName());
        System.out.println(LocalDateTime.now() + " User: " + principal.getName() + " accessed Time Card ID: " + userId);
        return timeCardDao.getTimeCardsByUserId(userId);
    }
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping(path = "/update")
    public TimeCards updateTimeCard(@RequestBody UpdateTimeCardDto updateTimeCardDto, Principal principal){
        int userId = userDao.getUserIdByUsername(principal.getName());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(LocalDateTime.now() + " User: " + principal.getName() + " is updating time card ID: " + updateTimeCardDto.getTimeCardId());
        return timeCardDao.updateTimeCard(updateTimeCardDto, userId, timestamp);
    }

}
