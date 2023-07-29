package com.devhive03.Model.DAO;

import jakarta.persistence.*;

import java.security.Timestamp;

@Entity
@Table(name = "MessageRooms")
public class MessageRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer roomID;

    @Column(nullable = false)
    private Integer buyerID;

    @Column(nullable = false)
    private Integer postID;

    @Column(nullable = false)
    private Integer writerID;

    @Column
    private String lastMessageID;

    @Column
    private Timestamp lastMessageDate;

    @Column
    private Integer state;

    @Column
    private Integer confirmationStatus;

    // Getters and Setters
}