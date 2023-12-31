package com.devhive03.Model.DAO;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "messageroom_reports")
public class MessageRoomsReport { //나중에 s뺴줘

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id", nullable = false)
    private Long reportID;

    @ManyToOne
    @JoinColumn(name = "reporter_user_id", nullable = false)
    private User user;

    @OneToOne
    @JoinColumn(name = "reportedMessageRooms_ID", nullable = false)
    private MessageRoom reportedMessageRooms;

    @Column(name = "report_content")
    private String reportContent = "";

    @Column(name = "report_date")
    private Timestamp reportDate = new java.sql.Timestamp(System.currentTimeMillis());

    // Getters and Setters
}