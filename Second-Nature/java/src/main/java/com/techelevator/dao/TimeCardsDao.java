package com.techelevator.dao;

import com.techelevator.model.TimeCard.ArchiveTimeCardDto;
import com.techelevator.model.TimeCard.CreateTimeCardDto;
import com.techelevator.model.TimeCard.TimeCards;
import com.techelevator.model.TimeCard.UpdateTimeCardDto;

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
