package com.devhive03.Model.DTO.Message;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter @Setter
public class MessageRoomDTO {

    private Buyer buyer;
    private Long roomID;
    private String lastMessageData;
    private Timestamp lastMessageDate;


    //... MessageRoom Class ...
    @Data
    public static class Buyer {
        private Long id;
        private String username;
        private String profilePhoto;
    }

    // Constructors, Getters, and Setters
}