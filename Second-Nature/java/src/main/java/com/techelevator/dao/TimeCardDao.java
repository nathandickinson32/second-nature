package com.techelevator.dao;

import com.techelevator.model.CreateTimeCardDto;
import com.techelevator.model.Equipment;
import com.techelevator.model.TimeCards;

import java.sql.Timestamp;
import java.util.List;

public interface TimeCardDao {
    // Create
    public TimeCards createTimeCard(CreateTimeCardDto createTimeCardDto, int userId, Timestamp timestamp);

    // Read
    public TimeCards getTimeCardById(int timeCardId);
    public List<TimeCards> getTimeCardsByUserId(int userId);


}
