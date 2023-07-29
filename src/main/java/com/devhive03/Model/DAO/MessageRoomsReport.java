package com.devhive03.Model.DAO;

import jakarta.persistence.*;

import java.security.Timestamp;

@Entity
@Table(name = "MessageRoomsReports")
public class MessageRoomsReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer reportID;

    @Column(nullable = false)
    private Integer reporterUserID;

    @Column(nullable = false)
    private Integer reportedMessageRoomsID;

    @Column(nullable = false)
    private Integer postID;

    @Column(nullable = false)
    private Integer writerID;

    @Column
    private String reportContent;

    @Column
    private Timestamp reportDate;

    // Getters and Setters
}