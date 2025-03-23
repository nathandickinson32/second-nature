package com.techelevator.dao;

import com.techelevator.model.Messages.ChatRoom;
import com.techelevator.model.Messages.CreateChatRoomDto;

import java.util.List;

public interface ChatRoomDao {
    //CREATE
    public ChatRoom createChatRoom(CreateChatRoomDto createChatRoomDTO, int userId);

    //READ
    public ChatRoom getChatRoomById(int id);

    public List<Integer> getMembersByRoomId(int roomId);
    public List<Integer> getRoomIdsByMemberId(int memberId);

    //UPDATE
    void addMemberToChatRoom( int roomId, int userId);

}
