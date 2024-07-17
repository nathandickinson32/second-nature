package com.techelevator.controller;

import com.techelevator.dao.TimeOffRequestsDao;
import com.techelevator.model.TimeOffRequests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class TimeOffRequestsController {
    @Autowired
    private TimeOffRequestsDao timeOffRequestsDao;

    @RequestMapping(path = "/get-all-time-off-requests", method = RequestMethod.GET)
    public List<TimeOffRequests> getAllTimeOffRequests() {
        return timeOffRequestsDao.getAllTimeOffRequests();
    }

    @RequestMapping(path="/get-all-time-off-requests-by-user-id/{id}", method = RequestMethod.GET)
    public List<TimeOffRequests> getAllTimeOffRequestsByUserId(@PathVariable int id) {
        return timeOffRequestsDao.getAllTimeOffRequestsByUserId(id);
    }
}
