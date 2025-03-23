package com.techelevator.model.Messages;

public class CreateChatRoomDto {
    private String roomName;
    private boolean isGroup;

    public CreateChatRoomDto(String roomName, boolean isGroup) {
        this.roomName = roomName;
        this.isGroup = isGroup;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public boolean isGroup() {
        return isGroup;
    }

    public void setGroup(boolean group) {
        isGroup = group;
    }
}
