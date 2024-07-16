package com.techelevator.dao;

import com.techelevator.model.TimeOffRequests;
import com.techelevator.model.User;

import java.util.List;

public interface TimeOffRequestsDao {
    List<TimeOffRequests> getAllTimeOffRequests();
}
