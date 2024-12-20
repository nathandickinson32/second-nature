package com.techelevator.dao;

import com.techelevator.model.TimeCard.*;

import java.sql.Timestamp;
import java.util.List;

public interface TimeCardsDao {
    // Create
    public TimeCards createTimeCard(int userId, Timestamp timestamp);
    public TimeCards handleTimeCardPunch(int userId);

    // Read
    public TimeCards getTimeCardById(int timeCardId);
    public List<TimeCards> getTimeCardsByUserId(int userId);
    public List<TimeCards> getTimeCardsForCurrentPayPeriod(int userId);

    // Update
    public TimeCards updateTimeCard(UpdateTimeCardDto updateTimeCardDto, int userId, Timestamp timestamp);
    public TimeCards managerUpdateTimeCard(ManagerUpdateCardDto managerUpdateCardDto, int userId, Timestamp timestamp);

    // Archive
    public TimeCards archiveTimeCard(ArchiveTimeCardDto archiveTimeCardDto, int userId);


}
