package com.techelevator.dao;

import com.techelevator.model.TimeOffRequests;
import com.techelevator.model.User;

import java.util.List;

public interface TimeOffRequestsDao {
    List<TimeOffRequests> getAllTimeOffRequests();
    List<TimeOffRequests> getAllTimeOffRequestsByUsername(String username);
    TimeOffRequests getTimeOffRequestByRequestId(int requestId);
    TimeOffRequests createNewTimeOffRequest(TimeOffRequests timeOffRequest);
    public List<TimeOffRequests> getAllTimeOffRequestsByUserId(int id);

}

