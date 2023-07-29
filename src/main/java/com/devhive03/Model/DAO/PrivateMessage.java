package com.devhive03.Model.DAO;

import jakarta.persistence.*;

import java.security.Timestamp;

@Entity
@Table(name = "PrivateMessages")
public class PrivateMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer messageID;

    @Column(nullable = false)
    private Integer messageRoomsID;

    @Column(nullable = false)
    private Integer postID;

    @Column(nullable = false)
    private Integer writerID2;

    @Column
    private Integer writerID;

    @Column
    private String privateMessageContent;

    @Column
    private Timestamp privateMessageContentDate;

    // Getters and Setters
}