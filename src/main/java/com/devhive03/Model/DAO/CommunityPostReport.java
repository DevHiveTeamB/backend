package com.devhive03.Model.DAO;

import jakarta.persistence.*;

import java.security.Timestamp;

@Entity
@Table(name = "CommunityPostReports")
public class CommunityPostReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ReportID", nullable = false)
    private int reportID;

    @ManyToOne
    @JoinColumn(name = "reporter_user_id", nullable = false)
    private User reporterUserID;

    @Column(name = "CommunityPostID", nullable = false)
    private int communityPostID;

    @Column(name = "ReportContent")
    private String reportContent;

    @Column(name = "ReportDate")
    private Timestamp reportDate;

    // Getters and Setters
}