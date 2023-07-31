package com.devhive03.Model.DAO;

import jakarta.persistence.*;
import jakarta.websocket.OnMessage;

import java.security.Timestamp;

@Entity
@Table(name = "private_messages")
public class PrivateMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id", nullable = false)
    private Integer messageID;

    @ManyToOne
    @JoinColumn(name = "messagerooms_id", nullable = false)
    private MessageRoom messageRooms;

    @ManyToOne
    @JoinColumn(name = "message_writer_id", nullable = false)
    private User MessageWriter;

    @Column(name = "private_message_content")
    private String privateMessageContent;

    @Column(name = "private_message_content_date")
    private Timestamp privateMessageContentDate;

    // Getters and Setters
}