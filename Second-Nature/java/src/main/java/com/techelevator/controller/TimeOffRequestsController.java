package com.techelevator.controller;

import com.techelevator.dao.TimeOffRequestsDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.TimeOffRequests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
public class TimeOffRequestsController {
    @Autowired
    private TimeOffRequestsDao timeOffRequestsDao;
    @Autowired
    private UserDao userDao;

    @RequestMapping(path = "/get-all-time-off-requests", method = RequestMethod.GET)
    public List<TimeOffRequests> getAllTimeOffRequests() {
        return timeOffRequestsDao.getAllTimeOffRequests();
    }

    @RequestMapping(path="/get-all-time-off-requests-by-username", method = RequestMethod.GET)
    public List<TimeOffRequests> getAllTimeOffRequestsByUsername(Principal principal) {
        return timeOffRequestsDao.getAllTimeOffRequestsByUsername(principal.getName());
    }

    @RequestMapping(path="/get-all-time-off-requests-by-user-id", method = RequestMethod.GET)
    public List<TimeOffRequests> getAllTimeOffRequestsByUserId(Principal principal) {
        return timeOffRequestsDao.getAllTimeOffRequestsByUserId(userDao.getUserIdByUsername(principal.getName()));
    }


    @RequestMapping(path="/get-time-off-request-by-request-id/{id}", method = RequestMethod.GET)
    public TimeOffRequests getIngredientsByRecipeId(@PathVariable int id){
        return timeOffRequestsDao.getTimeOffRequestByRequestId(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path="/add-new-time-off-request", method = RequestMethod.POST)
    public TimeOffRequests addNewTimeOffRequest(@RequestBody TimeOffRequests timeOffRequest) {
        return timeOffRequestsDao.createNewTimeOffRequest(timeOffRequest);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping(path="/update-time-off-request/{id}")
    public TimeOffRequests updateTimeOffRequest(@PathVariable int id, @RequestBody TimeOffRequests timeOffRequests){
        return timeOffRequestsDao.updateTimeOffRequestById(id, timeOffRequests);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(path="/delete-time-off-request/{id}")
    public String deleteTimeOffRequest(@PathVariable int id){
        timeOffRequestsDao.deleteTimeOffRequest(id);
        return "Time Off Request ID: " + id + " has been deleted.";
    }
}
