package com.devhive03.Model.DAO;

import jakarta.persistence.*;

import java.security.Timestamp;

@Entity
@Table(name = "messagerooms")
public class MessageRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "messageroom_id", nullable = false)
    private Integer roomID;


    @ManyToOne
    @JoinColumn(name = "buyer_id", nullable = false)
    private User buyer;


    @Column(nullable = false)
    private Integer postID;

    @Column(name = "last_message_id")
    private String lastMessageID;

    @Column(name = "last_message_date")
    private Timestamp lastMessageDate;

    @Column(name = "state")
    private Integer state;

    @Column(name = "confirmation_status")
    private Integer confirmationStatus;

    // Getters and Setters
}