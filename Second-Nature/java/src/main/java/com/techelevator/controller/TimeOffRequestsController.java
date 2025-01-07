package com.techelevator.controller;

import com.techelevator.dao.TimeOffRequestsDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.TimeOffRequest.TimeOffRequests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
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
    public TimeOffRequests getTimeOffRequestByRequestId(@PathVariable int id){
        return timeOffRequestsDao.getTimeOffRequestByRequestId(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path="/add-new-time-off-request", method = RequestMethod.POST)
    public TimeOffRequests addNewTimeOffRequest(@RequestBody TimeOffRequests timeOffRequest, Principal principal) {
        int userId = userDao.getUserIdByUsername(principal.getName());
        return timeOffRequestsDao.createNewTimeOffRequest(timeOffRequest, userId);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping(path="/update-time-off-request")
    public TimeOffRequests updateTimeOffRequest(@RequestBody TimeOffRequests timeOffRequests){
        return timeOffRequestsDao.updateTimeOffRequestById(timeOffRequests.getRequestId(), timeOffRequests);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(path="/delete-time-off-request")
    public String deleteTimeOffRequest(@RequestBody TimeOffRequests timeOffRequests){
        timeOffRequestsDao.deleteTimeOffRequest(timeOffRequests.getRequestId());
        return "Time off request has been deleted.";
    }
}
