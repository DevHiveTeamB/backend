package com.devhive03.Model.DTO.Message;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter

public class UserMessageRoomResponse {
    private Long userID;
    private List<MessageRoomDTO> messageRoom;

    // Constructors, Getters, and Setters
}