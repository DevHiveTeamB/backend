package com.devhive03.Model.DTO.Message;

import lombok.Getter;
import lombok.Setter;

import java.security.Timestamp;

@Getter @Setter
public class MessageRoomDTO {
    private Long lastMessageID;
    private Timestamp lastMessageDate;
    private Integer state;
    private Integer confirmationStatus;

    //... MessageRoom Class ...



    // Constructors, Getters, and Setters
}