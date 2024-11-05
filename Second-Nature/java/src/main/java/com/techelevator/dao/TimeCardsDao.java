package com.techelevator.dao;

import com.techelevator.model.TimeCard.ArchiveTimeCardDto;
import com.techelevator.model.TimeCard.CreateTimeCardDto;
import com.techelevator.model.TimeCard.TimeCards;
import com.techelevator.model.TimeCard.UpdateTimeCardDto;

import java.sql.Timestamp;
import java.util.List;

public interface TimeCardsDao {
    // Create
    public TimeCards createTimeCard(int userId, Timestamp timestamp);
    public TimeCards clockedInStatus(int userId);

    // Read
    public TimeCards getTimeCardById(int timeCardId);
    public List<TimeCards> getTimeCardsByUserId(int userId);

    // Update
    public TimeCards updateTimeCard(UpdateTimeCardDto updateTimeCardDto, int userId, Timestamp timestamp);

    // Archive
    public TimeCards archiveTimeCard(ArchiveTimeCardDto archiveTimeCardDto, int userId);


}
