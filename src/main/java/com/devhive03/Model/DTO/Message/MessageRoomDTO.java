package com.devhive03.Model.DTO.Message;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter @Setter
public class MessageRoomDTO {

    private Opponent opponent;
    private Long roomID;
    private String lastMessageData;
    private Timestamp lastMessageDate;
    private Long postid;

    //... MessageRoom Class ...
    @Data
    public static class Opponent {
        private Long id;
        private String username;
        private String profilePhoto;
    }

    // Constructors, Getters, and Setters
}