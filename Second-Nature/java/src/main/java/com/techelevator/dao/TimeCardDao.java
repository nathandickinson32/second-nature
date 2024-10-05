package com.techelevator.dao;

import com.techelevator.model.TimeCard.CreateTimeCardDto;
import com.techelevator.model.TimeCard.TimeCards;
import com.techelevator.model.TimeCard.UpdateTimeCardDto;

import java.sql.Timestamp;
import java.util.List;

public interface TimeCardDao {
    // Create
    public TimeCards createTimeCard(CreateTimeCardDto createTimeCardDto, int userId, Timestamp timestamp);

    // Read
    public TimeCards getTimeCardById(int timeCardId);
    public List<TimeCards> getTimeCardsByUserId(int userId);

    //update
    public TimeCards updateTimeCard(UpdateTimeCardDto updateTimeCardDto, int userId, Timestamp timestamp);
}
