package com.techelevator.dao;

import com.techelevator.model.ArchiveTimeCardDto;
import com.techelevator.model.CreateTimeCardDto;
import com.techelevator.model.TimeCards;
import com.techelevator.model.UpdateTimeCardDto;

public interface TimeCardsDao {

    //Create
    public TimeCards createTimeCards(CreateTimeCardDto timeCardDto, int userId);

    //Read
    public TimeCards getTimeCardByUserId(int userId);
    public TimeCards getTimeCardById(int timeCardId);

    //Update
    public TimeCards updateTimeCard(UpdateTimeCardDto updateTimeCardDto, int timeCardId);

    //Delete
    public void deleteTimeCard(int timeCardId);
    public TimeCards archiveTimeCard(ArchiveTimeCardDto archiveTimeCardDto, int timeCardId);

}
