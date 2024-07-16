package com.techelevator.controller;

import com.techelevator.dao.TimeOffRequestsDao;
import com.techelevator.model.TimeOffRequests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class TimeOffRequestsController {
@Autowired
    private TimeOffRequestsDao timeOffRequestsDao;

@RequestMapping(path="/get-all-time-off-requests", method = RequestMethod.GET)
    public List<TimeOffRequests>getAllTimeOffRequests(){
    return timeOffRequestsDao.getAllTimeOffRequests();
}
}
