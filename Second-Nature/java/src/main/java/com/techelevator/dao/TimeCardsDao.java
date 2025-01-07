package com.techelevator.dao;

import com.techelevator.model.TimeCard.*;

import java.sql.Timestamp;
import java.util.List;

public interface TimeCardsDao {
    // Create
    public TimeCards createLoggedInTimeCard(int userId, Timestamp timestamp);
    public TimeCards handleLoggedInTimeCardPunch(int userId);
    public void handleLoggedOutTimeCardPunch(int userId);
    public void createLoggedOutTimeCard(int userId, Timestamp timestamp);

    // Read
    public TimeCards getTimeCardById(int timeCardId);
    public List<TimeCards> getTimeCardsByUserId(int userId);
    public List<TimeCards> getTimeCardsForCurrentPayPeriod(int userId);

    // Update
    public TimeCards updateLoggedInTimeCard(UpdateTimeCardDto updateTimeCardDto, int userId, Timestamp timestamp);
    public TimeCards managerUpdateTimeCard(ManagerUpdateCardDto managerUpdateCardDto, int userId, Timestamp timestamp);
    public void updateLoggedOutTimeCard(UpdateTimeCardDto updateTimeCardDto, int userId, Timestamp timestamp);

    // Archive
    public TimeCards archiveTimeCard(ArchiveTimeCardDto archiveTimeCardDto, int userId);


}
