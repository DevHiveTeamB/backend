package com.devhive03.Model.DAO;

import jakarta.persistence.*;
import jakarta.websocket.OnMessage;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Getter @Setter
@Table(name = "private_messages")
public class PrivateMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id", nullable = false)
    private Long messageID;

    @ManyToOne
    @JoinColumn(name = "messagerooms_id", nullable = false)
    private MessageRoom messageRooms;

    @ManyToOne
    @JoinColumn(name = "message_writer_id", nullable = false)
    private User MessageWriter;

    @Column(name = "private_message_content")
    private String privateMessageContent;

    @Column(name = "private_message_content_date")
    private Timestamp privateMessageContentDate = new java.sql.Timestamp(System.currentTimeMillis());;

    public String getPrivateMessageContent() {
        return privateMessageContent;
    }

    public void setPrivateMessageContent(String privateMessageContent) {
        this.privateMessageContent = privateMessageContent;
    }

    public void setMessageRoom(MessageRoom messageRooms) {

        this.messageRooms = messageRooms;
    }



    // Getters and Setters
}