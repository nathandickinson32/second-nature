package com.techelevator.controller;


import com.techelevator.dao.TimeCardsDao;
import com.techelevator.dao.UserDao;
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
        return timeCardsDao.createTimeCard(userId,timestamp);
    }
    @GetMapping(path = "/{id}/time-cards")
    public List <TimeCards> getTimeCardByUserId(Principal principal){
        int userId = userDao.getUserIdByUsername(principal.getName());
        System.out.println(LocalDateTime.now() + " User: " + principal.getName() + " accessed Time Card ID: " + userId);
        return timeCardsDao.getTimeCardsByUserId(userId);
    }
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping(path = "/update")
    public TimeCards updateTimeCard(@RequestBody UpdateTimeCardDto updateTimeCardDto, Principal principal){
        int userId = userDao.getUserIdByUsername(principal.getName());
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(LocalDateTime.now() + " User: " + principal.getName() + " is updating time card ID: " + updateTimeCardDto.getTimeCardId());
        return timeCardsDao.updateTimeCard(updateTimeCardDto, userId, timestamp);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping(path = "/archive")
    public TimeCards archiveTimeCard(@RequestBody ArchiveTimeCardDto archiveTimeCardDto, Principal principal) {
        int userId = userDao.getUserIdByUsername(principal.getName());
        System.out.println(LocalDateTime.now() + " User: " + principal.getName() + " is archiving time card ID ID: " + archiveTimeCardDto.getTimeCardId());
        return timeCardsDao.archiveTimeCard(archiveTimeCardDto, userId);
    }

}
