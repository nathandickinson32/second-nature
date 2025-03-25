package com.techelevator.dao;

import com.techelevator.model.Messages.CreateMessageDto;
import com.techelevator.model.Messages.Message;

public interface MessageDao {
    //CREATE
    public Message createMessage(CreateMessageDto createMessageDto, int userId);

    //READ
    public  Message getMessageById(int messageId);
}

