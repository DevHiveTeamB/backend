package com.devhive03.Model.DTO.Message;

import lombok.Getter;
import lombok.Setter;

import java.security.Timestamp;

@Getter @Setter
public class MessageRoomDTO {
    private Long roomID;
    private String lastMessageData;
    private Timestamp lastMessageDate;

    //... MessageRoom Class ...



    // Constructors, Getters, and Setters
}